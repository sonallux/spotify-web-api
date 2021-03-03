package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * Request body for endpoint <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-add-tracks-to-playlist">Add Items to a Playlist</a>
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AddTracksToPlaylistRequest {
    /**
     * <p>A JSON array of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URIs</a> to add. For example: <code>{&quot;uris&quot;: [&quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot;,&quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;, &quot;spotify:episode:512ojhOuo1ktJprKbVcKyQ&quot;]}</code><br>
     * A maximum of 100 items can be added in one request. <em>Note: if the <code>uris</code> parameter is present in the query string, any URIs listed here in the body will be ignored.</em></p>
     */
    @NonNull
    private final java.util.List<String> uris;
    /**
     * <p>The position to insert the items, a zero-based index. For example, to insert the items in the first position: <code>position=0</code> ; to insert the items in the third position: <code>position=2</code>. If omitted, the items will be appended to the playlist. Items are added in the order they appear in the uris array. For example: <code>{&quot;uris&quot;: [&quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot;,&quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;], &quot;position&quot;: 3}</code></p>
     */
    private int position;
}
