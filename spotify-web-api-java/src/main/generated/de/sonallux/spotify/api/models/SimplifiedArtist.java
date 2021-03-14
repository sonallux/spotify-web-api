package de.sonallux.spotify.api.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedartistobject">SimplifiedArtistObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE) // Disable deserialization based on @JsonTypeInfo
public class SimplifiedArtist extends BaseObject {
    /**
     * <p>Known external URLs for this artist.</p>
     */
    public ExternalUrl externalUrls;
    /**
     * <p>The name of the artist.</p>
     */
    public String name;
}
