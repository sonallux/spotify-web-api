package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-albumobject">AlbumObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Album {
    /**
     * The artists of the album. Each artist object includes a link in href to more detailed information about the artist.
     */
    private java.util.List<Artist> artists;
    /**
     * The markets in which the album is available: ISO 3166-1 alpha-2 country codes. Note that an album is considered available in a market when at least 1 of its tracks is available in that market.
     */
    private java.util.List<String> available_markets;
    /**
     * The copyright statements of the album.
     */
    private java.util.List<Copyright> copyrights;
    /**
     * Known external IDs for the album.
     */
    private ExternalId external_ids;
    /**
     * Known external URLs for this album.
     */
    private ExternalUrl external_urls;
    /**
     * A list of the genres used to classify the album. For example: “Prog Rock” , “Post-Grunge”. (If not yet classified, the array is empty.)
     */
    private java.util.List<String> genres;
    /**
     * A link to the Web API endpoint providing full details of the album.
     */
    private String href;
    /**
     * The Spotify ID for the album.
     */
    private String id;
    /**
     * The cover art for the album in various sizes, widest first.
     */
    private java.util.List<Image> images;
    /**
     * The label for the album.
     */
    private String label;
    /**
     * The name of the album. In case of an album takedown, the value may be an empty string.
     */
    private String name;
    /**
     * The popularity of the album. The value will be between 0 and 100, with 100 being the most popular. The popularity is calculated from the popularity of the album’s individual tracks.
     */
    private Integer popularity;
    /**
     * The date the album was first released, for example “1981-12-15”. Depending on the precision, it might be shown as “1981” or “1981-12”.
     */
    private String release_date;
    /**
     * The precision with which release_date value is known: “year” , “month” , or “day”.
     */
    private String release_date_precision;
    /**
     * The tracks of the album.
     */
    private java.util.List<SimplifiedTrack> tracks;
    /**
     * The object type: “album”
     */
    private String type;
    /**
     * The Spotify URI for the album.
     */
    private String uri;
}