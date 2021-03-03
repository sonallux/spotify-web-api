package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-episodeobject">EpisodeObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Episode {
    /**
     * <p>A URL to a 30 second preview (MP3 format) of the episode. <code>null</code> if not available.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("audio_preview_url")
    private String audioPreviewUrl;
    /**
     * <p>A description of the episode.</p>
     */
    private String description;
    /**
     * <p>The episode length in milliseconds.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("duration_ms")
    private Integer durationMs;
    /**
     * <p>Whether or not the episode has explicit content (true = yes it does; false = no it does not OR unknown).</p>
     */
    private Boolean explicit;
    /**
     * <p>External URLs for this episode.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("external_urls")
    private ExternalUrl externalUrls;
    /**
     * <p>A link to the Web API endpoint providing full details of the episode.</p>
     */
    private String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the episode.</p>
     */
    private String id;
    /**
     * <p>The cover art for the episode in various sizes, widest first.</p>
     */
    private java.util.List<Image> images;
    /**
     * <p>True if the episode is hosted outside of Spotify's CDN.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("is_externally_hosted")
    private Boolean isExternallyHosted;
    /**
     * <p>True if the episode is playable in the given market. Otherwise false.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("is_playable")
    private Boolean isPlayable;
    /**
     * <p><strong>Note: This field is deprecated and might be removed in the future. Please use the <code>languages</code> field instead.</strong> The language used in the episode, identified by a <a href="https://en.wikipedia.org/wiki/ISO_639">ISO 639</a> code.</p>
     */
    private String language;
    /**
     * <p>A list of the languages used in the episode, identified by their <a href="https://en.wikipedia.org/wiki/ISO_639">ISO 639</a> code.</p>
     */
    private java.util.List<String> languages;
    /**
     * <p>The name of the episode.</p>
     */
    private String name;
    /**
     * <p>The date the episode was first released, for example <code>&quot;1981-12-15&quot;</code>. Depending on the precision, it might be shown as <code>&quot;1981&quot;</code> or <code>&quot;1981-12&quot;</code>.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("release_date")
    private String releaseDate;
    /**
     * <p>The precision with which <code>release_date</code> value is known: <code>&quot;year&quot;</code>, <code>&quot;month&quot;</code>, or <code>&quot;day&quot;</code>.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("release_date_precision")
    private String releaseDatePrecision;
    /**
     * <p>The user's most recent position in the episode. Set if the supplied access token is a user token and has the scope <code>user-read-playback-position</code>.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("resume_point")
    private ResumePoint resumePoint;
    /**
     * <p>The show on which the episode belongs.</p>
     */
    private SimplifiedShow show;
    /**
     * <p>The object type: &quot;episode&quot;.</p>
     */
    private String type;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the episode.</p>
     */
    private String uri;
}
