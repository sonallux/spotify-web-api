package de.sonallux.spotify.api.apis.playlists;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-upload-custom-playlist-cover">Upload a Custom Playlist Cover Image request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>ugc-image-upload, playlist-modify-public, playlist-modify-private</code>
 *
 * <h3>Notes</h3>
 * <p>The request should contain a Base64 encoded JPEG image data, maximum payload size is 256 KB.</p>
 * <p><strong>Rate Limiting:</strong> If you get status code <code>429</code>, it means that you have sent too many requests.
 * If this happens, have a look in the <code>Retry-After</code> header, where you will see a number displayed. This is the amount of seconds that you need to wait, before you can retry sending your requests.</p>
 *
 * <h3>Response</h3>
 * <p>If you get status code <code>429</code>, it means that you have sent too many requests.
 * If this happens, have a look in the <code>Retry-After</code> header, where you will see a number displayed.
 * This is the amount of seconds that you need to wait, before you can retry sending your requests.</p>
 */
public class UploadCustomPlaylistCoverRequest {
    private static final TypeReference<Void> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Upload a Custom Playlist Cover Image request</h3>
     * @param apiClient <p>The API client</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     */
    public UploadCustomPlaylistCoverRequest(ApiClient apiClient, String playlistId) {
        this.apiClient = apiClient;
        this.request = new Request("PUT", "/playlists/{playlist_id}/images")
            .addPathParameter("playlist_id", String.valueOf(playlistId))
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Void> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
