package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-currentlyplayingobject">CurrentlyPlayingObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class CurrentlyPlaying {
    /**
     * A Context Object. Can be null.
     */
    private Context context;
    /**
     * The object type of the currently playing item. Can be one of track, episode, ad or unknown.
     */
    private String currently_playing_type;
    /**
     * If something is currently playing, return true.
     */
    private Boolean is_playing;
    /**
     * The currently playing track or episode. Can be null.
     */
    private java.util.Map<String, Object> item;
    /**
     * Progress into the currently playing track or episode. Can be null.
     */
    private Integer progress_ms;
    /**
     * Unix Millisecond Timestamp when data was fetched
     */
    private Integer timestamp;
}