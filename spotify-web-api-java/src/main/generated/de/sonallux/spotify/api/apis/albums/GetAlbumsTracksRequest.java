package de.sonallux.spotify.api.apis.albums;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-an-albums-tracks">Get an Album's Tracks request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is 200 OK and the response body contains an album object in JSON format. On error, the header status code is an error code and the response body contains an error object.</p>
 */
public class GetAlbumsTracksRequest {
    private static final TypeReference<Paging<SimplifiedTrack>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get an Album's Tracks request</h3>
     * @param apiClient <p>The API client</p>
     * @param id <p>The Spotify ID of the album.</p>
     */
    public GetAlbumsTracksRequest(ApiClient apiClient, String id) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/albums/{id}/tracks")
            .addPathParameter("id", String.valueOf(id))
        ;
    }

    /**
     * <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string from_token. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>.</p>
     */
    public GetAlbumsTracksRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * <p>The maximum number of tracks to return. Default: 20. Minimum: 1. Maximum: 50.</p>
     */
    public GetAlbumsTracksRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first track to return. Default: 0 (the first object). Use with limit to get the next set of tracks.</p>
     */
    public GetAlbumsTracksRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Paging<SimplifiedTrack>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
