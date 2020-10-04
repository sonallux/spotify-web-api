package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-resumepointobject">ResumePointObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class ResumePoint {
    /**
     * Whether or not the episode has been fully played by the user.
     */
    private Boolean fully_played;
    /**
     * The user’s most recent position in the episode in milliseconds.
     */
    private Integer resume_position_ms;
}
