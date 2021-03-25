package de.sonallux.spotify.api.apis.playlists;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-a-list-of-current-users-playlists">Get a List of Current User's Playlists request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>playlist-read-private, playlist-read-collaborative</code>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedplaylistobject">playlist objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Please note that the access token has to be tied to a user.</p>
 */
public class GetListOfCurrentUsersPlaylistsRequest {
    private static final TypeReference<Paging<SimplifiedPlaylist>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get a List of Current User's Playlists request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetListOfCurrentUsersPlaylistsRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me/playlists")
        ;
    }

    /**
     * <p>'The maximum number of playlists to return. Default: 20. Minimum: 1. Maximum: 50.'</p>
     */
    public GetListOfCurrentUsersPlaylistsRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>'The index of the first playlist to return. Default: 0 (the first object). Maximum offset: 100.000. Use with <code>limit</code> to get the next set of playlists.'</p>
     */
    public GetListOfCurrentUsersPlaylistsRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Paging<SimplifiedPlaylist>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
