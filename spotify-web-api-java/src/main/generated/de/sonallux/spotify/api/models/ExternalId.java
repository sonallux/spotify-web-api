package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-externalidobject">ExternalIdObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class ExternalId {
    /**
     * <p><a href="http://en.wikipedia.org/wiki/International_Article_Number_%28EAN%29">International Article Number</a></p>
     */
    public String ean;
    /**
     * <p><a href="http://en.wikipedia.org/wiki/International_Standard_Recording_Code">International Standard Recording Code</a></p>
     */
    public String isrc;
    /**
     * <p><a href="http://en.wikipedia.org/wiki/Universal_Product_Code">Universal Product Code</a></p>
     */
    public String upc;
}
