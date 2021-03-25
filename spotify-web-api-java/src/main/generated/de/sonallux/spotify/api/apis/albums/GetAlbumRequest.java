package de.sonallux.spotify.api.apis.albums;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-an-album">Get an Album request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is 200 OK and the response body contains an album object in JSON format. On error, the header status code is an error code and the response body contains an error object.</p>
 */
public class GetAlbumRequest {
    private static final TypeReference<Album> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get an Album request</h3>
     * @param apiClient <p>The API client</p>
     * @param id <p>The Spotify ID of the album.</p>
     */
    public GetAlbumRequest(ApiClient apiClient, String id) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/albums/{id}")
            .addPathParameter("id", String.valueOf(id))
        ;
    }

    /**
     * <p>The market you'd like to request. Synonym for <code>country</code>.</p>
     */
    public GetAlbumRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Album> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
