package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-currentlyplayingcontextobject">CurrentlyPlayingContextObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class CurrentlyPlayingContext {
    /**
     * <p>Allows to update the user interface based on which playback actions are available within the current context.</p>
     */
    private Disallows actions;
    /**
     * <p>A Context Object. Can be <code>null</code>.</p>
     */
    private Context context;
    /**
     * <p>The object type of the currently playing item. Can be one of <code>track</code>, <code>episode</code>, <code>ad</code> or <code>unknown</code>.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("currently_playing_type")
    private String currentlyPlayingType;
    /**
     * <p>The device that is currently active.</p>
     */
    private Device device;
    /**
     * <p>If something is currently playing, return <code>true</code>.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("is_playing")
    private Boolean isPlaying;
    /**
     * <p>The currently playing track or episode. Can be <code>null</code>.</p>
     */
    private java.util.Map<String, Object> item;
    /**
     * <p>Progress into the currently playing track or episode. Can be <code>null</code>.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("progress_ms")
    private Integer progressMs;
    /**
     * <p>off, track, context</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("repeat_state")
    private String repeatState;
    /**
     * <p>If shuffle is on or off.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("shuffle_state")
    private String shuffleState;
    /**
     * <p>Unix Millisecond Timestamp when data was fetched.</p>
     */
    private Integer timestamp;
}
