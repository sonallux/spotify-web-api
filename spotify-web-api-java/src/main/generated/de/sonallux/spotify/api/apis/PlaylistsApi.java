package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.apis.playlists.*;
import lombok.RequiredArgsConstructor;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-playlists">Playlists API</a>
 */
@RequiredArgsConstructor
public class PlaylistsApi {
    private final ApiClient apiClient;

    /**
     * <h3>Add Items to a Playlist</h3>
     * <p>Add one or more items to a user's playlist.</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param uris <p>A JSON array of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URIs</a> to add. For example: <code>{&quot;uris&quot;: [&quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot;,&quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;, &quot;spotify:episode:512ojhOuo1ktJprKbVcKyQ&quot;]}</code><br>A maximum of 100 items can be added in one request. <em>Note: if the <code>uris</code> parameter is present in the query string, any URIs listed here in the body will be ignored.</em></p>
     * @param position <p>The position to insert the items, a zero-based index. For example, to insert the items in the first position: <code>position=0</code> ; to insert the items in the third position: <code>position=2</code>. If omitted, the items will be appended to the playlist. Items are added in the order they appear in the uris array. For example: <code>{&quot;uris&quot;: [&quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot;,&quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;], &quot;position&quot;: 3}</code></p>
     * @return a {@link AddTracksToPlaylistRequest} object to build and execute the request
     */
    public AddTracksToPlaylistRequest addTracksToPlaylist(String playlistId, java.util.List<String> uris, int position) {
        return new AddTracksToPlaylistRequest(apiClient, playlistId, uris, position);
    }

    /**
     * <h3>Change a Playlist's Details</h3>
     * <p>Change a playlist's name and public/private state. (The user must, of course, own the playlist.)</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @return a {@link ChangePlaylistDetailsRequest} object to build and execute the request
     */
    public ChangePlaylistDetailsRequest changePlaylistDetails(String playlistId) {
        return new ChangePlaylistDetailsRequest(apiClient, playlistId);
    }

    /**
     * <h3>Create a Playlist</h3>
     * <p>Create a playlist for a Spotify user. (The playlist will be empty until you <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-add-tracks-to-playlist">add tracks</a>.)</p>
     * @param userId <p>The user's <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify user ID</a>.</p>
     * @param name <p>The name for the new playlist, for example <code>&quot;Your Coolest Playlist&quot;</code> . This name does not need to be unique; a user may have several playlists with the same name.</p>
     * @return a {@link CreatePlaylistRequest} object to build and execute the request
     */
    public CreatePlaylistRequest createPlaylist(String userId, String name) {
        return new CreatePlaylistRequest(apiClient, userId, name);
    }

    /**
     * <h3>Get a List of Current User's Playlists</h3>
     * <p>Get a list of the playlists owned or followed by the current Spotify user.</p>
     * @return a {@link GetListOfCurrentUsersPlaylistsRequest} object to build and execute the request
     */
    public GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylists() {
        return new GetListOfCurrentUsersPlaylistsRequest(apiClient);
    }

    /**
     * <h3>Get a List of a User's Playlists</h3>
     * <p>Get a list of the playlists owned or followed by a Spotify user.</p>
     * @param userId <p>The user's <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify user ID</a>.</p>
     * @return a {@link GetListUsersPlaylistsRequest} object to build and execute the request
     */
    public GetListUsersPlaylistsRequest getListUsersPlaylists(String userId) {
        return new GetListUsersPlaylistsRequest(apiClient, userId);
    }

    /**
     * <h3>Get a Playlist</h3>
     * <p>Get a playlist owned by a Spotify user.</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @return a {@link GetPlaylistRequest} object to build and execute the request
     */
    public GetPlaylistRequest getPlaylist(String playlistId) {
        return new GetPlaylistRequest(apiClient, playlistId);
    }

    /**
     * <h3>Get a Playlist Cover Image</h3>
     * <p>Get the current image associated with a specific playlist.</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @return a {@link GetPlaylistCoverRequest} object to build and execute the request
     */
    public GetPlaylistCoverRequest getPlaylistCover(String playlistId) {
        return new GetPlaylistCoverRequest(apiClient, playlistId);
    }

    /**
     * <h3>Get a Playlist's Items</h3>
     * <p>Get full details of the items of a playlist owned by a Spotify user.</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>. For episodes, if a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br><em>Note: If neither market or user country are provided, the episode is considered unavailable for the client.</em></p>
     * @return a {@link GetPlaylistsTracksRequest} object to build and execute the request
     */
    public GetPlaylistsTracksRequest getPlaylistsTracks(String playlistId, String market) {
        return new GetPlaylistsTracksRequest(apiClient, playlistId, market);
    }

    /**
     * <h3>Remove Items from a Playlist</h3>
     * <p>Remove one or more items from a user's playlist.</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a></p>
     * @param tracks <p>An array of objects containing <a href="https://developer.spotify.com/spotify-documentation/web-api/#spotify-uris-and-ids">Spotify URIs</a> of the tracks or episodes to remove. For example: <code>{ &quot;tracks&quot;: [{ &quot;uri&quot;: &quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot; },{ &quot;uri&quot;: &quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot; }] }</code>. A maximum of 100 objects can be sent at once.</p>
     * @return a {@link RemoveTracksPlaylistRequest} object to build and execute the request
     */
    public RemoveTracksPlaylistRequest removeTracksPlaylist(String playlistId, java.util.List<String> tracks) {
        return new RemoveTracksPlaylistRequest(apiClient, playlistId, tracks);
    }

    /**
     * <h3>Reorder items in a playlist</h3>
     * <p>Reorder an item or a group of items in a playlist.</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param rangeStart <p>The position of the first item to be reordered.</p>
     * @param insertBefore <p>The position where the items should be inserted.<br>To reorder the items to the end of the playlist, simply set <em>insert_before</em> to the position after the last item.<br>Examples:<br>To reorder the first item to the last position in a playlist with 10 items, set <em>range_start</em> to 0, and <em>insert_before</em> to 10.<br>To reorder the last item in a playlist with 10 items to the start of the playlist, set <em>range_start</em> to 9, and <em>insert_before</em> to 0.</p>
     * @return a {@link ReorderPlaylistsTracksRequest} object to build and execute the request
     */
    public ReorderPlaylistsTracksRequest reorderPlaylistsTracks(String playlistId, int rangeStart, int insertBefore) {
        return new ReorderPlaylistsTracksRequest(apiClient, playlistId, rangeStart, insertBefore);
    }

    /**
     * <h3>Replace items in a playlist</h3>
     * <p>Replace all the items in a playlist, overwriting its existing items. This powerful request can be useful for replacing items, re-ordering existing items, or clearing the playlist.</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param uris <p>A comma-separated list of <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URIs</a> to set, can be track or episode URIs. For example: <code>uris=spotify:track:4iV5W9uYEdYUVa79Axb7Rh,spotify:track:1301WleyT98MSxVHPZCA6M,spotify:episode:512ojhOuo1ktJprKbVcKyQ</code><br>A maximum of 100 items can be set in one request.</p>
     * @return a {@link ReplacePlaylistsTracksRequest} object to build and execute the request
     */
    public ReplacePlaylistsTracksRequest replacePlaylistsTracks(String playlistId, java.util.List<String> uris) {
        return new ReplacePlaylistsTracksRequest(apiClient, playlistId, uris);
    }

    /**
     * <h3>Upload a Custom Playlist Cover Image</h3>
     * <p>Replace the image used to represent a specific playlist.</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @return a {@link UploadCustomPlaylistCoverRequest} object to build and execute the request
     */
    public UploadCustomPlaylistCoverRequest uploadCustomPlaylistCover(String playlistId) {
        return new UploadCustomPlaylistCoverRequest(apiClient, playlistId);
    }
}
