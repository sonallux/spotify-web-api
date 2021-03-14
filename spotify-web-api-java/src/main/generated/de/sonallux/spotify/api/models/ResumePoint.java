package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-resumepointobject">ResumePointObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class ResumePoint {
    /**
     * <p>Whether or not the episode has been fully played by the user.</p>
     */
    public boolean fullyPlayed;
    /**
     * <p>The user's most recent position in the episode in milliseconds.</p>
     */
    public int resumePositionMs;
}
