package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-playlisttrackobject">PlaylistTrackObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class PlaylistTrack {
    /**
     * <p>The date and time the track or episode was added. <em>Note that some very old playlists may return <code>null</code> in this field.</em></p>
     */
    public java.time.Instant addedAt;
    /**
     * <p>The Spotify user who added the track or episode. <em>Note that some very old playlists may return <code>null</code> in this field.</em></p>
     */
    public PublicUser addedBy;
    /**
     * <p>Whether this track or episode is a <a href="https://developer.spotify.com/web-api/local-files-spotify-playlists/">local file</a> or not.</p>
     */
    public boolean isLocal;
    /**
     * <p>Information about the track or episode.</p>
     */
    public java.util.Map<String, Object> track;
}
