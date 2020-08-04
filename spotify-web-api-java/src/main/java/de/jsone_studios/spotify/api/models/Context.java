package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-contextobject">ContextObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Context {
    /**
     * External URLs for this context.
     */
    private ExternalUrl external_urls;
    /**
     * A link to the Web API endpoint providing full details of the track.
     */
    private String href;
    /**
     * The object type, e.g. “artist”, “playlist”, “album”.
     */
    private String type;
    /**
     * The Spotify URI for the context.
     */
    private String uri;
}