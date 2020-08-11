package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class StartUsersPlaybackRequest {
    /**
     * Spotify URI of the context to play. Valid contexts are albums, artists, playlists. Example: {"context_uri": "spotify:album:1Je1IMUlBXcx1Fz0WE7oPT"}
     */
    private String context_uri;
    /**
     * A JSON array of the Spotify track URIs to play. For example: {"uris": ["spotify:track:4iV5W9uYEdYUVa79Axb7Rh", "spotify:track:1301WleyT98MSxVHPZCA6M"]}
     */
    private java.util.List<String> uris;
    /**
     * Indicates from where in the context playback should start. Only available when context_uri corresponds to an album or playlist object, or when the uris parameter is used. “position” is zero based and can’t be negative. Example: "offset": {"position": 5} “uri” is a string representing the uri of the item to start at. Example: "offset": {"uri": "spotify:track:1301WleyT98MSxVHPZCA6M"}
     */
    private java.util.Map<String, Object> offset;
    /**
     * Indicates from what position to start playback. Must be a positive number. Passing in a position that is greater than the length of the track will cause the player to start playing the next song.
     */
    private Integer position_ms;
}