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
    private Boolean interrupting_playback;
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
    private Boolean skipping_next;
    /**
     * <p>Skipping to the previous context. Optional field.</p>
     */
    private Boolean skipping_prev;
    /**
     * <p>Toggling repeat context flag. Optional field.</p>
     */
    private Boolean toggling_repeat_context;
    /**
     * <p>Toggling repeat track flag. Optional field.</p>
     */
    private Boolean toggling_repeat_track;
    /**
     * <p>Toggling shuffle flag. Optional field.</p>
     */
    private Boolean toggling_shuffle;
    /**
     * <p>Transfering playback between devices. Optional field.</p>
     */
    private Boolean transferring_playback;
}
