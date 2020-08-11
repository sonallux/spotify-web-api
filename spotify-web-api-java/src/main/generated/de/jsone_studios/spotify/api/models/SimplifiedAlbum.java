package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-simplifiedalbumobject">SimplifiedAlbumObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class SimplifiedAlbum {
    /**
     * The field is present when getting an artist’s albums. Possible values are “album”, “single”, “compilation”, “appears_on”. Compare to album_type this field represents relationship between the artist and the album.
     */
    private String album_group;
    /**
     * The type of the album: one of “album”, “single”, or “compilation”.
     */
    private String album_type;
    /**
     * The artists of the album. Each artist object includes a link in href to more detailed information about the artist.
     */
    private java.util.List<SimplifiedArtist> artists;
    /**
     * The markets in which the album is available: ISO 3166-1 alpha-2 country codes. Note that an album is considered available in a market when at least 1 of its tracks is available in that market.
     */
    private java.util.List<String> available_markets;
    /**
     * Known external URLs for this album.
     */
    private ExternalUrl external_urls;
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
     * The name of the album. In case of an album takedown, the value may be an empty string.
     */
    private String name;
    /**
     * The object type: “album”
     */
    private String type;
    /**
     * The Spotify URI for the album.
     */
    private String uri;
}