package de.jsone_studios.spotify.api.authentication;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Base64;
import java.util.List;

public class SpotifyAuthApi
{
    public static final String SPOTIFY_ACCOUNTS_ENDPOINT = "https://accounts.spotify.com";

    private final String clientId;
    private final String clientSecret;

    private final AuthSpotifyService authService;

    public SpotifyAuthApi(String clientId, String clientSecret, HttpUrl baseUrl)
    {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authService = createAuthService(baseUrl);
    }

    public SpotifyAuthApi(String clientId, String clientSecret, String baseUrl)
    {
        this(clientId, clientSecret, HttpUrl.get(baseUrl));
    }

    public SpotifyAuthApi(String clientId, String clientSecret)
    {
        this(clientId, clientSecret, SPOTIFY_ACCOUNTS_ENDPOINT);
    }

    private AuthSpotifyService createAuthService(HttpUrl baseUrl)
    {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
        return retrofit.create(AuthSpotifyService.class);
    }

    public HttpUrl getAuthorizeUrl(String redirectUri, String state, List<Scope> scopes) {
        return getAuthorizeUrl("code", redirectUri, state, scopes);
    }

    public HttpUrl getAuthorizeUrl(String responseType, String redirectUri, String state, List<Scope> scopes)
    {
        String scope;
        if (scopes == null || scopes.size() == 0)
        {
            scope = "";
        }
        else
        {
            scope = scopes.stream().map(Scope::getScope).reduce((scope1, scope2) -> scope1 + " " + scope2).get();
        }

        return new HttpUrl.Builder()
            .scheme("https")
            .host("accounts.spotify.com")
            .addPathSegment("authorize")
            .addQueryParameter("client_id", clientId)
            .addQueryParameter("response_type", responseType)
            .addEncodedQueryParameter("redirect_uri", redirectUri)
            .addQueryParameter("scope", scope)
            .addQueryParameter("state", state)
            .build();
    }

    public Call<AuthTokens> getAuthTokensFromAuthCode(String code, String redirectUri)
    {
        String authHeader = createAuthorizationHeaderValue();
        return authService.getAuthTokens(authHeader, "authorization_code", code, redirectUri);
    }

    public Call<AuthTokens> getAuthTokensFromRefreshToken(String refreshToken)
    {
        String authHeader = createAuthorizationHeaderValue();
        return authService.getAuthTokens(authHeader, "refresh_token", refreshToken);
    }

    private String createAuthorizationHeaderValue()
    {
        String clientInfo = clientId + ":" + clientSecret;
        String base64ClientInfo = Base64.getEncoder().encodeToString(clientInfo.getBytes());
        return "Basic " + base64ClientInfo;
    }

    public RedirectResponse parseRedirectResponse(String url)
    {
        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null)
        {
            return null;
        }
        String state = httpUrl.queryParameter("state");
        String error = httpUrl.queryParameter("error");
        String authCode = httpUrl.queryParameter("code");

        return new RedirectResponse(state, error, authCode);
    }

    public class RedirectResponse
    {
        private final String state;
        private final String error;
        private final String authCode;

        private RedirectResponse(String state, String error, String authCode)
        {
            this.state = state;
            this.error = error;
            this.authCode = authCode;
        }

        public String getState()
        {
            return state;
        }

        public String getError()
        {
            return error;
        }

        public String getAuthCode()
        {
            return authCode;
        }
    }
}
