package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-artistobject">ArtistObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Artist {
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
     * <p>A link to the Web API endpoint providing full details of the artist.</p>
     */
    public String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist.</p>
     */
    public String id;
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
    /**
     * <p>The object type: <code>&quot;artist&quot;</code></p>
     */
    public String type;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the artist.</p>
     */
    public String uri;
}
