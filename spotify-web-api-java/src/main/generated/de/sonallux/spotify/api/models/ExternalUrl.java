package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-externalurlobject">ExternalUrlObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class ExternalUrl {
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URL</a> for the object.</p>
     */
    private String spotify;
}
