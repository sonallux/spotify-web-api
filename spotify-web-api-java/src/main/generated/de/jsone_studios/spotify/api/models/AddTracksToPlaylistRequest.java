package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AddTracksToPlaylistRequest {
    /**
     * A JSON array of the Spotify URIs to add. For example: {&quot;uris&quot;: [&quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot;,&quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;, &quot;spotify:episode:512ojhOuo1ktJprKbVcKyQ&quot;]} A maximum of 100 items can be added in one request. Note: if the uris parameter is present in the query string, any URIs listed here in the body will be ignored.
     */
    private java.util.List<String> uris;
    /**
     * The position to insert the items, a zero-based index. For example, to insert the items in the first position: position&#x3D;0 ; to insert the items in the third position: position&#x3D;2. If omitted, the items will be appended to the playlist. Items are added in the order they appear in the uris array. For example: {&quot;uris&quot;: [&quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot;,&quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;], &quot;position&quot;: 3}
     */
    private Integer position;
}
