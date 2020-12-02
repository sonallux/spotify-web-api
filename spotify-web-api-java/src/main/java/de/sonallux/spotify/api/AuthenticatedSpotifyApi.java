package de.sonallux.spotify.api;

import de.sonallux.spotify.api.authentication.AuthTokens;
import de.sonallux.spotify.api.authentication.SpotifyAuthApi;
import de.sonallux.spotify.api.util.TextUtils;
import okhttp3.*;
import retrofit2.Call;

import java.io.IOException;

public class AuthenticatedSpotifyApi extends SpotifyApi {
    private final AuthenticationProvider authProvider;
    private final SpotifyAuthApi authApi;

    public AuthenticatedSpotifyApi(AuthenticationProvider authProvider, HttpUrl baseUrlWebApi, HttpUrl baseUrlAuthApi) {
        super(createOkHttpClient(authProvider), baseUrlWebApi);
        this.authProvider = authProvider;
        this.authApi = new SpotifyAuthApi(authProvider.getClientId(), authProvider.getClientSecret(), baseUrlAuthApi);
    }

    public AuthenticatedSpotifyApi(AuthenticationProvider authProvider) {
        super(createOkHttpClient(authProvider));
        this.authProvider = authProvider;
        this.authApi = new SpotifyAuthApi(authProvider.getClientId(), authProvider.getClientSecret());
    }

    private static OkHttpClient createOkHttpClient(AuthenticationProvider authProvider) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationInterceptor(authProvider))
                .build();
    }

    public SpotifyAuthApi getAuthApi() {
        return authApi;
    }

    /**
     * Request a access-token and refresh-token from an authorization code.
     * The tokens are passed to {@link AuthenticationProvider#onNewAuthTokens(AuthTokens)}.
     *
     * @param authCode    the authorization code returned form an authorization request
     * @param redirectUri the redirect URI used for the authorization request
     * @return <code>true</code> if new tokens were requested successfully, <code>false</code> otherwise
     */
    public boolean getAuthTokensFromAuthCode(String authCode, String redirectUri) {
        Call<AuthTokens> call = authApi.getAuthTokensFromAuthCode(authCode, redirectUri);
        return executeAuthTokensCall(call);
    }

    /**
     * Request a access-token and refresh-token from an existing refresh code.
     * The tokens are passed to {@link AuthenticationProvider#onNewAuthTokens(AuthTokens)}.
     *
     * @return <code>true</code> if new tokens were requested successfully, <code>false</code> otherwise
     */
    private boolean getAuthTokensFromRefreshToken() {
        String refreshToken = authProvider.getRefreshToken();
        if (TextUtils.isEmpty(refreshToken)) {
            return false;
        }
        Call<AuthTokens> call = authApi.getAuthTokensFromRefreshToken(refreshToken);
        return executeAuthTokensCall(call);
    }

    private boolean executeAuthTokensCall(Call<AuthTokens> authTokensCall) {
        try {
            AuthTokens authTokens = callApiAndReturnBody(authTokensCall);
            authProvider.onNewAuthTokens(authTokens);

            return true;
        }
        catch (SpotifyApiException e) {
            authProvider.onErrorGettingAuthTokens(e);
            return false;
        }
    }

    @Override
    public <T> retrofit2.Response<T> callApi(Call<T> call) throws SpotifyApiException {
        try {
            retrofit2.Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response;
            }
            else if (response.code() == 401) { //Unauthorized
                if (getAuthTokensFromRefreshToken()) {
                    return call.clone().execute();
                }
            }
            return response;
        }
        catch (IOException e) {
            throw new SpotifyApiException("Failed to make api call", e);
        }
    }

    private static class AuthenticationInterceptor implements Interceptor {
        private final AuthenticationProvider authProvider;

        AuthenticationInterceptor(AuthenticationProvider authProvider) {
            this.authProvider = authProvider;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String accessToken = authProvider.getAccessToken();
            if (accessToken != null) {
                request = request.newBuilder()
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .build();
            }
            return chain.proceed(request);
        }
    }
}
