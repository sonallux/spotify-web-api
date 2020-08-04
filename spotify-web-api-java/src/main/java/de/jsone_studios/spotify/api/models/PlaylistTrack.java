package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-playlisttrackobject">PlaylistTrackObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class PlaylistTrack {
    /**
     * The date and time the track or episode was added. Note that some very old playlists may return null in this field.
     */
    private java.time.LocalDateTime added_at;
    /**
     * The Spotify user who added the track or episode. Note that some very old playlists may return null in this field.
     */
    private PublicUser added_by;
    /**
     * Whether this track or episode is a local file or not.
     */
    private Boolean is_local;
    /**
     * Information about the track or episode.
     */
    private java.util.Map<String, Object> track;
}