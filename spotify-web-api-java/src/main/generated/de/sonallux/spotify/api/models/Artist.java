package de.sonallux.spotify.api.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-artistobject">ArtistObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE) // Disable deserialization based on @JsonTypeInfo
public class Artist extends BaseObject {
    /**
     * <p>Known external URLs for this artist.</p>
     */
    public ExternalUrl externalUrls;
    /**
     * <p>Information about the followers of the artist.</p>
     */
    public Followers followers;
    /**
     * <p>A list of the genres the artist is associated with. For example: <code>&quot;Prog Rock&quot;</code> , <code>&quot;Post-Grunge&quot;</code>. (If not yet classified, the array is empty.)</p>
     */
    public java.util.List<String> genres;
    /**
     * <p>Images of the artist in various sizes, widest first.</p>
     */
    public java.util.List<Image> images;
    /**
     * <p>The name of the artist.</p>
     */
    public String name;
    /**
     * <p>The popularity of the artist. The value will be between 0 and 100, with 100 being the most popular. The artist's popularity is calculated from the popularity of all the artist's tracks.</p>
     */
    public int popularity;
}
