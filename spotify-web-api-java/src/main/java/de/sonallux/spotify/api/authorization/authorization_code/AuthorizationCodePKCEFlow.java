package de.sonallux.spotify.api.authorization.authorization_code;

import de.sonallux.spotify.api.authorization.AuthorizationUrlBuilder;
import de.sonallux.spotify.api.authorization.AuthorizationRedirectResponse;
import de.sonallux.spotify.api.authorization.SpotifyAuthorizationException;
import de.sonallux.spotify.api.authorization.TokenStore;
import de.sonallux.spotify.api.util.TextUtil;
import okhttp3.HttpUrl;

/**
 * Implements the
 * <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#authorization-code-flow-with-proof-key-for-code-exchange-pkce">
 * Authorization Code Flow with Proof Key for Code Exchange (PKCE)</a>
 */
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

    public AuthorizationUrlBuilder createAuthorizationUrl(String codeChallenge) {
        return new AuthorizationCodePKCEUrlBuilder(clientId, redirectUri, codeChallenge);
    }

    public void exchangeAuthorizationCode(AuthorizationRedirectResponse<String> authResponse, String codeVerifier) throws SpotifyAuthorizationException{
        if (!authResponse.isSuccess()) {
            throw new SpotifyAuthorizationException("Authorization failed: " + authResponse.getState());
        }

        var tokensCall = tokenApi
            .getTokensFromAuthorizationCodePKCE(clientId, "authorization_code", authResponse.getBody(), redirectUri, codeVerifier);
        executeAuthTokensCall(tokensCall);
    }

    @Override
    public boolean refreshAccessToken() {
        var tokens = tokenStore.loadTokens();
        if (tokens == null || !TextUtil.hasText(tokens.getRefreshToken())) {
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
