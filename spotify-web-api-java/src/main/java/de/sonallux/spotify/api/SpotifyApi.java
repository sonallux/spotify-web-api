package de.sonallux.spotify.api;

import de.sonallux.spotify.api.authorization.ApiAuthorizationProvider;
import lombok.AllArgsConstructor;
import okhttp3.*;
import retrofit2.Call;

import java.io.IOException;

public class SpotifyApi extends SpotifyWebApi {
    private final ApiAuthorizationProvider authProvider;

    public SpotifyApi(ApiAuthorizationProvider authProvider) {
        super(createOkHttpClient(authProvider));
        this.authProvider = authProvider;
    }

    SpotifyApi(ApiAuthorizationProvider authProvider, HttpUrl baseUrl) {
        super(createOkHttpClient(authProvider), baseUrl);
        this.authProvider = authProvider;
    }

    private static OkHttpClient createOkHttpClient(ApiAuthorizationProvider authProvider) {
        return new OkHttpClient.Builder()
            .addInterceptor(new AuthorizationAddingInterceptor(authProvider))
            .build();
    }

    @Override
    public <T> retrofit2.Response<T> callApi(Call<T> call) throws SpotifyApiException {
        try {
            retrofit2.Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response;
            }
            else if (response.code() == 401) { //Unauthorized
                if (authProvider.refreshAccessToken()) {
                    return call.clone().execute();
                }
            }
            return response;
        }
        catch (IOException e) {
            throw new SpotifyApiException("Failed to make api call", e);
        }
    }

    @AllArgsConstructor
    private static class AuthorizationAddingInterceptor implements Interceptor {
        private final ApiAuthorizationProvider authProvider;

        @Override
        public Response intercept(Chain chain) throws IOException {
            var request = chain.request();
            var authorizationHeaderValue = authProvider.getAuthorizationHeaderValue();
            if (authorizationHeaderValue != null) {
                request = request.newBuilder()
                    .addHeader("Authorization", authorizationHeaderValue)
                    .build();
            }
            return chain.proceed(request);
        }
    }
}
