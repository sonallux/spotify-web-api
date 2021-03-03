package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-albumobject">AlbumObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Album {
    /**
     * <p>The type of the album: <code>album</code>, <code>single</code>, or <code>compilation</code>.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("album_type")
    private String albumType;
    /**
     * <p>The artists of the album. Each artist object includes a link in <code>href</code> to more detailed information about the artist.</p>
     */
    private java.util.List<Artist> artists;
    /**
     * <p>The markets in which the album is available: <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country codes</a>. Note that an album is considered available in a market when at least 1 of its tracks is available in that market.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("available_markets")
    private java.util.List<String> availableMarkets;
    /**
     * <p>The copyright statements of the album.</p>
     */
    private java.util.List<Copyright> copyrights;
    /**
     * <p>Known external IDs for the album.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("external_ids")
    private ExternalId externalIds;
    /**
     * <p>Known external URLs for this album.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("external_urls")
    private ExternalUrl externalUrls;
    /**
     * <p>A list of the genres used to classify the album. For example: &quot;Prog Rock&quot; , &quot;Post-Grunge&quot;. (If not yet classified, the array is empty.)</p>
     */
    private java.util.List<String> genres;
    /**
     * <p>A link to the Web API endpoint providing full details of the album.</p>
     */
    private String href;
    /**
     * <p>The Spotify ID for the album.</p>
     */
    private String id;
    /**
     * <p>The cover art for the album in various sizes, widest first.</p>
     */
    private java.util.List<Image> images;
    /**
     * <p>The label for the album.</p>
     */
    private String label;
    /**
     * <p>The name of the album. In case of an album takedown, the value may be an empty string.</p>
     */
    private String name;
    /**
     * <p>The popularity of the album. The value will be between 0 and 100, with 100 being the most popular. The popularity is calculated from the popularity of the album's individual tracks.</p>
     */
    private Integer popularity;
    /**
     * <p>The date the album was first released, for example &quot;1981-12-15&quot;. Depending on the precision, it might be shown as &quot;1981&quot; or &quot;1981-12&quot;.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("release_date")
    private String releaseDate;
    /**
     * <p>The precision with which release_date value is known: &quot;year&quot; , &quot;month&quot; , or &quot;day&quot;.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("release_date_precision")
    private String releaseDatePrecision;
    /**
     * <p>Included in the response when a content restriction is applied. See <a href="https://developer.spotify.com/documentation/web-api/reference/#object-albumrestrictionobject">Restriction Object</a> for more details.</p>
     */
    private AlbumRestriction restrictions;
    /**
     * <p>The tracks of the album.</p>
     */
    private Paging<SimplifiedTrack> tracks;
    /**
     * <p>The object type: &quot;album&quot;</p>
     */
    private String type;
    /**
     * <p>The Spotify URI for the album.</p>
     */
    private String uri;
}
