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
    public boolean interruptingPlayback;
    /**
     * <p>Pausing. Optional field.</p>
     */
    public boolean pausing;
    /**
     * <p>Resuming. Optional field.</p>
     */
    public boolean resuming;
    /**
     * <p>Seeking playback location. Optional field.</p>
     */
    public boolean seeking;
    /**
     * <p>Skipping to the next context. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("skipping_next")
    public boolean skippingNext;
    /**
     * <p>Skipping to the previous context. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("skipping_prev")
    public boolean skippingPrev;
    /**
     * <p>Toggling repeat context flag. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("toggling_repeat_context")
    public boolean togglingRepeatContext;
    /**
     * <p>Toggling repeat track flag. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("toggling_repeat_track")
    public boolean togglingRepeatTrack;
    /**
     * <p>Toggling shuffle flag. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("toggling_shuffle")
    public boolean togglingShuffle;
    /**
     * <p>Transfering playback between devices. Optional field.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("transferring_playback")
    public boolean transferringPlayback;
}
