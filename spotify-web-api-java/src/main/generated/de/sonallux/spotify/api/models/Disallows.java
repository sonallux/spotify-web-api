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
    private boolean interruptingPlayback;
    /**
     * <p>Pausing. Optional field.</p>
     */
    private boolean pausing;
    /**
     * <p>Resuming. Optional field.</p>
     */
    private boolean resuming;
    /**
     * <p>Seeking playback location. Optional field.</p>
     */
    private boolean seeking;
    /**
     * <p>Skipping to the next context. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("skipping_next")
    private boolean skippingNext;
    /**
     * <p>Skipping to the previous context. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("skipping_prev")
    private boolean skippingPrev;
    /**
     * <p>Toggling repeat context flag. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("toggling_repeat_context")
    private boolean togglingRepeatContext;
    /**
     * <p>Toggling repeat track flag. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("toggling_repeat_track")
    private boolean togglingRepeatTrack;
    /**
     * <p>Toggling shuffle flag. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("toggling_shuffle")
    private boolean togglingShuffle;
    /**
     * <p>Transfering playback between devices. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("transferring_playback")
    private boolean transferringPlayback;
}
