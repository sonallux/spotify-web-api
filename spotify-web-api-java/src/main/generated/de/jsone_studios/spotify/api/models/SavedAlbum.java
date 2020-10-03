package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-savedalbumobject">SavedAlbumObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class SavedAlbum {
    /**
     * The date and time the album was saved Timestamps are returned in ISO 8601 format as Coordinated Universal Time (UTC) with a zero offset: YYYY-MM-DDTHH:MM:SSZ. If the time is imprecise (for example, the date/time of an album release), an additional field indicates the precision; see for example, release_date in an album object.
     */
    private java.time.LocalDateTime added_at;
    /**
     * Information about the album.
     */
    private Album album;
}
