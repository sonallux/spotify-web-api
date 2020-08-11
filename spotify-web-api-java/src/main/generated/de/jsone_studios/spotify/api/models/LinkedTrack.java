package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LinkedTrack {
    /**
     * Known external URLs for this track.
     */
    private ExternalUrl external_urls;
    /**
     * A link to the Web API endpoint providing full details of the track.
     */
    private String href;
    /**
     * The Spotify ID for the track.
     */
    private String id;
    /**
     * The object type: "track".
     */
    private String type;
    /**
     * The Spotify URI for the track.
     */
    private String uri;
}