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
     * <p><a href="http://en.wikipedia.org/wiki/International_Article_Number_%28EAN%29">International Article Number</a></p>
     */
    private String ean;
    /**
     * <p><a href="http://en.wikipedia.org/wiki/International_Standard_Recording_Code">International Standard Recording Code</a></p>
     */
    private String isrc;
    /**
     * <p><a href="http://en.wikipedia.org/wiki/Universal_Product_Code">Universal Product Code</a></p>
     */
    private String upc;
}
