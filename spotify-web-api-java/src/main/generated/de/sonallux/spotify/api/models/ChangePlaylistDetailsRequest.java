package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ChangePlaylistDetailsRequest {
    /**
     * <p>The new name for the playlist, for example <code>&quot;My New Playlist Title&quot;</code></p>
     */
    private String name;
    /**
     * <p>If <code>true</code> the playlist will be public, if <code>false</code> it will be private.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("public")
    @lombok.experimental.Accessors(prefix = "_")
    private Boolean _public;
    /**
     * <p>If <code>true</code> , the playlist will become collaborative and other users will be able to modify the playlist in their Spotify client. <em>Note: You can only set <code>collaborative</code> to <code>true</code> on non-public playlists.</em></p>
     */
    private Boolean collaborative;
    /**
     * <p>Value for playlist description as displayed in Spotify Clients and in the Web API.</p>
     */
    private String description;
}
