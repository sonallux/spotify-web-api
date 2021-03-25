package de.sonallux.spotify.api.apis.playlists;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-playlist-cover">Get a Playlist Cover Image request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the response body contains a list of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-imageobject">image objects</a> in JSON format and the HTTP status code in the response header is <code>200</code> OK<br>
 * On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class GetPlaylistCoverRequest {
    private static final TypeReference<java.util.List<Image>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get a Playlist Cover Image request</h3>
     * @param apiClient <p>The API client</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     */
    public GetPlaylistCoverRequest(ApiClient apiClient, String playlistId) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/playlists/{playlist_id}/images")
            .addPathParameter("playlist_id", String.valueOf(playlistId))
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<java.util.List<Image>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
