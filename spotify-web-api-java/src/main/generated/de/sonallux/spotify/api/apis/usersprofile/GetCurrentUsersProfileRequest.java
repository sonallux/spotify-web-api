package de.sonallux.spotify.api.apis.usersprofile;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-current-users-profile">Get Current User's Profile request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-read-email, user-read-private</code>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-privateuserobject">user object</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. When requesting fields that you don't have the user's authorization to access, it will return error <code>403</code> Forbidden.</p>
 * <p><strong>Important!</strong> If the <code>user-read-email</code> scope is authorized, the returned JSON will include the email address that was entered when the user created their Spotify account. <strong>This email address is unverified</strong>; do not assume that the email address belongs to the user.</p>
 */
public class GetCurrentUsersProfileRequest {
    private static final TypeReference<PrivateUser> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get Current User's Profile request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetCurrentUsersProfileRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me")
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<PrivateUser> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
