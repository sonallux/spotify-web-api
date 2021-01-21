package de.sonallux.spotify.api.authorization.flows;

import de.sonallux.spotify.api.BaseSpotifyApi;
import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.authorization.*;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Arrays;
import java.util.Base64;

public class AuthorizationCodeFlow implements ApiAuthorizationProvider {
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;

    private final BaseSpotifyApi baseSpotifyApi;
    private final TokenStore tokenStore;
    private final AuthorizationCodeFlowTokenApi tokenApi;

    AuthorizationCodeFlow(String clientId, String clientSecret, String redirectUri, TokenStore tokenStore, HttpUrl tokenApiBaseUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.tokenStore = tokenStore;
        var retrofit = createRetrofit(tokenApiBaseUrl);
        this.baseSpotifyApi = new BaseSpotifyApi(retrofit);
        this.tokenApi = retrofit.create(AuthorizationCodeFlowTokenApi.class);
    }

    public AuthorizationCodeFlow(String clientId, String clientSecret, String redirectUri, TokenStore tokenStore) {
        this(clientId, clientSecret, redirectUri, tokenStore, AuthorizationCodeFlowTokenApi.BASE_URL);
    }

    public AuthorizationCodeFlow(String clientId, String clientSecret, String redirectUri) {
        this(clientId, clientSecret, redirectUri, new InMemoryTokenStore());
    }

    private static Retrofit createRetrofit(HttpUrl tokenApiBaseUrl) {
        return new Retrofit.Builder()
            .baseUrl(tokenApiBaseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
    }

    public AuthorizationUriBuilder createAuthorizationUri() {
        return new AuthorizationUriBuilder(clientId, redirectUri);
    }

    public boolean onAuthorizationResponse(String url) {
        var httpUrl = HttpUrl.parse(url);
        if (httpUrl == null) {
            return false;
        }

        var error = httpUrl.queryParameter("error");
        if (error != null) {
            //TODO: somehow return content of error to caller
            return false;
        }
        var state = httpUrl.queryParameter("state"); //TODO: State should be verified. Maybe check how spring-security is doing that
        var code = httpUrl.queryParameter("code");

        var tokensCall = tokenApi
            .getTokensFromAuthorizationCode(createTokensCallAuthHeader(), "authorization_code", code, redirectUri);
        return executeAuthTokensCall(tokensCall);
    }

    @Override
    public String getAuthorizationHeaderValue() {
        var tokens = tokenStore.loadTokens();
        if (tokens == null || tokens.getAccessToken() == null || tokens.getTokenType() == null) {
            return null;
        }

        return tokens.getTokenType() + " " + tokens.getAccessToken();
    }

    @Override
    public boolean refreshAccessToken() {
        var tokens = tokenStore.loadTokens();
        if (tokens == null || tokens.getRefreshToken() == null) {
            return false;
        }

        var tokensCall = tokenApi
            .getTokensFromRefreshToken(createTokensCallAuthHeader(), "refresh_token", tokens.getRefreshToken());
        return executeAuthTokensCall(tokensCall);
    }

    private String createTokensCallAuthHeader() {
        String clientInfo = clientId + ":" + clientSecret;
        String base64ClientInfo = Base64.getEncoder().encodeToString(clientInfo.getBytes());
        return "Basic " + base64ClientInfo;
    }

    private boolean executeAuthTokensCall(Call<AuthTokens> authTokensCall) {
        try {
            AuthTokens authTokens = baseSpotifyApi.callApiAndReturnBody(authTokensCall);
            tokenStore.storeTokens(authTokens);
            return true;
        }
        catch (SpotifyApiException e) {
            //TODO: propagate error to caller
            return false;
        }
    }

    public static class AuthorizationUriBuilder {
        private final HttpUrl.Builder builder;

        private AuthorizationUriBuilder(String clientId, String redirectUri) {
            builder = new HttpUrl.Builder()
                .scheme("https")
                .host("accounts.spotify.com")
                .addPathSegment("authorize")
                .addQueryParameter("client_id", clientId)
                .addQueryParameter("response_type", "code")
                .addQueryParameter("redirect_uri", redirectUri);
        }

        public AuthorizationUriBuilder state(String state) {
            builder.addQueryParameter("state", state);
            return this;
        }

        public AuthorizationUriBuilder scopes(Scope... scopes) {
            if (scopes.length == 0) {
                return this;
            }
            var scopeString = Arrays.stream(scopes)
                .map(Scope::getName)
                .reduce((scope1, scope2) -> scope1 + " " + scope2)
                .orElse("");

            builder.addQueryParameter("scope", scopeString);
            return this;
        }

        public AuthorizationUriBuilder showDialog(boolean showDialog) {
            builder.addQueryParameter("show_dialog", String.valueOf(showDialog));
            return this;
        }

        public HttpUrl build() {
            return builder.build();
        }
    }
}
