package de.sonallux.spotify.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.sonallux.spotify.api.authorization.ApiAuthorizationProvider;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.AuthorizationAddingInterceptor;
import de.sonallux.spotify.api.http.AuthorizedApiCall;
import lombok.NoArgsConstructor;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Objects;

@NoArgsConstructor
public class SpotifyWebApiBuilder {
    private HttpUrl baseUrl;
    private Call.Factory callFactory;
    private ObjectMapper objectMapper;
    private ApiAuthorizationProvider authorizationProvider;

    public SpotifyWebApiBuilder baseUrl(String baseUrl) {
        Objects.requireNonNull(baseUrl, "baseUrl == null");
        return baseUrl(HttpUrl.get(baseUrl));
    }

    public SpotifyWebApiBuilder baseUrl(HttpUrl baseUrl) {
        this.baseUrl = Objects.requireNonNull(baseUrl, "baseUrl == null");
        return this;
    }

    public SpotifyWebApiBuilder httpClient(OkHttpClient httpClient) {
        return callFactory(Objects.requireNonNull(httpClient, "httpClient == null"));
    }

    public SpotifyWebApiBuilder callFactory(Call.Factory factory) {
        this.callFactory = Objects.requireNonNull(factory, "factory == null");
        return this;
    }

    public SpotifyWebApiBuilder objectMapper(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper == null");
        return this;
    }

    public SpotifyWebApiBuilder authorization(ApiAuthorizationProvider authorizationProvider) {
        this.authorizationProvider = Objects.requireNonNull(authorizationProvider, "authorizationProvider == null");
        return this;
    }

    public SpotifyWebApi build() {
        if (baseUrl == null) {
            baseUrl = SpotifyWebApi.SPOTIFY_WEB_API_ENDPOINT;
        }

        if (callFactory == null) {
            if (authorizationProvider == null) {
                callFactory = new OkHttpClient();
            } else {
                callFactory = new OkHttpClient.Builder()
                    .addInterceptor(new AuthorizationAddingInterceptor(authorizationProvider))
                    .build();
            }
        }

        if (objectMapper == null) {
            objectMapper = createDefaultObjectMapper();
        }

        var apiClientBuilder = ApiClient.builder()
            .baseUrl(baseUrl)
            .callFactory(callFactory)
            .objectMapper(objectMapper);

        if (authorizationProvider != null) {
            apiClientBuilder.apiCallFactory(new AuthorizedApiCall.Factory(authorizationProvider));
        }

        return new SpotifyWebApi(apiClientBuilder.build());
    }

    private ObjectMapper createDefaultObjectMapper() {
        return new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .registerModule(new JavaTimeModule());
    }
}
