package de.sonallux.spotify.api.apis.library;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-saved-albums">Get User's Saved Albums request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-library-read</code>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-savedalbumobject">saved album objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. Each album object is accompanied by a <a href="https://developer.spotify.com/documentation/web-api/#timestamps">timestamp</a> (<code>added_at</code>) to show when it was added. There is also an <strong>etag</strong> in the header that can be used in future <a href="https://developer.spotify.com/documentation/web-api/#conditional-requests">conditional requests</a>.</p>
 * <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class GetUsersSavedAlbumsRequest {
    private static final TypeReference<Paging<SavedAlbum>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get User's Saved Albums request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetUsersSavedAlbumsRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me/albums")
        ;
    }

    /**
     * <p>The maximum number of objects to return. Default: 20. Minimum: 1. Maximum: 50.</p>
     */
    public GetUsersSavedAlbumsRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first object to return. Default: 0 (i.e., the first object). Use with <code>limit</code> to get the next set of objects.</p>
     */
    public GetUsersSavedAlbumsRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>.</p>
     */
    public GetUsersSavedAlbumsRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Paging<SavedAlbum>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
