package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-episodeobject">EpisodeObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Episode {
    /**
     * A URL to a 30 second preview (MP3 format) of the episode. null if not available.
     */
    private String audio_preview_url;
    /**
     * A description of the episode.
     */
    private String description;
    /**
     * The episode length in milliseconds.
     */
    private Integer duration_ms;
    /**
     * Whether or not the episode has explicit content (true &#x3D; yes it does; false &#x3D; no it does not OR unknown).
     */
    private Boolean explicit;
    /**
     * External URLs for this episode.
     */
    private ExternalUrl external_urls;
    /**
     * A link to the Web API endpoint providing full details of the episode.
     */
    private String href;
    /**
     * The Spotify ID for the episode.
     */
    private String id;
    /**
     * The cover art for the episode in various sizes, widest first.
     */
    private java.util.List<Image> images;
    /**
     * True if the episode is hosted outside of Spotify’s CDN.
     */
    private Boolean is_externally_hosted;
    /**
     * True if the episode is playable in the given market. Otherwise false.
     */
    private Boolean is_playable;
    /**
     * Note: This field is deprecated and might be removed in the future. Please use the languages field instead. The language used in the episode, identified by a ISO 639 code.
     */
    private String language;
    /**
     * A list of the languages used in the episode, identified by their ISO 639 code.
     */
    private java.util.List<String> languages;
    /**
     * The name of the episode.
     */
    private String name;
    /**
     * The date the episode was first released, for example &quot;1981-12-15&quot;. Depending on the precision, it might be shown as &quot;1981&quot; or &quot;1981-12&quot;.
     */
    private String release_date;
    /**
     * The precision with which release_date value is known: &quot;year&quot;, &quot;month&quot;, or &quot;day&quot;.
     */
    private String release_date_precision;
    /**
     * The user’s most recent position in the episode. Set if the supplied access token is a user token and has the scope user-read-playback-position.
     */
    private ResumePoint resume_point;
    /**
     * The show on which the episode belongs.
     */
    private SimplifiedShow show;
    /**
     * The object type: “episode”.
     */
    private String type;
    /**
     * The Spotify URI for the episode.
     */
    private String uri;
}