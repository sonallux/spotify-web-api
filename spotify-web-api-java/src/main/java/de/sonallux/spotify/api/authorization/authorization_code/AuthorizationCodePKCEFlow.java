package de.sonallux.spotify.api.authorization.authorization_code;

import de.sonallux.spotify.api.authorization.SpotifyAuthorizationException;
import de.sonallux.spotify.api.authorization.TokenStore;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class AuthorizationCodePKCEFlow extends AbstractAuthorizationCodeFlow {

    AuthorizationCodePKCEFlow(String clientId, String redirectUri, TokenStore tokenStore, HttpUrl tokenApiBaseUrl) {
        super(clientId, redirectUri, tokenStore, tokenApiBaseUrl);
    }

    public AuthorizationCodePKCEFlow(String clientId, String redirectUri, TokenStore tokenStore) {
        super(clientId, redirectUri, tokenStore);
    }

    public AuthorizationCodePKCEFlow(String clientId, String redirectUri) {
        super(clientId, redirectUri);
    }

    private static Retrofit createRetrofit(HttpUrl tokenApiBaseUrl) {
        return new Retrofit.Builder()
            .baseUrl(tokenApiBaseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
    }

    public AuthorizationCodeUriBuilder createAuthorizationUri(String codeChallenge) {
        return new AuthorizationCodePKCEUriBuilder(clientId, redirectUri, codeChallenge);
    }

    public void exchangeAuthorizationCode(AuthorizationResponse authResponse, String codeVerifier) throws SpotifyAuthorizationException{
        if (!authResponse.isSuccess()) {
            throw new SpotifyAuthorizationException("Authorization failed: " + authResponse.getState());
        }

        var tokensCall = tokenApi
            .getTokensFromAuthorizationCodePKCE(clientId, "authorization_code", authResponse.getCode(), redirectUri, codeVerifier);
        executeAuthTokensCall(tokensCall);
    }

    @Override
    public boolean refreshAccessToken() {
        var tokens = tokenStore.loadTokens();
        if (tokens == null || tokens.getRefreshToken() == null) {
            return false;
        }

        var tokensCall = tokenApi
            .getTokensFromRefreshTokenPKCE(clientId, "refresh_token", tokens.getRefreshToken());
        try {
            executeAuthTokensCall(tokensCall);
            return true;
        }
        catch (SpotifyAuthorizationException ignore) {
            return false;
        }
    }
}
