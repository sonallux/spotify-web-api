package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * Request body for endpoint <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-start-a-users-playback">Start/Resume a User's Playback</a>
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class StartUsersPlaybackRequest {
    /**
     * <p>Spotify URI of the context to play. Valid contexts are albums, artists, playlists. Example: {&quot;context_uri&quot;: &quot;spotify:album:1Je1IMUlBXcx1Fz0WE7oPT&quot;}</p>
     */
    private String contextUri;
    /**
     * <p>A JSON array of the Spotify track URIs to play. For example: {&quot;uris&quot;: [&quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot;, &quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;]}</p>
     */
    private java.util.List<String> uris;
    /**
     * <p>Indicates from where in the context playback should start. Only available when context_uri corresponds to an album or playlist object, or when the uris parameter is used. “position” is zero based and can’t be negative. Example: &quot;offset&quot;: {&quot;position&quot;: 5} “uri” is a string representing the uri of the item to start at. Example: &quot;offset&quot;: {&quot;uri&quot;: &quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;}</p>
     */
    private java.util.Map<String, Object> offset;
    /**
     * <p>Indicates from what position to start playback. Must be a positive number. Passing in a position that is greater than the length of the track will cause the player to start playing the next song.</p>
     */
    private Integer positionMs;
}
