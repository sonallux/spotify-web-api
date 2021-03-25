package de.sonallux.spotify.api.apis.usersprofile;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-profile">Get a User's Profile request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-publicuserobject">user object</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. If a user with that user_id doesn't exist, the status code is <code>404</code> NOT FOUND.</p>
 */
public class GetUsersProfileRequest {
    private static final TypeReference<PublicUser> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get a User's Profile request</h3>
     * @param apiClient <p>The API client</p>
     * @param userId <p>The user's <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify user ID</a>.</p>
     */
    public GetUsersProfileRequest(ApiClient apiClient, String userId) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/users/{user_id}")
            .addPathParameter("user_id", String.valueOf(userId))
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<PublicUser> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
