package de.sonallux.spotify.api.authorization.authorization_code;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.authorization.AuthTokens;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;

@RequiredArgsConstructor
class AuthorizationCodeTokenApi {
    static final HttpUrl BASE_URL = HttpUrl.get("https://accounts.spotify.com");
    private static final TypeReference<AuthTokens> AUTH_TOKENS_TYPE = new TypeReference<>() {};

    private final ApiClient apiClient;

    ApiCall<AuthTokens> getTokensFromAuthorizationCode(String authHeaderValue, String grantType, String code, String redirectUri) {
        var request = new Request("POST", "/api/token")
            .addHeaderParameter("Authorization", authHeaderValue)
            .addFormUrlEncodedField("grant_type", grantType)
            .addFormUrlEncodedField("code", code)
            .addFormUrlEncodedField("redirect_uri", redirectUri);
        return apiClient.createApiCall(request, AUTH_TOKENS_TYPE);
    }

    ApiCall<AuthTokens> getTokensFromRefreshToken(String authHeaderValue, String grantType, String refreshToken) {
        var request = new Request("POST", "/api/token")
            .addHeaderParameter("Authorization", authHeaderValue)
            .addFormUrlEncodedField("grant_type", grantType)
            .addFormUrlEncodedField("refresh_token", refreshToken);
        return apiClient.createApiCall(request, AUTH_TOKENS_TYPE);
    }

    ApiCall<AuthTokens> getTokensFromAuthorizationCodePKCE(String clientId, String grantType, String code, String redirectUri, String codeVerifier) {
        var request = new Request("POST", "/api/token")
            .addFormUrlEncodedField("client_id", clientId)
            .addFormUrlEncodedField("grant_type", grantType)
            .addFormUrlEncodedField("code", code)
            .addFormUrlEncodedField("redirect_uri", redirectUri)
            .addFormUrlEncodedField("code_verifier", codeVerifier);
        return apiClient.createApiCall(request, AUTH_TOKENS_TYPE);
    }

    ApiCall<AuthTokens> getTokensFromRefreshTokenPKCE(String clientId, String grantType, String refreshToken) {
        var request = new Request("POST", "/api/token")
            .addFormUrlEncodedField("client_id", clientId)
            .addFormUrlEncodedField("grant_type", grantType)
            .addFormUrlEncodedField("refresh_token", refreshToken);
        return apiClient.createApiCall(request, AUTH_TOKENS_TYPE);
    }
}
