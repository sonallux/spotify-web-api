package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SimplifiedArtist {
    /**
     * <p>Known external URLs for this artist.</p>
     */
    private ExternalUrl external_urls;
    /**
     * <p>A link to the Web API endpoint providing full details of the artist.</p>
     */
    private String href;
    /**
     * <p>The Spotify ID for the artist.</p>
     */
    private String id;
    /**
     * <p>The name of the artist.</p>
     */
    private String name;
    /**
     * <p>The object type: &quot;artist&quot;</p>
     */
    private String type;
    /**
     * <p>The Spotify URI for the artist.</p>
     */
    private String uri;
}
