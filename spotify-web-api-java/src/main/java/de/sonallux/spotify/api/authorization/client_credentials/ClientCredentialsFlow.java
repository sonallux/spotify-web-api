package de.sonallux.spotify.api.authorization.client_credentials;

import de.sonallux.spotify.api.BaseSpotifyApi;
import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.authorization.InMemoryTokenStore;
import de.sonallux.spotify.api.authorization.SpotifyAuthorizationException;
import de.sonallux.spotify.api.authorization.TokenStore;
import de.sonallux.spotify.api.authorization.TokenStoreApiAuthorizationProvider;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Base64;

/**
 * Implements the
 * <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#client-credentials-flow">
 * Client Credentials Flow</a>
 */
public class ClientCredentialsFlow extends TokenStoreApiAuthorizationProvider {
    private final String clientId;
    private final String clientSecret;

    private final BaseSpotifyApi baseSpotifyApi;
    private final AuthorizationTokenApi tokenApi;

    ClientCredentialsFlow(String clientId, String clientSecret, TokenStore tokenStore, HttpUrl tokenApiBaseUrl) {
        super(tokenStore);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        var retrofit = createRetrofit(tokenApiBaseUrl);
        this.baseSpotifyApi = new BaseSpotifyApi(retrofit);
        this.tokenApi = retrofit.create(AuthorizationTokenApi.class);
    }

    public ClientCredentialsFlow(String clientId, String clientSecret) {
        this(clientId, clientSecret, new InMemoryTokenStore(), AuthorizationTokenApi.BASE_URL);
    }

    private static Retrofit createRetrofit(HttpUrl tokenApiBaseUrl) {
        return new Retrofit.Builder()
            .baseUrl(tokenApiBaseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
    }

    public void authorize() throws SpotifyAuthorizationException {
        var authTokensCall = tokenApi.getAuthTokens(createTokensCallAuthHeader(), "client_credentials");
        try {
            var authTokens = baseSpotifyApi.callApiAndReturnBody(authTokensCall);
            tokenStore.storeTokens(authTokens);
        }
        catch (SpotifyApiException e) {
            throw new SpotifyAuthorizationException("Failed to get auth tokens", e);
        }
    }

    private String createTokensCallAuthHeader() {
        String clientInfo = clientId + ":" + clientSecret;
        String base64ClientInfo = Base64.getEncoder().encodeToString(clientInfo.getBytes());
        return "Basic " + base64ClientInfo;
    }
}
