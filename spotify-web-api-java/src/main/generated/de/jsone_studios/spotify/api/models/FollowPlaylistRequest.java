package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FollowPlaylistRequest {
    /**
     * Defaults to true. If true the playlist will be included in user’s public playlists, if false it will remain private. To be able to follow playlists privately, the user must have granted the playlist-modify-private scope.
     */
    @com.fasterxml.jackson.annotation.JsonProperty("public")
    private Boolean _public;
}