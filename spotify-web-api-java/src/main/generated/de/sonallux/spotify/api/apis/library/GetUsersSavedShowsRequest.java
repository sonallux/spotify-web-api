package de.sonallux.spotify.api.apis.library;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-saved-shows">Get User's Saved Shows request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-library-read</code>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-savedshowobject">saved show objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format.
 * If the current user has no shows saved, the response will be an empty array. If a show is unavailable in the given <code>market</code> it is filtered out. The <code>total</code> field in the paging object represents the number of all items, filtered or not, and thus might be larger than the actual total number of observable items.
 * On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#error-details">error object</a>.</p>
 */
public class GetUsersSavedShowsRequest {
    private static final TypeReference<Paging<SavedShow>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get User's Saved Shows request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetUsersSavedShowsRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me/shows")
        ;
    }

    /**
     * <p>The maximum number of shows to return. Default: 20. Minimum: 1. Maximum: 50</p>
     */
    public GetUsersSavedShowsRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first show to return. Default: 0 (the first object). Use with limit to get the next set of shows.</p>
     */
    public GetUsersSavedShowsRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Paging<SavedShow>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
