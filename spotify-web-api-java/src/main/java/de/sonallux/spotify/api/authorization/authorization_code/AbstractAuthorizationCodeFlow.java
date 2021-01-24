package de.sonallux.spotify.api.authorization.authorization_code;

import de.sonallux.spotify.api.BaseSpotifyApi;
import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.authorization.*;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

abstract class AbstractAuthorizationCodeFlow implements ApiAuthorizationProvider {
    final String clientId;
    final String redirectUri;

    final BaseSpotifyApi baseSpotifyApi;
    final TokenStore tokenStore;
    final AuthorizationCodeTokenApi tokenApi;

    AbstractAuthorizationCodeFlow(String clientId, String redirectUri, TokenStore tokenStore, HttpUrl tokenApiBaseUrl) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.tokenStore = tokenStore;
        var retrofit = createRetrofit(tokenApiBaseUrl);
        this.baseSpotifyApi = new BaseSpotifyApi(retrofit);
        this.tokenApi = retrofit.create(AuthorizationCodeTokenApi.class);
    }

    AbstractAuthorizationCodeFlow(String clientId, String redirectUri, TokenStore tokenStore) {
        this(clientId, redirectUri, tokenStore, AuthorizationCodeTokenApi.BASE_URL);
    }

    AbstractAuthorizationCodeFlow(String clientId, String redirectUri) {
        this(clientId, redirectUri, new InMemoryTokenStore());
    }

    private static Retrofit createRetrofit(HttpUrl tokenApiBaseUrl) {
        return new Retrofit.Builder()
            .baseUrl(tokenApiBaseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
    }

    @Override
    public String getAuthorizationHeaderValue() {
        var tokens = tokenStore.loadTokens();
        if (tokens == null || tokens.getAccessToken() == null || tokens.getTokenType() == null) {
            return null;
        }

        return tokens.getTokenType() + " " + tokens.getAccessToken();
    }

    void executeAuthTokensCall(Call<AuthTokens> authTokensCall) throws SpotifyAuthorizationException {
        try {
            AuthTokens authTokens = baseSpotifyApi.callApiAndReturnBody(authTokensCall);
            tokenStore.storeTokens(authTokens);
        }
        catch (SpotifyApiException e) {
            throw new SpotifyAuthorizationException("Failed to get auth tokens", e);
        }
    }
}
