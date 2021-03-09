package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-showobject">ShowObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Show {
    /**
     * <p>A list of the countries in which the show can be played, identified by their <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code.</p>
     */
    public java.util.List<String> availableMarkets;
    /**
     * <p>The copyright statements of the show.</p>
     */
    public java.util.List<Copyright> copyrights;
    /**
     * <p>A description of the show.</p>
     */
    public String description;
    /**
     * <p>A list of the show's episodes.</p>
     */
    public Paging<SimplifiedEpisode> episodes;
    /**
     * <p>Whether or not the show has explicit content (true = yes it does; false = no it does not OR unknown).</p>
     */
    public boolean explicit;
    /**
     * <p>External URLs for this show.</p>
     */
    public ExternalUrl externalUrls;
    /**
     * <p>A link to the Web API endpoint providing full details of the show.</p>
     */
    public String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the show.</p>
     */
    public String id;
    /**
     * <p>The cover art for the show in various sizes, widest first.</p>
     */
    public java.util.List<Image> images;
    /**
     * <p>True if all of the show's episodes are hosted outside of Spotify's CDN. This field might be <code>null</code> in some cases.</p>
     */
    public boolean isExternallyHosted;
    /**
     * <p>A list of the languages used in the show, identified by their <a href="https://en.wikipedia.org/wiki/ISO_639">ISO 639</a> code.</p>
     */
    public java.util.List<String> languages;
    /**
     * <p>The media type of the show.</p>
     */
    public String mediaType;
    /**
     * <p>The name of the episode.</p>
     */
    public String name;
    /**
     * <p>The publisher of the show.</p>
     */
    public String publisher;
    /**
     * <p>The object type: &quot;show&quot;.</p>
     */
    public String type;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the show.</p>
     */
    public String uri;
}
