package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-trackobject">TrackObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Track {
    /**
     * The album on which the track appears. The album object includes a link in href to full information about the album.
     */
    private SimplifiedAlbum album;
    /**
     * The artists who performed the track. Each artist object includes a link in href to more detailed information about the artist.
     */
    private java.util.List<Artist> artists;
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
     * Known external IDs for the track.
     */
    private ExternalId external_ids;
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
     * Part of the response when Track Relinking is applied. If true , the track is playable in the given market. Otherwise false.
     */
    private Boolean is_playable;
    /**
     * Part of the response when Track Relinking is applied, and the requested track has been replaced with different track. The track in the linked_from object contains information about the originally requested track.
     */
    private LinkedTrack linked_from;
    /**
     * The name of the track.
     */
    private String name;
    /**
     * The popularity of the track. The value will be between 0 and 100, with 100 being the most popular. The popularity of a track is a value between 0 and 100, with 100 being the most popular. The popularity is calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how recent those plays are. Generally speaking, songs that are being played a lot now will have a higher popularity than songs that were played a lot in the past. Duplicate tracks (e.g. the same track from a single and an album) are rated independently. Artist and album popularity is derived mathematically from track popularity. Note that the popularity value may lag actual popularity by a few days: the value is not updated in real time.
     */
    private Integer popularity;
    /**
     * A link to a 30 second preview (MP3 format) of the track. Can be null
     */
    private String preview_url;
    /**
     * Part of the response when Track Relinking is applied, the original track is not available in the given market, and Spotify did not have any tracks to relink it with. The track response will still contain metadata for the original track, and a restrictions object containing the reason why the track is not available: &quot;restrictions&quot; : {&quot;reason&quot; : &quot;market&quot;}
     */
    private java.util.List<TrackRestriction> restrictions;
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
