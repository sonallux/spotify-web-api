package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedshowobject">SimplifiedShowObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class SimplifiedShow {
    /**
     * <p>A list of the countries in which the show can be played, identified by their <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("available_markets")
    private java.util.List<String> availableMarkets;
    /**
     * <p>The copyright statements of the show.</p>
     */
    private java.util.List<Copyright> copyrights;
    /**
     * <p>A description of the show.</p>
     */
    private String description;
    /**
     * <p>Whether or not the show has explicit content (true = yes it does; false = no it does not OR unknown).</p>
     */
    private Boolean explicit;
    /**
     * <p>External URLs for this show.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("external_urls")
    private ExternalUrl externalUrls;
    /**
     * <p>A link to the Web API endpoint providing full details of the show.</p>
     */
    private String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the show.</p>
     */
    private String id;
    /**
     * <p>The cover art for the show in various sizes, widest first.</p>
     */
    private java.util.List<Image> images;
    /**
     * <p>True if all of the show's episodes are hosted outside of Spotify's CDN. This field might be <code>null</code> in some cases.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("is_externally_hosted")
    private Boolean isExternallyHosted;
    /**
     * <p>A list of the languages used in the show, identified by their <a href="https://en.wikipedia.org/wiki/ISO_639">ISO 639</a> code.</p>
     */
    private java.util.List<String> languages;
    /**
     * <p>The media type of the show.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("media_type")
    private String mediaType;
    /**
     * <p>The name of the episode.</p>
     */
    private String name;
    /**
     * <p>The publisher of the show.</p>
     */
    private String publisher;
    /**
     * <p>The object type: &quot;show&quot;.</p>
     */
    private String type;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the show.</p>
     */
    private String uri;
}
