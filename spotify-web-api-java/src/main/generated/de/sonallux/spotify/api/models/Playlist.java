package de.sonallux.spotify.api.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-playlistobject">PlaylistObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE) // Disable deserialization based on @JsonTypeInfo
public class Playlist extends BaseObject {
    /**
     * <p><code>true</code> if the owner allows other users to modify the playlist.</p>
     */
    public boolean collaborative;
    /**
     * <p>The playlist description. <em>Only returned for modified, verified playlists, otherwise</em> <code>null</code>.</p>
     */
    public String description;
    /**
     * <p>Known external URLs for this playlist.</p>
     */
    public ExternalUrl externalUrls;
    /**
     * <p>Information about the followers of the playlist.</p>
     */
    public Followers followers;
    /**
     * <p>Images for the playlist. The array may be empty or contain up to three images. The images are returned by size in descending order. See <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/">Working with Playlists</a>. <em>Note: If returned, the source URL for the image (<code>url</code>) is temporary and will expire in less than a day.</em></p>
     */
    public java.util.List<Image> images;
    /**
     * <p>The name of the playlist.</p>
     */
    public String name;
    /**
     * <p>The user who owns the playlist</p>
     */
    public PublicUser owner;
    /**
     * <p>The playlist's public/private status: <code>true</code> the playlist is public, <code>false</code> the playlist is private, <code>null</code> the playlist status is not relevant. For more about public/private status, see <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/">Working with Playlists</a></p>
     */
    @lombok.experimental.Accessors(prefix = "_")
    public boolean _public;
    /**
     * <p>The version identifier for the current playlist. Can be supplied in other requests to target a specific playlist version</p>
     */
    public String snapshotId;
    /**
     * <p>Information about the tracks of the playlist. Note, a track object may be <code>null</code>. This can happen if a track is no longer available.</p>
     */
    public Paging<PlaylistTrack> tracks;
}
