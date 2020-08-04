package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TrackRestriction {
    /**
     * The reason why a track is not available.
     */
    private String reason;
}