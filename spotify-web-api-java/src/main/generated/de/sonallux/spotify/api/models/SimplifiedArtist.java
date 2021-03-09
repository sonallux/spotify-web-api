package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedartistobject">SimplifiedArtistObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class SimplifiedArtist {
    /**
     * <p>Known external URLs for this artist.</p>
     */
    public ExternalUrl externalUrls;
    /**
     * <p>A link to the Web API endpoint providing full details of the artist.</p>
     */
    public String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist.</p>
     */
    public String id;
    /**
     * <p>The name of the artist.</p>
     */
    public String name;
    /**
     * <p>The object type: <code>&quot;artist&quot;</code></p>
     */
    public String type;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the artist.</p>
     */
    public String uri;
}
