package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-simplifiedshowobject">SimplifiedShowObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class SimplifiedShow {
    /**
     * A list of the countries in which the show can be played, identified by their ISO 3166-1 alpha-2 code.
     */
    private java.util.List<String> available_markets;
    /**
     * The copyright statements of the show.
     */
    private java.util.List<Copyright> copyrights;
    /**
     * A description of the show.
     */
    private String description;
    /**
     * Whether or not the show has explicit content (true = yes it does; false = no it does not OR unknown).
     */
    private Boolean explicit;
    /**
     * External URLs for this show.
     */
    private ExternalUrl external_urls;
    /**
     * A link to the Web API endpoint providing full details of the show.
     */
    private String href;
    /**
     * The Spotify ID for the show.
     */
    private String id;
    /**
     * The cover art for the show in various sizes, widest first.
     */
    private java.util.List<Image> images;
    /**
     * True if all of the show’s episodes are hosted outside of Spotify’s CDN. This field might be null in some cases.
     */
    private Boolean is_externally_hosted;
    /**
     * A list of the languages used in the show, identified by their ISO 639 code.
     */
    private java.util.List<String> languages;
    /**
     * The media type of the show.
     */
    private String media_type;
    /**
     * The name of the episode.
     */
    private String name;
    /**
     * The publisher of the show.
     */
    private String publisher;
    /**
     * The object type: “show”.
     */
    private String type;
    /**
     * The Spotify URI for the show.
     */
    private String uri;
}