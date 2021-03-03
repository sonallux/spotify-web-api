package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-playlistobject">PlaylistObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Playlist {
    /**
     * <p><code>true</code> if the owner allows other users to modify the playlist.</p>
     */
    private Boolean collaborative;
    /**
     * <p>The playlist description. <em>Only returned for modified, verified playlists, otherwise</em> <code>null</code>.</p>
     */
    private String description;
    /**
     * <p>Known external URLs for this playlist.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("external_urls")
    private ExternalUrl externalUrls;
    /**
     * <p>Information about the followers of the playlist.</p>
     */
    private Followers followers;
    /**
     * <p>A link to the Web API endpoint providing full details of the playlist.</p>
     */
    private String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     */
    private String id;
    /**
     * <p>Images for the playlist. The array may be empty or contain up to three images. The images are returned by size in descending order. See <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/">Working with Playlists</a>. <em>Note: If returned, the source URL for the image (<code>url</code>) is temporary and will expire in less than a day.</em></p>
     */
    private java.util.List<Image> images;
    /**
     * <p>The name of the playlist.</p>
     */
    private String name;
    /**
     * <p>The user who owns the playlist</p>
     */
    private PublicUser owner;
    /**
     * <p>The playlist's public/private status: <code>true</code> the playlist is public, <code>false</code> the playlist is private, <code>null</code> the playlist status is not relevant. For more about public/private status, see <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/">Working with Playlists</a></p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("public")
    @lombok.experimental.Accessors(prefix = "_")
    private Boolean _public;
    /**
     * <p>The version identifier for the current playlist. Can be supplied in other requests to target a specific playlist version</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("snapshot_id")
    private String snapshotId;
    /**
     * <p>Information about the tracks of the playlist. Note, a track object may be <code>null</code>. This can happen if a track is no longer available.</p>
     */
    private Paging<PlaylistTrack> tracks;
    /**
     * <p>The object type: &quot;playlist&quot;</p>
     */
    private String type;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the playlist.</p>
     */
    private String uri;
}
