package de.sonallux.spotify.validator;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

@Slf4j
public class SpotifyClientCredentialsAuthInterceptor implements ClientHttpRequestInterceptor {

    private final String spotifyClientId;
    private final String spotifyClientSecret;
    private final RestClient restClient;
    private @Nullable AccessToken accessToken = null;

    public SpotifyClientCredentialsAuthInterceptor() {
        this.spotifyClientId = requireNonNull(System.getenv("SPOTIFY_CLIENT_ID"), "Missing SPOTIFY_CLIENT_ID environment variable");
        this.spotifyClientSecret = requireNonNull(System.getenv("SPOTIFY_CLIENT_SECRET"), "Missing SPOTIFY_CLIENT_SECRET environment variable");
        this.restClient = RestClient.builder().build();
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (request.getHeaders().containsHeader(HttpHeaders.AUTHORIZATION)) {
            return execution.execute(request, body);
        }

        if (accessToken == null) {
            retrieveNewAccessToken();
        }

        if (accessToken != null && "Bearer".equals(accessToken.tokenType)) {
            request.getHeaders().setBearerAuth(accessToken.accessToken());
        }

        return execution.execute(request, body);
    }

    private void retrieveNewAccessToken() {
        var body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", "client_credentials");

        try {
            this.accessToken = restClient.post()
                .uri("https://accounts.spotify.com/api/token")
                .headers(headers -> {
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.setBasicAuth(spotifyClientId, spotifyClientSecret);
                })
                .body(body)
                .retrieve()
                .body(AccessToken.class);
        } catch (RestClientException e) {
            log.warn("Failed to retrieve access token", e);
        }
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private record AccessToken(String tokenType, String accessToken) {
    }
}
