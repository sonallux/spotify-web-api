package de.sonallux.spotify.api.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-trackobject">TrackObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE) // Disable deserialization based on @JsonTypeInfo
public class Track extends BaseObject {
    /**
     * <p>The album on which the track appears. The album object includes a link in <code>href</code> to full information about the album.</p>
     */
    public SimplifiedAlbum album;
    /**
     * <p>The artists who performed the track. Each artist object includes a link in <code>href</code> to more detailed information about the artist.</p>
     */
    public java.util.List<Artist> artists;
    /**
     * <p>A list of the countries in which the track can be played, identified by their <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code.</p>
     */
    public java.util.List<String> availableMarkets;
    /**
     * <p>The disc number (usually <code>1</code> unless the album consists of more than one disc).</p>
     */
    public int discNumber;
    /**
     * <p>The track length in milliseconds.</p>
     */
    public int durationMs;
    /**
     * <p>Whether or not the track has explicit lyrics ( <code>true</code> = yes it does; <code>false</code> = no it does not OR unknown).</p>
     */
    public boolean explicit;
    /**
     * <p>Known external IDs for the track.</p>
     */
    public ExternalId externalIds;
    /**
     * <p>Known external URLs for this track.</p>
     */
    public ExternalUrl externalUrls;
    /**
     * <p>Whether or not the track is from a local file.</p>
     */
    public boolean isLocal;
    /**
     * <p>Part of the response when <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a> is applied. If <code>true</code> , the track is playable in the given market. Otherwise <code>false</code>.</p>
     */
    public boolean isPlayable;
    /**
     * <p>Part of the response when <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a> is applied, and the requested track has been replaced with different track. The track in the <code>linked_from</code> object contains information about the originally requested track.</p>
     */
    public LinkedTrack linkedFrom;
    /**
     * <p>The name of the track.</p>
     */
    public String name;
    /**
     * <p>The popularity of the track. The value will be between 0 and 100, with 100 being the most popular.<br>
     * The popularity of a track is a value between 0 and 100, with 100 being the most popular. The popularity is calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how recent those plays are.<br>
     * Generally speaking, songs that are being played a lot now will have a higher popularity than songs that were played a lot in the past. Duplicate tracks (e.g. the same track from a single and an album) are rated independently. Artist and album popularity is derived mathematically from track popularity. Note that the popularity value may lag actual popularity by a few days: the value is not updated in real time.</p>
     */
    public int popularity;
    /**
     * <p>A link to a 30 second preview (MP3 format) of the track. Can be <code>null</code></p>
     */
    public String previewUrl;
    /**
     * <p>Included in the response when a content restriction is applied. See <a href="https://developer.spotify.com/documentation/web-api/reference/#object-trackrestrictionobject">Restriction Object</a> for more details.</p>
     */
    public TrackRestriction restrictions;
    /**
     * <p>The number of the track. If an album has several discs, the track number is the number on the specified disc.</p>
     */
    public int trackNumber;
}
