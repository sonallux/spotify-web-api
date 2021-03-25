package de.sonallux.spotify.api.authorization.authorization_code;

import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.authorization.*;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.util.TextUtil;
import okhttp3.HttpUrl;

abstract class AbstractAuthorizationCodeFlow extends TokenStoreApiAuthorizationProvider {
    final String clientId;
    final String redirectUri;
    final AuthorizationCodeTokenApi tokenApi;

    AbstractAuthorizationCodeFlow(String clientId, String redirectUri, TokenStore tokenStore, HttpUrl tokenApiBaseUrl) {
        super(tokenStore);
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        var apiClient = createApiClient(tokenApiBaseUrl);
        this.tokenApi = new AuthorizationCodeTokenApi(apiClient);
    }

    AbstractAuthorizationCodeFlow(String clientId, String redirectUri, TokenStore tokenStore) {
        this(clientId, redirectUri, tokenStore, AuthorizationCodeTokenApi.BASE_URL);
    }

    AbstractAuthorizationCodeFlow(String clientId, String redirectUri) {
        this(clientId, redirectUri, new InMemoryTokenStore());
    }

    private static ApiClient createApiClient(HttpUrl tokenApiBaseUrl) {
        return ApiClient.builder()
            .baseUrl(tokenApiBaseUrl)
            .build();
    }

    public AuthorizationRedirectResponse<String> parseAuthorizationRedirectResponse(String url) {
        return AuthorizationRedirectResponse.parse(url, httpUrl -> {
            var code = httpUrl.queryParameter("code");
            if (TextUtil.hasText(code)) {
                return code;
            }
            return null;
        });
    }

    void executeAuthTokensCall(ApiCall<AuthTokens> authTokensCall) throws SpotifyAuthorizationException {
        try {
            var authTokens = authTokensCall.execute();
            tokenStore.storeTokens(authTokens);
        }
        catch (SpotifyApiException e) {
            throw new SpotifyAuthorizationException("Failed to get auth tokens", e);
        }
    }
}
