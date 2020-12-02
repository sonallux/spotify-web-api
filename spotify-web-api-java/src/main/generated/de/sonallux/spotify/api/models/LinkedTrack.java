package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LinkedTrack {
    /**
     * <p>Known external URLs for this track.</p>
     */
    private ExternalUrl external_urls;
    /**
     * <p>A link to the Web API endpoint providing full details of the track.</p>
     */
    private String href;
    /**
     * <p>The Spotify ID for the track.</p>
     */
    private String id;
    /**
     * <p>The object type: &quot;track&quot;.</p>
     */
    private String type;
    /**
     * <p>The Spotify URI for the track.</p>
     */
    private String uri;
}
