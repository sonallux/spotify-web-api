package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-currentlyplayingobject">CurrentlyPlayingObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class CurrentlyPlaying {
    /**
     * <p>A Context Object. Can be <code>null</code>.</p>
     */
    private Context context;
    /**
     * <p>The object type of the currently playing item. Can be one of <code>track</code>, <code>episode</code>, <code>ad</code> or <code>unknown</code>.</p>
     */
    private String currently_playing_type;
    /**
     * <p>If something is currently playing, return <code>true</code>.</p>
     */
    private Boolean is_playing;
    /**
     * <p>The currently playing track or episode. Can be <code>null</code>.</p>
     */
    private java.util.Map<String, Object> item;
    /**
     * <p>Progress into the currently playing track or episode. Can be <code>null</code>.</p>
     */
    private Integer progress_ms;
    /**
     * <p>Unix Millisecond Timestamp when data was fetched</p>
     */
    private Integer timestamp;
}
