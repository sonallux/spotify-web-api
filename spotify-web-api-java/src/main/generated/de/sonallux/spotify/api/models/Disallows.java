package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-disallowsobject">DisallowsObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Disallows {
    /**
     * <p>Interrupting playback. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("interrupting_playback")
    private Boolean interruptingPlayback;
    /**
     * <p>Pausing. Optional field.</p>
     */
    private Boolean pausing;
    /**
     * <p>Resuming. Optional field.</p>
     */
    private Boolean resuming;
    /**
     * <p>Seeking playback location. Optional field.</p>
     */
    private Boolean seeking;
    /**
     * <p>Skipping to the next context. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("skipping_next")
    private Boolean skippingNext;
    /**
     * <p>Skipping to the previous context. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("skipping_prev")
    private Boolean skippingPrev;
    /**
     * <p>Toggling repeat context flag. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("toggling_repeat_context")
    private Boolean togglingRepeatContext;
    /**
     * <p>Toggling repeat track flag. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("toggling_repeat_track")
    private Boolean togglingRepeatTrack;
    /**
     * <p>Toggling shuffle flag. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("toggling_shuffle")
    private Boolean togglingShuffle;
    /**
     * <p>Transfering playback between devices. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("transferring_playback")
    private Boolean transferringPlayback;
}
