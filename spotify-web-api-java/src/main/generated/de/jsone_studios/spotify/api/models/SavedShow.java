package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-savedshowobject">SavedShowObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class SavedShow {
    /**
     * The date and time the show was saved. Timestamps are returned in ISO 8601 format as Coordinated Universal Time (UTC) with a zero offset: YYYY-MM-DDTHH:MM:SSZ. If the time is imprecise (for example, the date/time of an album release), an additional field indicates the precision; see for example, release_date in an album object.
     */
    private java.time.LocalDateTime added_at;
    /**
     * Information about the show.
     */
    private SimplifiedShow show;
}