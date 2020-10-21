package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-albumrestrictionobject">AlbumRestrictionObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class AlbumRestriction {
    /**
     * <p>The reason for the restriction. Supported values:</p>
     */
    private String reason;
}
