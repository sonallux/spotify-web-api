package de.sonallux.spotify.api.apis.follow;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-followed">Get User's Followed Artists request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-follow-modify</code>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an <code>artists</code> object.
 * The <code>artists</code> object in turn contains a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-cursorpagingobject">cursor-based paging object</a> of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-artistobject">Artists</a>.
 * On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class GetFollowedRequest {
    private static final TypeReference<FollowingArtists> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get User's Followed Artists request</h3>
     * @param apiClient <p>The API client</p>
     * @param type <p>The ID type: currently only <code>artist</code> is supported.</p>
     */
    public GetFollowedRequest(ApiClient apiClient, String type) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me/following")
            .addQueryParameter("type", String.valueOf(type))
        ;
    }

    /**
     * <p>The last artist ID retrieved from the previous request.</p>
     */
    public GetFollowedRequest after(String after) {
        this.request.addQueryParameter("after", String.valueOf(after));
        return this;
    }

    /**
     * <p>The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.</p>
     */
    public GetFollowedRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<FollowingArtists> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
