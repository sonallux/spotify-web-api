package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-artistobject">ArtistObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Artist {
    /**
     * Known external URLs for this artist.
     */
    private ExternalUrl external_urls;
    /**
     * Information about the followers of the artist.
     */
    private Followers followers;
    /**
     * A list of the genres the artist is associated with. For example: "Prog Rock" , "Post-Grunge". (If not yet classified, the array is empty.)
     */
    private java.util.List<String> genres;
    /**
     * A link to the Web API endpoint providing full details of the artist.
     */
    private String href;
    /**
     * The Spotify ID for the artist.
     */
    private String id;
    /**
     * Images of the artist in various sizes, widest first.
     */
    private java.util.List<Image> images;
    /**
     * The name of the artist.
     */
    private String name;
    /**
     * The popularity of the artist. The value will be between 0 and 100, with 100 being the most popular. The artist’s popularity is calculated from the popularity of all the artist’s tracks.
     */
    private Integer popularity;
    /**
     * The object type: "artist"
     */
    private String type;
    /**
     * The Spotify URI for the artist.
     */
    private String uri;
}