package de.sonallux.spotify.api.http;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.authorization.ApiAuthorizationProvider;
import lombok.AllArgsConstructor;

import java.io.IOException;

public class AuthorizedApiCall<T> extends ApiCall<T> {
    private final ApiAuthorizationProvider authProvider;

    public AuthorizedApiCall(ApiAuthorizationProvider authProvider, ApiClient apiClient, Request request, TypeReference<T> responseType) {
        super(apiClient, request, responseType);
        this.authProvider = authProvider;
    }

    @Override
    protected Response<T> parseResponse(okhttp3.Response rawResponse) throws IOException {
        if (rawResponse.code() == 401) { //Unauthorized
            if (authProvider.refreshAccessToken()) {
                rawResponse = getRawCall().clone().execute();
            }
        }

        return super.parseResponse(rawResponse);
    }

    @AllArgsConstructor
    public static class Factory extends ApiCall.Factory {
        private final ApiAuthorizationProvider authProvider;

        @Override
        public <T> ApiCall<T> createApiCall(ApiClient apiClient, Request request, TypeReference<T> responseType) {
            return new AuthorizedApiCall<>(authProvider, apiClient, request, responseType);
        }
    }
}
