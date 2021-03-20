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
    public Disallows actions;
    /**
     * <p>A Context Object. Can be <code>null</code>.</p>
     */
    public Context context;
    /**
     * <p>The object type of the currently playing item. Can be one of <code>track</code>, <code>episode</code>, <code>ad</code> or <code>unknown</code>.</p>
     */
    public String currentlyPlayingType;
    /**
     * <p>The device that is currently active.</p>
     */
    public Device device;
    /**
     * <p>If something is currently playing, return <code>true</code>.</p>
     */
    public boolean isPlaying;
    /**
     * <p>The currently playing track or episode. Can be <code>null</code>.</p>
     */
    public BaseObject item;
    /**
     * <p>Progress into the currently playing track or episode. Can be <code>null</code>.</p>
     */
    public int progressMs;
    /**
     * <p>off, track, context</p>
     */
    public String repeatState;
    /**
     * <p>If shuffle is on or off.</p>
     */
    public String shuffleState;
    /**
     * <p>Unix Millisecond Timestamp when data was fetched.</p>
     */
    public int timestamp;
}
