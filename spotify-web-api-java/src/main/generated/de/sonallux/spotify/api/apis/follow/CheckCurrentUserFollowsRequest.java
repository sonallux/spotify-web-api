package de.sonallux.spotify.api.apis.follow;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-check-current-user-follows">Get Following State for Artists/Users request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-follow-read</code>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a JSON array of <code>true</code> or <code>false</code> values, in the same order in which the <code>ids</code> were specified.
 * On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class CheckCurrentUserFollowsRequest {
    private static final TypeReference<java.util.List<Boolean>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get Following State for Artists/Users request</h3>
     * @param apiClient <p>The API client</p>
     * @param type <p>The ID type: either <code>artist</code> or <code>user</code>.</p>
     * @param ids <p>A comma-separated list of the artist or the user <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> to check. For example: <code>ids=74ASZWbe4lXaubB36ztrGX,08td7MxkoHQkXnWAYD8d6Q</code>. A maximum of 50 IDs can be sent in one request.</p>
     */
    public CheckCurrentUserFollowsRequest(ApiClient apiClient, String type, String ids) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me/following/contains")
            .addQueryParameter("type", String.valueOf(type))
            .addQueryParameter("ids", String.valueOf(ids))
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<java.util.List<Boolean>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
