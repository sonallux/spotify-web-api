package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-savedtrackobject">SavedTrackObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class SavedTrack {
    /**
     * <p>The date and time the track was saved. Timestamps are returned in ISO 8601 format as Coordinated Universal Time (UTC) with a zero offset: YYYY-MM-DDTHH:MM:SSZ. If the time is imprecise (for example, the date/time of an album release), an additional field indicates the precision; see for example, release_date in an album object.</p>
     */
    public java.time.Instant addedAt;
    /**
     * <p>Information about the track.</p>
     */
    public Track track;
}
