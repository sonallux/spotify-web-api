package de.sonallux.spotify.api.apis.playlists;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-remove-tracks-playlist">Remove Items from a Playlist request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>playlist-modify-public, playlist-modify-private</code>
 *
 * <h3>Notes</h3>
 * <h3>Frequently Asked Questions:</h3>
 * <ul>
 * <li>
 * <p><strong>Is it possible to delete a playlist?</strong>
 * No, it isn't. The reason there is no endpoint for this is explained in our Working With Playlists Guide in the section <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/#following-and-unfollowing-a-playlist">Following and Unfollowing a Playlist</a>.</p>
 * </li>
 * <li>
 * <p><strong>Can I use <code>X-HTTP-Method-Override</code> or similar to send a DELETE request overriding the HTTP verb?</strong>
 * Not at the moment, the delete operation needs to be specified through a DELETE request.</p>
 * </li>
 * </ul>
 *
 * <h3>Response</h3>
 * <p>On success, the response body contains a <code>snapshot_id</code> in JSON format
 * and the HTTP status code in the response header is <code>200</code> OK. The <code>snapshot_id</code>
 * can be used to identify your playlist version in future requests.</p>
 * <p>On error, the header status code is an <a href="https://developer.spotify.com/spotify-documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/spotify-documentation/web-api/#response-schema">error object</a>.
 * Trying to remove an item when you do not have the user's authorization returns error <code>403</code> Forbidden.
 * Attempting to use several different ways to remove items returns <code>400</code> Bad Request.
 * Other client errors returning <code>400</code> Bad Request include specifying invalid positions.</p>
 */
public class RemoveTracksPlaylistRequest {
    private static final TypeReference<SnapshotId> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Remove Items from a Playlist request</h3>
     * @param apiClient <p>The API client</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a></p>
     * @param tracks <p>An array of objects containing <a href="https://developer.spotify.com/spotify-documentation/web-api/#spotify-uris-and-ids">Spotify URIs</a> of the tracks or episodes to remove. For example: <code>{ &quot;tracks&quot;: [{ &quot;uri&quot;: &quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot; },{ &quot;uri&quot;: &quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot; }] }</code>. A maximum of 100 objects can be sent at once.</p>
     */
    public RemoveTracksPlaylistRequest(ApiClient apiClient, String playlistId, java.util.List<String> tracks) {
        this.apiClient = apiClient;
        this.request = new Request("DELETE", "/playlists/{playlist_id}/tracks")
            .addPathParameter("playlist_id", String.valueOf(playlistId))
            .addBodyParameter("tracks", tracks)
        ;
    }

    /**
     * <p>The playlist's snapshot ID against which you want to make the changes. The API will validate that the specified items exist and in the specified positions and make the changes, even if more recent changes have been made to the playlist.</p>
     */
    public RemoveTracksPlaylistRequest snapshotId(String snapshotId) {
        this.request.addBodyParameter("snapshot_id", snapshotId);
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<SnapshotId> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
