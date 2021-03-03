package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-playlists">Playlists API</a>
 */
public interface PlaylistsApi {

    /**
     * <h3>Add Items to a Playlist</h3>
     * <p>Add one or more items to a user's playlist.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param requestBody <p>the request body</p>
     * @return <p>On success, the HTTP status code in the response header is <code>201</code> Created. The response body contains a <code>snapshot_id</code> in JSON format. The <code>snapshot_id</code> can be used to identify your playlist version in future requests. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to add an item when you do not have the user's authorization, or when there are more than 10.000 items in the playlist, returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-add-tracks-to-playlist">Add Items to a Playlist</a>
     */
    @POST("/playlists/{playlist_id}/tracks")
    Call<SnapshotId> addTracksToPlaylist(@Path("playlist_id") String playlistId, @Body AddTracksToPlaylistRequest requestBody);

    /**
     * <h3>Add Items to a Playlist</h3>
     * <p>Add one or more items to a user's playlist.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param requestBody <p>the request body</p>
     * @param position <p>The position to insert the items, a zero-based index. For example, to insert the items in the first position: <code>position=0</code>; to insert the items in the third position: <code>position=2</code> . If omitted, the items will be appended to the playlist. Items are added in the order they are listed in the query string or request body.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>201</code> Created. The response body contains a <code>snapshot_id</code> in JSON format. The <code>snapshot_id</code> can be used to identify your playlist version in future requests. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to add an item when you do not have the user's authorization, or when there are more than 10.000 items in the playlist, returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-add-tracks-to-playlist">Add Items to a Playlist</a>
     */
    @POST("/playlists/{playlist_id}/tracks")
    Call<SnapshotId> addTracksToPlaylist(@Path("playlist_id") String playlistId, @Body AddTracksToPlaylistRequest requestBody, @Query("position") Integer position);

    /**
     * <h3>Change a Playlist's Details</h3>
     * <p>Change a playlist's name and public/private state. (The user must, of course, own the playlist.)</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @return <p>On success the HTTP status code in the response header is <code>200</code> OK.</p>
     *         <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to change a playlist when you do not have the user's authorization returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-change-playlist-details">Change a Playlist's Details</a>
     */
    @PUT("/playlists/{playlist_id}")
    Call<Void> changePlaylistDetails(@Path("playlist_id") String playlistId);

    /**
     * <h3>Change a Playlist's Details</h3>
     * <p>Change a playlist's name and public/private state. (The user must, of course, own the playlist.)</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param requestBody <p>The request body</p>
     * @return <p>On success the HTTP status code in the response header is <code>200</code> OK.</p>
     *         <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to change a playlist when you do not have the user's authorization returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-change-playlist-details">Change a Playlist's Details</a>
     */
    @PUT("/playlists/{playlist_id}")
    Call<Void> changePlaylistDetails(@Path("playlist_id") String playlistId, @Body ChangePlaylistDetailsRequest requestBody);

    /**
     * <h3>Create a Playlist</h3>
     * <p>Create a playlist for a Spotify user. (The playlist will be empty until you <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-add-tracks-to-playlist">add tracks</a>.)</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     *
     * @param userId <p>The user's <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify user ID</a>.</p>
     * @param requestBody <p>the request body</p>
     * @return <p>On success, the response body contains the created <a href="https://developer.spotify.com/documentation/web-api/reference/#object-playlistobject">playlist object</a>
     *         in JSON format and the HTTP status code in the response header is <code>200</code> OK or
     *         <code>201</code> Created. There is also a <code>Location</code> response header giving the Web API
     *         endpoint for the new playlist.</p>
     *         <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to create a playlist when you do not have the user's authorization returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-create-playlist">Create a Playlist</a>
     */
    @POST("/users/{user_id}/playlists")
    Call<Playlist> createPlaylist(@Path("user_id") String userId, @Body CreatePlaylistRequest requestBody);

    /**
     * <h3>Get a List of Current User's Playlists</h3>
     * <p>Get a list of the playlists owned or followed by the current Spotify user.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-read-private, playlist-read-collaborative</code>
     *
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedplaylistobject">playlist objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Please note that the access token has to be tied to a user.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-a-list-of-current-users-playlists">Get a List of Current User's Playlists</a>
     */
    @GET("/me/playlists")
    Call<Paging<SimplifiedPlaylist>> getListOfCurrentUsersPlaylists();

    /**
     * <h3>Get a List of Current User's Playlists</h3>
     * <p>Get a list of the playlists owned or followed by the current Spotify user.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-read-private, playlist-read-collaborative</code>
     *
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedplaylistobject">playlist objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Please note that the access token has to be tied to a user.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-a-list-of-current-users-playlists">Get a List of Current User's Playlists</a>
     */
    @GET("/me/playlists")
    Call<Paging<SimplifiedPlaylist>> getListOfCurrentUsersPlaylists(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get a List of a User's Playlists</h3>
     * <p>Get a list of the playlists owned or followed by a Spotify user.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-read-private, playlist-read-collaborative</code>
     *
     * @param userId <p>The user's <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify user ID</a>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedplaylistobject">playlist objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-list-users-playlists">Get a List of a User's Playlists</a>
     */
    @GET("/users/{user_id}/playlists")
    Call<Paging<SimplifiedPlaylist>> getListUsersPlaylists(@Path("user_id") String userId);

    /**
     * <h3>Get a List of a User's Playlists</h3>
     * <p>Get a list of the playlists owned or followed by a Spotify user.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-read-private, playlist-read-collaborative</code>
     *
     * @param userId <p>The user's <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify user ID</a>.</p>
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedplaylistobject">playlist objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-list-users-playlists">Get a List of a User's Playlists</a>
     */
    @GET("/users/{user_id}/playlists")
    Call<Paging<SimplifiedPlaylist>> getListUsersPlaylists(@Path("user_id") String userId, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get a Playlist</h3>
     * <p>Get a playlist owned by a Spotify user.</p>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @return <p>On success, the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-playlistobject">playlist object</a> in JSON format and the HTTP status code in the response header is <code>200</code> OK. If an episode is unavailable in the given <code>market</code>, its information will not be included in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Requesting playlists that you do not have the user's authorization to access returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-playlist">Get a Playlist</a>
     */
    @GET("/playlists/{playlist_id}")
    Call<Playlist> getPlaylist(@Path("playlist_id") String playlistId);

    /**
     * <h3>Get a Playlist</h3>
     * <p>Get a playlist owned by a Spotify user.</p>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-playlistobject">playlist object</a> in JSON format and the HTTP status code in the response header is <code>200</code> OK. If an episode is unavailable in the given <code>market</code>, its information will not be included in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Requesting playlists that you do not have the user's authorization to access returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-playlist">Get a Playlist</a>
     */
    @GET("/playlists/{playlist_id}")
    Call<Playlist> getPlaylist(@Path("playlist_id") String playlistId, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get a Playlist Cover Image</h3>
     * <p>Get the current image associated with a specific playlist.</p>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @return <p>On success, the response body contains a list of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-imageobject">image objects</a> in JSON format and the HTTP status code in the response header is <code>200</code> OK<br>
     *         On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-playlist-cover">Get a Playlist Cover Image</a>
     */
    @GET("/playlists/{playlist_id}/images")
    Call<java.util.List<Image>> getPlaylistCover(@Path("playlist_id") String playlistId);

    /**
     * <h3>Get a Playlist's Items</h3>
     * <p>Get full details of the items of a playlist owned by a Spotify user.</p>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>. For episodes, if a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br><em>Note: If neither market or user country are provided, the episode is considered unavailable for the client.</em></p>
     * @return <p>On success, the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedtrackobject">track objects</a> and <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedepisodeobject">episode objects</a> (depends on the <code>additional_types</code> parameter), wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a> in JSON format and the HTTP status code in the response header is <code>200</code> OK. If an episode is unavailable in the given <code>market</code>, its information will not be included in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Requesting playlists that you do not have the user's authorization to access returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-playlists-tracks">Get a Playlist's Items</a>
     */
    @GET("/playlists/{playlist_id}/tracks")
    Call<Paging<java.util.Map<String, Object>>> getPlaylistsTracks(@Path("playlist_id") String playlistId, @Query("market") String market);

    /**
     * <h3>Get a Playlist's Items</h3>
     * <p>Get full details of the items of a playlist owned by a Spotify user.</p>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>. For episodes, if a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br><em>Note: If neither market or user country are provided, the episode is considered unavailable for the client.</em></p>
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedtrackobject">track objects</a> and <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedepisodeobject">episode objects</a> (depends on the <code>additional_types</code> parameter), wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a> in JSON format and the HTTP status code in the response header is <code>200</code> OK. If an episode is unavailable in the given <code>market</code>, its information will not be included in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Requesting playlists that you do not have the user's authorization to access returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-playlists-tracks">Get a Playlist's Items</a>
     */
    @GET("/playlists/{playlist_id}/tracks")
    Call<Paging<java.util.Map<String, Object>>> getPlaylistsTracks(@Path("playlist_id") String playlistId, @Query("market") String market, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Remove Items from a Playlist</h3>
     * <p>Remove one or more items from a user's playlist.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     *
     * <h3>Notes</h3>
     * <h3>Frequently Asked Questions:</h3>
     * <ul>
     * <li>
     * <p><strong>Is it possible to delete a playlist?</strong>
     * No, it isn't. The reason there is no endpoint for this is explained in our Working With Playlists Guide in the section <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/#following-and-unfollowing-a-playlist">Following and Unfollowing a Playlist</a>.</p>
     * </li>
     * <li>
     * <p><strong>Can I use <code>X-HTTP-Method-Override</code> or similar to send a DELETE request overriding the HTTP verb?</strong>
     * Not at the moment, the delete operation needs to be specified through a DELETE request.</p>
     * </li>
     * </ul>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a></p>
     * @param requestBody <p>the request body</p>
     * @return <p>On success, the response body contains a <code>snapshot_id</code> in JSON format
     *         and the HTTP status code in the response header is <code>200</code> OK. The <code>snapshot_id</code>
     *         can be used to identify your playlist version in future requests.</p>
     *         <p>On error, the header status code is an <a href="https://developer.spotify.com/spotify-documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/spotify-documentation/web-api/#response-schema">error object</a>.
     *         Trying to remove an item when you do not have the user's authorization returns error <code>403</code> Forbidden.
     *         Attempting to use several different ways to remove items returns <code>400</code> Bad Request.
     *         Other client errors returning <code>400</code> Bad Request include specifying invalid positions.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-remove-tracks-playlist">Remove Items from a Playlist</a>
     */
    @DELETE("/playlists/{playlist_id}/tracks")
    Call<SnapshotId> removeTracksPlaylist(@Path("playlist_id") String playlistId, @Body RemoveTracksPlaylistRequest requestBody);

    /**
     * <h3>Reorder items in a playlist</h3>
     * <p>Reorder an item or a group of items in a playlist.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @return <p>On a successful <strong>reorder</strong> operation, the response body contains a <code>snapshot_id</code> in JSON format and the HTTP status code in the response header is <code>200</code> OK. The <code>snapshot_id</code> can be used to identify your playlist version in future requests.</p>
     *         <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a>, the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>, and the existing playlist is unmodified. Trying to set an item when you do not have the user's authorization returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-reorder-or-replace-playlists-tracks">Reorder items in a playlist</a>
     */
    @PUT("/playlists/{playlist_id}/tracks")
    Call<SnapshotId> reorderPlaylistsTracks(@Path("playlist_id") String playlistId);

    /**
     * <h3>Reorder items in a playlist</h3>
     * <p>Reorder an item or a group of items in a playlist.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param requestBody <p>The request body</p>
     * @return <p>On a successful <strong>reorder</strong> operation, the response body contains a <code>snapshot_id</code> in JSON format and the HTTP status code in the response header is <code>200</code> OK. The <code>snapshot_id</code> can be used to identify your playlist version in future requests.</p>
     *         <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a>, the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>, and the existing playlist is unmodified. Trying to set an item when you do not have the user's authorization returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-reorder-or-replace-playlists-tracks">Reorder items in a playlist</a>
     */
    @PUT("/playlists/{playlist_id}/tracks")
    Call<SnapshotId> reorderPlaylistsTracks(@Path("playlist_id") String playlistId, @Body ReorderPlaylistsTracksRequest requestBody);

    /**
     * <h3>Replace items in a playlist</h3>
     * <p>Replace all the items in a playlist, overwriting its existing items. This powerful request can be useful for replacing items, re-ordering existing items, or clearing the playlist.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param requestBody <p>the request body</p>
     * @return <p>On a successful <strong>replace</strong> operation, the HTTP status code in the response header is <code>201</code> Created.</p>
     *         <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a>, the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>, and the existing playlist is unmodified. Trying to set an item when you do not have the user's authorization returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-reorder-or-replace-playlists-tracks">Replace items in a playlist</a>
     */
    @PUT("/playlists/{playlist_id}/tracks")
    Call<Void> replacePlaylistsTracks(@Path("playlist_id") String playlistId, @Body ReplacePlaylistsTracksRequest requestBody);

    /**
     * <h3>Upload a Custom Playlist Cover Image</h3>
     * <p>Replace the image used to represent a specific playlist.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>ugc-image-upload, playlist-modify-public, playlist-modify-private</code>
     *
     * <h3>Notes</h3>
     * <p>The request should contain a Base64 encoded JPEG image data, maximum payload size is 256 KB.</p>
     * <p><strong>Rate Limiting:</strong> If you get status code <code>429</code>, it means that you have sent too many requests.
     * If this happens, have a look in the <code>Retry-After</code> header, where you will see a number displayed. This is the amount of seconds that you need to wait, before you can retry sending your requests.</p>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @return <p>If you get status code <code>429</code>, it means that you have sent too many requests.
     *         If this happens, have a look in the <code>Retry-After</code> header, where you will see a number displayed.
     *         This is the amount of seconds that you need to wait, before you can retry sending your requests.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-upload-custom-playlist-cover">Upload a Custom Playlist Cover Image</a>
     */
    @PUT("/playlists/{playlist_id}/images")
    Call<Void> uploadCustomPlaylistCover(@Path("playlist_id") String playlistId);
}
