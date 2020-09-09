package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CreatePlaylistRequest {
    /**
     * The name for the new playlist, for example &quot;Your Coolest Playlist&quot; . This name does not need to be unique; a user may have several playlists with the same name.
     */
    @NonNull
    private String name;
    /**
     * Defaults to true . If true the playlist will be public, if false it will be private. To be able to create private playlists, the user must have granted the playlist-modify-private scope
     */
    @com.fasterxml.jackson.annotation.JsonProperty("public")
    private Boolean _public;
    /**
     * Defaults to false . If true the playlist will be collaborative. Note that to create a collaborative playlist you must also set public to false . To create collaborative playlists you must have granted playlist-modify-private and playlist-modify-public scopes .
     */
    private Boolean collaborative;
    /**
     * value for playlist description as displayed in Spotify Clients and in the Web API.
     */
    private String description;
}