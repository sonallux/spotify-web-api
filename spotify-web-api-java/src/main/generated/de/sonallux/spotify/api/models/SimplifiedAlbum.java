package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedalbumobject">SimplifiedAlbumObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class SimplifiedAlbum {
    /**
     * <p>The field is present when getting an artist's albums. Possible values are &quot;album&quot;, &quot;single&quot;, &quot;compilation&quot;, &quot;appears_on&quot;. Compare to album_type this field represents relationship between the artist and the album.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("album_group")
    private String albumGroup;
    /**
     * <p>The type of the album: one of &quot;album&quot;, &quot;single&quot;, or &quot;compilation&quot;.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("album_type")
    private String albumType;
    /**
     * <p>The artists of the album. Each artist object includes a link in <code>href</code> to more detailed information about the artist.</p>
     */
    private java.util.List<SimplifiedArtist> artists;
    /**
     * <p>The markets in which the album is available: <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country codes</a>. Note that an album is considered available in a market when at least 1 of its tracks is available in that market.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("available_markets")
    private java.util.List<String> availableMarkets;
    /**
     * <p>Known external URLs for this album.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("external_urls")
    private ExternalUrl externalUrls;
    /**
     * <p>A link to the Web API endpoint providing full details of the album.</p>
     */
    private String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the album.</p>
     */
    private String id;
    /**
     * <p>The cover art for the album in various sizes, widest first.</p>
     */
    private java.util.List<Image> images;
    /**
     * <p>The name of the album. In case of an album takedown, the value may be an empty string.</p>
     */
    private String name;
    /**
     * <p>The date the album was first released, for example <code>1981</code>. Depending on the precision, it might be shown as <code>1981-12</code> or <code>1981-12-15</code>.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("release_date")
    private String releaseDate;
    /**
     * <p>The precision with which <code>release_date</code> value is known: <code>year</code> , <code>month</code> , or <code>day</code>.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("release_date_precision")
    private String releaseDatePrecision;
    /**
     * <p>Included in the response when a content restriction is applied. See <a href="https://developer.spotify.com/documentation/web-api/reference/#object-albumrestrictionobject">Restriction Object</a> for more details.</p>
     */
    private AlbumRestriction restrictions;
    /**
     * <p>The object type: &quot;album&quot;</p>
     */
    private String type;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the album.</p>
     */
    private String uri;
}
