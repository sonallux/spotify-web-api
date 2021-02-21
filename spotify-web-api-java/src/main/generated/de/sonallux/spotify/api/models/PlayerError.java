package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-playererrorobject">PlayerErrorObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class PlayerError {
    /**
     * <p>A short description of the cause of the error.</p>
     */
    private String message;
    /**
     * <ul>
     * <li><code>NO_PREV_TRACK</code> - The command requires a previous track, but there is none in the context.</li>
     * <li><code>NO_NEXT_TRACK</code> - The command requires a next track, but there is none in the context.</li>
     * <li><code>NO_SPECIFIC_TRACK</code> - The requested track does not exist.</li>
     * <li><code>ALREADY_PAUSED</code> - The command requires playback to not be paused.</li>
     * <li><code>NOT_PAUSED</code> - The command requires playback to be paused.</li>
     * <li><code>NOT_PLAYING_LOCALLY</code> - The command requires playback on the local device.</li>
     * <li><code>NOT_PLAYING_TRACK</code> - The command requires that a track is currently playing.</li>
     * <li><code>NOT_PLAYING_CONTEXT</code> - The command requires that a context is currently playing.</li>
     * <li><code>ENDLESS_CONTEXT</code> - The shuffle command cannot be applied on an endless context.</li>
     * <li><code>CONTEXT_DISALLOW</code> - The command could not be performed on the context.</li>
     * <li><code>ALREADY_PLAYING</code> - The track should not be restarted if the same track and context is already playing, and there is a resume point.</li>
     * <li><code>RATE_LIMITED</code> - The user is rate limited due to too frequent track play, also known as cat-on-the-keyboard spamming.</li>
     * <li><code>REMOTE_CONTROL_DISALLOW</code> - The context cannot be remote-controlled.</li>
     * <li><code>DEVICE_NOT_CONTROLLABLE</code> - Not possible to remote control the device.</li>
     * <li><code>VOLUME_CONTROL_DISALLOW</code> - Not possible to remote control the device's volume.</li>
     * <li><code>NO_ACTIVE_DEVICE</code> - Requires an active device and the user has none.</li>
     * <li><code>PREMIUM_REQUIRED</code> - The request is prohibited for non-premium users.</li>
     * <li><code>UNKNOWN</code> - Certain actions are restricted because of unknown reasons.</li>
     * </ul>
     */
    private String reason;
    /**
     * <p>The HTTP status code. Either <code>404 NOT FOUND</code> or <code>403 FORBIDDEN</code>. Also returned in the response header.</p>
     */
    private Integer status;
}
