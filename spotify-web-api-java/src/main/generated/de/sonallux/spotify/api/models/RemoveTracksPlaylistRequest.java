package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * Request body for endpoint <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-remove-tracks-playlist">Remove Items from a Playlist</a>
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class RemoveTracksPlaylistRequest {
    /**
     * <p>An array of objects containing <a href="https://developer.spotify.com/spotify-documentation/web-api/#spotify-uris-and-ids">Spotify URIs</a> of the tracks or episodes to remove. For example: <code>{ &quot;tracks&quot;: [{ &quot;uri&quot;: &quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot; },{ &quot;uri&quot;: &quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot; }] }</code>. A maximum of 100 objects can be sent at once.</p>
     */
    @NonNull
    private final java.util.List<String> tracks;
    /**
     * <p>The playlist's snapshot ID against which you want to make the changes. The API will validate that the specified items exist and in the specified positions and make the changes, even if more recent changes have been made to the playlist.</p>
     */
    private String snapshot_id;
}
