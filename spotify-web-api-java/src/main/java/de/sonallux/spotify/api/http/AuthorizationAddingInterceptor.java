package de.sonallux.spotify.api.http;

import de.sonallux.spotify.api.authorization.ApiAuthorizationProvider;
import de.sonallux.spotify.api.util.TextUtil;
import lombok.AllArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@AllArgsConstructor
public class AuthorizationAddingInterceptor implements Interceptor {
    private final ApiAuthorizationProvider authProvider;

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        var request = chain.request();
        var authorizationHeaderValue = authProvider.getAuthorizationHeaderValue();
        if (TextUtil.hasText(authorizationHeaderValue)) {
            request = request.newBuilder()
                .addHeader("Authorization", authorizationHeaderValue)
                .build();
        }
        return chain.proceed(request);
    }
}
