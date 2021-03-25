package de.sonallux.spotify.api.apis.player;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-recently-played">Get Current User's Recently Played Tracks request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="#play-history-object">play history objects</a> (wrapped in a <a href="#cursor-based-paging-object">cursor-based paging object</a>) in JSON format. The play history items each contain the context the track was played from (e.g. playlist, album), the date and time the track was played, and a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedtrackobject">track object (simplified)</a>. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 * <p>If private session is enabled the response will be a <code>204 NO CONTENT</code> with an empty payload.</p>
 */
public class GetRecentlyPlayedRequest {
    private static final TypeReference<CursorPaging<PlayHistory>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get Current User's Recently Played Tracks request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetRecentlyPlayedRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me/player/recently-played")
        ;
    }

    /**
     * <p>The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.</p>
     */
    public GetRecentlyPlayedRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>A Unix timestamp in milliseconds. Returns all items after (but not including) this cursor position. If <code>after</code> is specified, <code>before</code> must not be specified.</p>
     */
    public GetRecentlyPlayedRequest after(int after) {
        this.request.addQueryParameter("after", String.valueOf(after));
        return this;
    }

    /**
     * <p>A Unix timestamp in milliseconds. Returns all items before (but not including) this cursor position. If <code>before</code> is specified, <code>after</code> must not be specified.</p>
     */
    public GetRecentlyPlayedRequest before(int before) {
        this.request.addQueryParameter("before", String.valueOf(before));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<CursorPaging<PlayHistory>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
