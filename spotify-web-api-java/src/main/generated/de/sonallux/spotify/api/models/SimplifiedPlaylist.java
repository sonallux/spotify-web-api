package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedplaylistobject">SimplifiedPlaylistObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class SimplifiedPlaylist {
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
     * <p>A link to the Web API endpoint providing full details of the playlist.</p>
     */
    public String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     */
    public String id;
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
     * <p>A collection containing a link ( <code>href</code> ) to the Web API endpoint where full details of the playlist's tracks can be retrieved, along with the <code>total</code> number of tracks in the playlist. Note, a track object may be <code>null</code>. This can happen if a track is no longer available.</p>
     */
    public PlaylistTracksRef tracks;
    /**
     * <p>The object type: &quot;playlist&quot;</p>
     */
    public String type;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the playlist.</p>
     */
    public String uri;
}
