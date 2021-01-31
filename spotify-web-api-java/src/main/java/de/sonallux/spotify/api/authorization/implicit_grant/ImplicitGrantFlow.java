package de.sonallux.spotify.api.authorization.implicit_grant;

import de.sonallux.spotify.api.authorization.*;
import de.sonallux.spotify.api.util.TextUtil;
import okhttp3.HttpUrl;

/**
 * Implements the
 * <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#implicit-grant-flow">
 * Implicit Grant Flow</a>
 */
public class ImplicitGrantFlow extends TokenStoreApiAuthorizationProvider {
    private final String clientId;
    private final String redirectUri;

    ImplicitGrantFlow(String clientId, String redirectUri, TokenStore tokenStore) {
        super(tokenStore);
        this.clientId = clientId;
        this.redirectUri = redirectUri;
    }

    public ImplicitGrantFlow(String clientId, String redirectUri) {
        this(clientId, redirectUri, new InMemoryTokenStore());
    }

    public AuthorizationUrlBuilder createAuthorizationUrl() {
        return new AuthorizationUrlBuilder(clientId, redirectUri, "token");
    }

    public AuthorizationRedirectResponse<RedirectResponseBody> parseAuthorizationRedirectResponse(String url) {
        var parsedHttpUrl = HttpUrl.get(url);

        // If the user grants access, the final URL will contain a hash fragment with the data encoded as a query string.
        var fragment = parsedHttpUrl.fragment();
        if (TextUtil.hasText(fragment)) {
            parsedHttpUrl = parsedHttpUrl.newBuilder().query(fragment).fragment(null).build();
        }

        return AuthorizationRedirectResponse.parse(parsedHttpUrl, httpUrl -> {
            var accessToken = httpUrl.queryParameter("access_token");
            var tokenType = httpUrl.queryParameter("token_type");
            var expiresIn = httpUrl.queryParameter("expires_in");
            if (TextUtil.hasText(accessToken) && TextUtil.hasText(tokenType) && TextUtil.hasText(expiresIn)) {
                return new RedirectResponseBody(accessToken, tokenType, Integer.parseInt(expiresIn));
            }
            return null;
        });
    }

    public void useResponse(AuthorizationRedirectResponse<RedirectResponseBody> response) {
        if (!response.isSuccess()) {
            return;
        }
        var body = response.getBody();
        var authTokens = AuthTokens.builder()
            .accessToken(body.getAccessToken())
            .tokenType(body.getTokenType())
            .expiresIn(body.getExpiresIn())
            .build();
        tokenStore.storeTokens(authTokens);
    }
}
