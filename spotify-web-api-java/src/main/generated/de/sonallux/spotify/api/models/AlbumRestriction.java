package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-albumrestrictionobject">AlbumRestrictionObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class AlbumRestriction {
    /**
     * <p>The reason for the restriction. Supported values:</p> <ul> <li><code>market</code> - The content item is not available in the given market.</li> <li><code>product</code> - The content item is not available for the user's subscription type.</li> <li><code>explicit</code> - The content item is explicit and the user's account is set to not play explicit content.<br> Additional reasons may be added in the future. <strong>Note</strong>: If you use this field, make sure that your application safely handles unknown values.</li> </ul>
     */
    private String reason;
}
