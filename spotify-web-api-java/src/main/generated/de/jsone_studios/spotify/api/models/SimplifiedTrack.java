package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-simplifiedtrackobject">SimplifiedTrackObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class SimplifiedTrack {
    /**
     * The artists who performed the track. Each artist object includes a link in href to more detailed information about the artist.
     */
    private java.util.List<SimplifiedArtist> artists;
    /**
     * A list of the countries in which the track can be played, identified by their ISO 3166-1 alpha-2 code.
     */
    private java.util.List<String> available_markets;
    /**
     * The disc number (usually 1 unless the album consists of more than one disc).
     */
    private Integer disc_number;
    /**
     * The track length in milliseconds.
     */
    private Integer duration_ms;
    /**
     * Whether or not the track has explicit lyrics ( true &#x3D; yes it does; false &#x3D; no it does not OR unknown).
     */
    private Boolean explicit;
    /**
     * External URLs for this track.
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
     * Part of the response when Track Relinking is applied. If true , the track is playable in the given market. Otherwise false.
     */
    private Boolean is_playable;
    /**
     * Part of the response when Track Relinking is applied and is only part of the response if the track linking, in fact, exists. The requested track has been replaced with a different track. The track in the linked_from object contains information about the originally requested track.
     */
    private LinkedTrack linked_from;
    /**
     * The name of the track.
     */
    private String name;
    /**
     * A URL to a 30 second preview (MP3 format) of the track.
     */
    private String preview_url;
    /**
     * Included in the response when a content restriction is applied. See Restriction Object for more details.
     */
    private TrackRestriction restrictions;
    /**
     * The number of the track. If an album has several discs, the track number is the number on the specified disc.
     */
    private Integer track_number;
    /**
     * The object type: “track”.
     */
    private String type;
    /**
     * The Spotify URI for the track.
     */
    private String uri;
}
