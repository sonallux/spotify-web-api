package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-artistobject">ArtistObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Artist {
    /**
     * <p>Known external URLs for this artist.</p>
     */
    private ExternalUrl external_urls;
    /**
     * <p>Information about the followers of the artist.</p>
     */
    private Followers followers;
    /**
     * <p>A list of the genres the artist is associated with. For example: <code>&quot;Prog Rock&quot;</code> , <code>&quot;Post-Grunge&quot;</code>. (If not yet classified, the array is empty.)</p>
     */
    private java.util.List<String> genres;
    /**
     * <p>A link to the Web API endpoint providing full details of the artist.</p>
     */
    private String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist.</p>
     */
    private String id;
    /**
     * <p>Images of the artist in various sizes, widest first.</p>
     */
    private java.util.List<Image> images;
    /**
     * <p>The name of the artist.</p>
     */
    private String name;
    /**
     * <p>The popularity of the artist. The value will be between 0 and 100, with 100 being the most popular. The artist's popularity is calculated from the popularity of all the artist's tracks.</p>
     */
    private Integer popularity;
    /**
     * <p>The object type: <code>&quot;artist&quot;</code></p>
     */
    private String type;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the artist.</p>
     */
    private String uri;
}
