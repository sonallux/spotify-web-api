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
    @com.fasterxml.jackson.annotation.JsonProperty("added_at")
    private java.time.LocalDateTime addedAt;
    /**
     * <p>The Spotify user who added the track or episode. <em>Note that some very old playlists may return <code>null</code> in this field.</em></p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("added_by")
    private PublicUser addedBy;
    /**
     * <p>Whether this track or episode is a <a href="https://developer.spotify.com/web-api/local-files-spotify-playlists/">local file</a> or not.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("is_local")
    private Boolean isLocal;
    /**
     * <p>Information about the track or episode.</p>
     */
    private java.util.Map<String, Object> track;
}
