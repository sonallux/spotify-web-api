package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-externalidobject">ExternalIdObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class ExternalId {
    /**
     * International Article Number
     */
    private String ean;
    /**
     * International Standard Recording Code
     */
    private String isrc;
    /**
     * Universal Product Code
     */
    private String upc;
}
