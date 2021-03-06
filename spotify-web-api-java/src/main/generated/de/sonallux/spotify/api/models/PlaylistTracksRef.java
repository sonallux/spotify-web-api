package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-playlisttracksrefobject">PlaylistTracksRefObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class PlaylistTracksRef {
    /**
     * <p>A link to the Web API endpoint where full details of the playlist's tracks can be retrieved.</p>
     */
    public String href;
    /**
     * <p>Number of tracks in the playlist.</p>
     */
    public int total;
}
