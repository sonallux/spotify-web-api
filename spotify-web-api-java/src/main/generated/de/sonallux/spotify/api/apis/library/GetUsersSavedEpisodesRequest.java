package de.sonallux.spotify.api.apis.library;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-saved-episodes">Get User's Saved Episodes request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-library-read</code>
 *
 * <h3>Response</h3>
 * <ul>
 * <li>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-savedepisodeobject">saved episode objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</li>
 * <li>Trying to get more than 2000 episodes for a user will return results for 2000 episodes. Only the 2000 returned episodes are sorted. This limitation will be removed in the near future.</li>
 * </ul>
 */
public class GetUsersSavedEpisodesRequest {
    private static final TypeReference<Paging<SavedEpisode>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get User's Saved Episodes request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetUsersSavedEpisodesRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me/episodes")
        ;
    }

    /**
     * <p>An <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>. If a country code is specified, only episodes that are available in that market will be returned.<br>If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br><em>Note: If neither market or user country are provided, the content is considered unavailable for the client.</em><br>Users can view the country that is associated with their account in the <a href="https://www.spotify.com/se/account/overview/">account settings</a>.</p>
     */
    public GetUsersSavedEpisodesRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * <p>The maximum number of objects to return. Default: 20. Minimum: 1. Maximum: 50.</p>
     */
    public GetUsersSavedEpisodesRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first object to return. Default: 0 (i.e., the first object). Use with <code>limit</code> to get the next set of objects.</p>
     */
    public GetUsersSavedEpisodesRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Paging<SavedEpisode>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
