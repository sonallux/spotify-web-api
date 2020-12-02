package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-trackrestrictionobject">TrackRestrictionObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class TrackRestriction {
    /**
     * <p>The reason for the restriction. Supported values:</p>
     */
    private String reason;
}
