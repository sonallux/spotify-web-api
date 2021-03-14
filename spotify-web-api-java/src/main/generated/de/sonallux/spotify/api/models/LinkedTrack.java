package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-linkedtrackobject">LinkedTrackObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class LinkedTrack {
    /**
     * <p>Known external URLs for this track.</p>
     */
    public ExternalUrl externalUrls;
    /**
     * <p>A link to the Web API endpoint providing full details of the track.</p>
     */
    public String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the track.</p>
     */
    public String id;
    /**
     * <p>The object type: &quot;track&quot;.</p>
     */
    public String type;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the track.</p>
     */
    public String uri;
}
