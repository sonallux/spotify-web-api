package de.sonallux.spotify.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

@Slf4j
public class SpotifyClientCredentialsAuthInterceptor implements ClientHttpRequestInterceptor {

    private final String spotifyClientId;
    private final String spotifyClientSecret;
    private final RestTemplate restTemplate;
    private AccessToken accessToken = null;

    public SpotifyClientCredentialsAuthInterceptor() {
        this.spotifyClientId = requireNonNull(System.getenv("SPOTIFY_CLIENT_ID"), "Missing SPOTIFY_CLIENT_ID environment variable");
        this.spotifyClientSecret = requireNonNull(System.getenv("SPOTIFY_CLIENT_SECRET"), "Missing SPOTIFY_CLIENT_SECRET environment variable");
        this.restTemplate = new RestTemplate();
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return execution.execute(request, body);
        }

        if (accessToken == null) {
            retrieveNewAccessToken();
        }

        if (accessToken != null) {
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, accessToken.asHeaderValue());
        }

        return execution.execute(request, body);
    }

    private void retrieveNewAccessToken() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(spotifyClientId, spotifyClientSecret);

        var body = new LinkedMultiValueMap<String, String>();
        body.add("grant_type", "client_credentials");

        var httpEntity = new HttpEntity<>(body, headers);

        try {
            var response = restTemplate.exchange("https://accounts.spotify.com/api/token", HttpMethod.POST, httpEntity, AccessToken.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                this.accessToken = response.getBody();
            } else {
                log.warn("Failed to retrieve access token: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            log.warn("Failed to retrieve access token", e);
        }
    }

    private record AccessToken(String token_type, String access_token) {
        public String asHeaderValue() {
            return "%s %s".formatted(token_type, access_token);
        }
    }
}
