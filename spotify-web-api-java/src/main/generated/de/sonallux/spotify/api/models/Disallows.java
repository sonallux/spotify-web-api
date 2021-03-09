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
    public boolean skippingNext;
    /**
     * <p>Skipping to the previous context. Optional field.</p>
     */
    public boolean skippingPrev;
    /**
     * <p>Toggling repeat context flag. Optional field.</p>
     */
    public boolean togglingRepeatContext;
    /**
     * <p>Toggling repeat track flag. Optional field.</p>
     */
    public boolean togglingRepeatTrack;
    /**
     * <p>Toggling shuffle flag. Optional field.</p>
     */
    public boolean togglingShuffle;
    /**
     * <p>Transfering playback between devices. Optional field.</p>
     */
    public boolean transferringPlayback;
}
