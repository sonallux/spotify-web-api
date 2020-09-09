package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ChangePlaylistDetailsRequest {
    /**
     * The new name for the playlist, for example &quot;My New Playlist Title&quot;
     */
    private String name;
    /**
     * If true the playlist will be public, if false it will be private.
     */
    @com.fasterxml.jackson.annotation.JsonProperty("public")
    private Boolean _public;
    /**
     * If true , the playlist will become collaborative and other users will be able to modify the playlist in their Spotify client. Note: You can only set collaborative to true on non-public playlists.
     */
    private Boolean collaborative;
    /**
     * Value for playlist description as displayed in Spotify Clients and in the Web API.
     */
    private String description;
}