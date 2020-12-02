package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FollowPlaylistRequest {
    /**
     * <p>Defaults to <code>true</code>. If <code>true</code> the playlist will be included in user's public playlists, if <code>false</code> it will remain private. To be able to follow playlists privately, the user must have granted the <code>playlist-modify-private</code> <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#list-of-scopes">scope</a>.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("public")
    private Boolean _public;
}
