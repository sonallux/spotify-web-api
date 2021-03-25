package de.sonallux.spotify.api.apis.personalization;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-top-artists-and-tracks">Get a User's Top Tracks request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-top-read</code>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a> of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#track-object-full">Tracks</a>. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class GetUsersTopTracksRequest {
    private static final TypeReference<Paging<Track>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get a User's Top Tracks request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetUsersTopTracksRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me/top/tracks")
        ;
    }

    /**
     * <p>Over what time frame the affinities are computed. Valid values: <code>long_term</code> (calculated from several years of data and including all new data as it becomes available), <code>medium_term</code> (approximately last 6 months), <code>short_term</code> (approximately last 4 weeks). Default: <code>medium_term</code></p>
     */
    public GetUsersTopTracksRequest timeRange(String timeRange) {
        this.request.addQueryParameter("time_range", String.valueOf(timeRange));
        return this;
    }

    /**
     * <p>The number of entities to return. Default: 20. Minimum: 1. Maximum: 50. For example: <code>limit=2</code></p>
     */
    public GetUsersTopTracksRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first entity to return. Default: 0 (i.e., the first track). Use with limit to get the next set of entities.</p>
     */
    public GetUsersTopTracksRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Paging<Track>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
