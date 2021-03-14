package de.sonallux.spotify.api.authorization.authorization_code;

import de.sonallux.spotify.api.BaseSpotifyApi;
import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.authorization.*;
import de.sonallux.spotify.api.util.JacksonConverterFactory;
import de.sonallux.spotify.api.util.TextUtil;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Retrofit;

abstract class AbstractAuthorizationCodeFlow extends TokenStoreApiAuthorizationProvider {
    final String clientId;
    final String redirectUri;

    final BaseSpotifyApi baseSpotifyApi;
    final AuthorizationCodeTokenApi tokenApi;

    AbstractAuthorizationCodeFlow(String clientId, String redirectUri, TokenStore tokenStore, HttpUrl tokenApiBaseUrl) {
        super(tokenStore);
        this.clientId = clientId;
        this.redirectUri = redirectUri;
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
            .addConverterFactory(new JacksonConverterFactory())
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

    void executeAuthTokensCall(Call<AuthTokens> authTokensCall) throws SpotifyAuthorizationException {
        try {
            var authTokens = baseSpotifyApi.callApiAndReturnBody(authTokensCall);
            tokenStore.storeTokens(authTokens);
        }
        catch (SpotifyApiException e) {
            throw new SpotifyAuthorizationException("Failed to get auth tokens", e);
        }
    }
}
