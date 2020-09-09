package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RemoveTracksPlaylistRequest {
    /**
     * An array of objects containing Spotify URIs of the tracks or episodes to remove. For example: { &quot;tracks&quot;: [{ &quot;uri&quot;: &quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot; },{ &quot;uri&quot;: &quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot; }] }. A maximum of 100 objects can be sent at once.
     */
    @NonNull
    private java.util.List<String> tracks;
    /**
     * The playlist’s snapshot ID against which you want to make the changes. The API will validate that the specified items exist and in the specified positions and make the changes, even if more recent changes have been made to the playlist.
     */
    private String snapshot_id;
}