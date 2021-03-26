package de.sonallux.spotify.api.apis.playlists;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-add-tracks-to-playlist">Add Items to a Playlist request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>playlist-modify-public, playlist-modify-private</code>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>201</code> Created. The response body contains a <code>snapshot_id</code> in JSON format. The <code>snapshot_id</code> can be used to identify your playlist version in future requests. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to add an item when you do not have the user's authorization, or when there are more than 10.000 items in the playlist, returns error <code>403</code> Forbidden.</p>
 */
public class AddTracksToPlaylistRequest {
    private static final TypeReference<SnapshotId> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Add Items to a Playlist request</h3>
     * @param apiClient <p>The API client</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param uris <p>A JSON array of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URIs</a> to add. For example: <code>{&quot;uris&quot;: [&quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot;,&quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;, &quot;spotify:episode:512ojhOuo1ktJprKbVcKyQ&quot;]}</code><br>A maximum of 100 items can be added in one request. <em>Note: if the <code>uris</code> parameter is present in the query string, any URIs listed here in the body will be ignored.</em></p>
     */
    public AddTracksToPlaylistRequest(ApiClient apiClient, String playlistId, java.util.List<String> uris) {
        this.apiClient = apiClient;
        this.request = new Request("POST", "/playlists/{playlist_id}/tracks")
            .addPathParameter("playlist_id", String.valueOf(playlistId))
            .addBodyParameter("uris", uris)
        ;
    }

    /**
     * <p>The position to insert the items, a zero-based index. For example, to insert the items in the first position: <code>position=0</code> ; to insert the items in the third position: <code>position=2</code>. If omitted, the items will be appended to the playlist. Items are added in the order they appear in the uris array. For example: <code>{&quot;uris&quot;: [&quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot;,&quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;], &quot;position&quot;: 3}</code></p>
     */
    public AddTracksToPlaylistRequest position(int position) {
        this.request.addBodyParameter("position", position);
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<SnapshotId> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
