package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-playlistobject">PlaylistObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Playlist {
    /**
     * true if the owner allows other users to modify the playlist.
     */
    private Boolean collaborative;
    /**
     * Known external URLs for this playlist.
     */
    private ExternalUrl external_urls;
    /**
     * A link to the Web API endpoint providing full details of the playlist.
     */
    private String href;
    /**
     * The Spotify ID for the playlist.
     */
    private String id;
    /**
     * Images for the playlist. The array may be empty or contain up to three images. The images are returned by size in descending order. See Working with Playlists. Note: If returned, the source URL for the image (url) is temporary and will expire in less than a day.
     */
    private java.util.List<Image> images;
    /**
     * The name of the playlist.
     */
    private String name;
    /**
     * The user who owns the playlist
     */
    private PublicUser owner;
    /**
     * The playlist’s public/private status: true the playlist is public, false the playlist is private, null the playlist status is not relevant. For more about public/private status, see Working with Playlists
     */
    @com.fasterxml.jackson.annotation.JsonProperty("public")
    private Boolean _public;
    /**
     * The version identifier for the current playlist. Can be supplied in other requests to target a specific playlist version
     */
    private String snapshot_id;
    /**
     * A collection containing a link (href) to the Web API endpoint where full details of the playlist’s tracks can be retrieved, along with the total number of items in the playlist.
     */
    private java.util.List<PlaylistTrack> tracks;
    /**
     * The object type: “playlist”
     */
    private String type;
    /**
     * The Spotify URI for the playlist.
     */
    private String uri;
}