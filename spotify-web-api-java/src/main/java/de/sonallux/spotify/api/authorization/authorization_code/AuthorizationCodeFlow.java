package de.sonallux.spotify.api.authorization.authorization_code;

import de.sonallux.spotify.api.authorization.SpotifyAuthorizationException;
import de.sonallux.spotify.api.authorization.TokenStore;
import okhttp3.HttpUrl;

import java.util.Base64;

public class AuthorizationCodeFlow extends AbstractAuthorizationCodeFlow {
    private final String clientSecret;

    AuthorizationCodeFlow(String clientId, String clientSecret, String redirectUri, TokenStore tokenStore, HttpUrl tokenApiBaseUrl) {
        super(clientId, redirectUri, tokenStore, tokenApiBaseUrl);
        this.clientSecret = clientSecret;
    }

    public AuthorizationCodeFlow(String clientId, String clientSecret, String redirectUri, TokenStore tokenStore) {
        super(clientId, redirectUri, tokenStore);
        this.clientSecret = clientSecret;
    }

    public AuthorizationCodeFlow(String clientId, String clientSecret, String redirectUri) {
        super(clientId, redirectUri);
        this.clientSecret = clientSecret;
    }

    public AuthorizationCodeUriBuilder createAuthorizationUri() {
        return new AuthorizationCodeUriBuilder(clientId, redirectUri);
    }

    public void exchangeAuthorizationCode(AuthorizationResponse authResponse) throws SpotifyAuthorizationException{
        if (!authResponse.isSuccess()) {
            throw new SpotifyAuthorizationException("Authorization failed: " + authResponse.getState());
        }

        var tokensCall = tokenApi
            .getTokensFromAuthorizationCode(createTokensCallAuthHeader(), "authorization_code", authResponse.getCode(), redirectUri);
        executeAuthTokensCall(tokensCall);
    }

    @Override
    public boolean refreshAccessToken() {
        var tokens = tokenStore.loadTokens();
        if (tokens == null || tokens.getRefreshToken() == null) {
            return false;
        }

        var tokensCall = tokenApi
            .getTokensFromRefreshToken(createTokensCallAuthHeader(), "refresh_token", tokens.getRefreshToken());
        try {
            executeAuthTokensCall(tokensCall);
            return true;
        }
        catch (SpotifyAuthorizationException ignore) {
            return false;
        }
    }

    private String createTokensCallAuthHeader() {
        String clientInfo = clientId + ":" + clientSecret;
        String base64ClientInfo = Base64.getEncoder().encodeToString(clientInfo.getBytes());
        return "Basic " + base64ClientInfo;
    }
}
