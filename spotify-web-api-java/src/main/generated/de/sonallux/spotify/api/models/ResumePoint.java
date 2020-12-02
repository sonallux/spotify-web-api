package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-resumepointobject">ResumePointObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class ResumePoint {
    /**
     * <p>Whether or not the episode has been fully played by the user.</p>
     */
    private Boolean fully_played;
    /**
     * <p>The user's most recent position in the episode in milliseconds.</p>
     */
    private Integer resume_position_ms;
}
