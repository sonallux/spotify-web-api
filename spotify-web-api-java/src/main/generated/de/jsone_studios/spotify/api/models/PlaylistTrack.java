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
     * <p>The date and time the track or episode was added. <em>Note that some very old playlists may return <code>null</code> in this field.</em></p>
     */
    private java.time.LocalDateTime added_at;
    /**
     * <p>The Spotify user who added the track or episode. <em>Note that some very old playlists may return <code>null</code> in this field.</em></p>
     */
    private PublicUser added_by;
    /**
     * <p>Whether this track or episode is a <a href="https://developer.spotify.com/web-api/local-files-spotify-playlists/">local file</a> or not.</p>
     */
    private Boolean is_local;
    /**
     * <p>Information about the track or episode.</p>
     */
    private java.util.Map<String, Object> track;
}
