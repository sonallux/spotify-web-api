package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SimplifiedArtist {
    /**
     * Known external URLs for this artist.
     */
    private ExternalUrl external_urls;
    /**
     * A link to the Web API endpoint providing full details of the artist.
     */
    private String href;
    /**
     * The Spotify ID for the artist.
     */
    private String id;
    /**
     * The name of the artist.
     */
    private String name;
    /**
     * The object type: &quot;artist&quot;
     */
    private String type;
    /**
     * The Spotify URI for the artist.
     */
    private String uri;
}