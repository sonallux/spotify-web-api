package de.sonallux.spotify.api.authorization.client_credentials;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.authorization.AuthTokens;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;

@RequiredArgsConstructor
class AuthorizationTokenApi {
    static final HttpUrl BASE_URL = HttpUrl.get("https://accounts.spotify.com");
    private static final TypeReference<AuthTokens> AUTH_TOKENS_TYPE = new TypeReference<>() {};

    private final ApiClient apiClient;

    ApiCall<AuthTokens> getAuthTokens(String authHeaderValue, String grantType) {
        var request = new Request("POST", "/api/token")
            .addHeaderParameter("Authorization", authHeaderValue)
            .addFormUrlEncodedField("grant_type", grantType);
        return apiClient.createApiCall(request, AUTH_TOKENS_TYPE);
    }
}
