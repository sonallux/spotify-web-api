package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-playlists">Playlists API</a>
 */
public interface PlaylistsApi {

    /**
     * <h3>Add Items to a Playlist</h3>
     * Add one or more items to a user’s playlist.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 201 Created. The response body contains a snapshot_id in JSON format. The snapshot_id can be used to identify your playlist version in future requests. On error, the header status code is an error code and the response body contains an error object. Trying to add an item when you do not have the user’s authorization, or when there are more than 10.000 items in the playlist, returns error 403 Forbidden.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @param requestBody the request body
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-add-tracks-to-playlist">Add Items to a Playlist</a>
     */
    @POST("/playlists/{playlist_id}/tracks")
    Call<SnapshotId> addTracksToPlaylist(@Path("playlist_id") String playlist_id, @Body AddTracksToPlaylistRequest requestBody);

    /**
     * <h3>Add Items to a Playlist</h3>
     * Add one or more items to a user’s playlist.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 201 Created. The response body contains a snapshot_id in JSON format. The snapshot_id can be used to identify your playlist version in future requests. On error, the header status code is an error code and the response body contains an error object. Trying to add an item when you do not have the user’s authorization, or when there are more than 10.000 items in the playlist, returns error 403 Forbidden.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @param requestBody the request body
     * @param position The position to insert the items, a zero-based index. For example, to insert the items in the first position: position=0; to insert the items in the third position: position=2 . If omitted, the items will be appended to the playlist. Items are added in the order they are listed in the query string or request body.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-add-tracks-to-playlist">Add Items to a Playlist</a>
     */
    @POST("/playlists/{playlist_id}/tracks")
    Call<SnapshotId> addTracksToPlaylist(@Path("playlist_id") String playlist_id, @Body AddTracksToPlaylistRequest requestBody, @Query("position") Integer position);

    /**
     * <h3>Change a Playlist's Details</h3>
     * Change a playlist’s name and public/private state. (The user must, of course, own the playlist.)
     * <h3>Response</h3>
     * On success the HTTP status code in the response header is 200 OK. On error, the header status code is an error code and the response body contains an error object. Trying to change a playlist when you do not have the user’s authorization returns error 403 Forbidden.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-change-playlist-details">Change a Playlist's Details</a>
     */
    @PUT("/playlists/{playlist_id}")
    Call<Void> changePlaylistDetails(@Path("playlist_id") String playlist_id);

    /**
     * <h3>Change a Playlist's Details</h3>
     * Change a playlist’s name and public/private state. (The user must, of course, own the playlist.)
     * <h3>Response</h3>
     * On success the HTTP status code in the response header is 200 OK. On error, the header status code is an error code and the response body contains an error object. Trying to change a playlist when you do not have the user’s authorization returns error 403 Forbidden.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @param requestBody The request body
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-change-playlist-details">Change a Playlist's Details</a>
     */
    @PUT("/playlists/{playlist_id}")
    Call<Void> changePlaylistDetails(@Path("playlist_id") String playlist_id, @Body ChangePlaylistDetailsRequest requestBody);

    /**
     * <h3>Create a Playlist</h3>
     * Create a playlist for a Spotify user. (The playlist will be empty until you add tracks.)
     * <h3>Response</h3>
     * On success, the response body contains the created playlist object in JSON format and the HTTP status code in the response header is 200 OK or 201 Created. There is also a Location response header giving the Web API endpoint for the new playlist. On error, the header status code is an error code and the response body contains an error object. Trying to create a playlist when you do not have the user’s authorization returns error 403 Forbidden.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     * 
     * @param user_id The user’s Spotify user ID.
     * @param requestBody the request body
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-create-playlist">Create a Playlist</a>
     */
    @POST("/users/{user_id}/playlists")
    Call<Playlist> createPlaylist(@Path("user_id") String user_id, @Body CreatePlaylistRequest requestBody);

    /**
     * <h3>Get a List of Current User's Playlists</h3>
     * Get a list of the playlists owned or followed by the current Spotify user.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an array of simplified playlist objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. Please note that the access token has to be tied to a user.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-read-private, playlist-read-collaborative</code>
     * 
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-list-of-current-users-playlists">Get a List of Current User's Playlists</a>
     */
    @GET("/me/playlists")
    Call<Paging<SimplifiedPlaylist>> getListOfCurrentUsersPlaylists();

    /**
     * <h3>Get a List of Current User's Playlists</h3>
     * Get a list of the playlists owned or followed by the current Spotify user.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an array of simplified playlist objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. Please note that the access token has to be tied to a user.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-read-private, playlist-read-collaborative</code>
     * 
     * @param queryParameters A map of optional query parameters
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-list-of-current-users-playlists">Get a List of Current User's Playlists</a>
     */
    @GET("/me/playlists")
    Call<Paging<SimplifiedPlaylist>> getListOfCurrentUsersPlaylists(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get a List of a User's Playlists</h3>
     * Get a list of the playlists owned or followed by a Spotify user.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an array of simplified playlist objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-read-private, playlist-read-collaborative</code>
     * 
     * @param user_id The user’s Spotify user ID.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-list-users-playlists">Get a List of a User's Playlists</a>
     */
    @GET("/users/{user_id}/playlists")
    Call<Paging<SimplifiedPlaylist>> getListUsersPlaylists(@Path("user_id") String user_id);

    /**
     * <h3>Get a List of a User's Playlists</h3>
     * Get a list of the playlists owned or followed by a Spotify user.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an array of simplified playlist objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-read-private, playlist-read-collaborative</code>
     * 
     * @param user_id The user’s Spotify user ID.
     * @param queryParameters A map of optional query parameters
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-list-users-playlists">Get a List of a User's Playlists</a>
     */
    @GET("/users/{user_id}/playlists")
    Call<Paging<SimplifiedPlaylist>> getListUsersPlaylists(@Path("user_id") String user_id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get a Playlist</h3>
     * Get a playlist owned by a Spotify user.
     * <h3>Response</h3>
     * On success, the response body contains a playlist object in JSON format and the HTTP status code in the response header is 200 OK. If an episode is unavailable in the given market, its information will not be included in the response. On error, the header status code is an error code and the response body contains an error object. Requesting playlists that you do not have the user’s authorization to access returns error 403 Forbidden.
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-playlist">Get a Playlist</a>
     */
    @GET("/playlists/{playlist_id}")
    Call<Playlist> getPlaylist(@Path("playlist_id") String playlist_id);

    /**
     * <h3>Get a Playlist</h3>
     * Get a playlist owned by a Spotify user.
     * <h3>Response</h3>
     * On success, the response body contains a playlist object in JSON format and the HTTP status code in the response header is 200 OK. If an episode is unavailable in the given market, its information will not be included in the response. On error, the header status code is an error code and the response body contains an error object. Requesting playlists that you do not have the user’s authorization to access returns error 403 Forbidden.
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @param queryParameters A map of optional query parameters
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-playlist">Get a Playlist</a>
     */
    @GET("/playlists/{playlist_id}")
    Call<Playlist> getPlaylist(@Path("playlist_id") String playlist_id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get a Playlist Cover Image</h3>
     * Get the current image associated with a specific playlist.
     * <h3>Response</h3>
     * On success, the response body contains a list of image objects in JSON format and the HTTP status code in the response header is 200 OK On error, the header status code is an error code and the response body contains an error object.
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-playlist-cover">Get a Playlist Cover Image</a>
     */
    @GET("/playlists/{playlist_id}/images")
    Call<java.util.List<Image>> getPlaylistCover(@Path("playlist_id") String playlist_id);

    /**
     * <h3>Get a Playlist's Items</h3>
     * Get full details of the items of a playlist owned by a Spotify user.
     * <h3>Response</h3>
     * On success, the response body contains an array of track objects and episode objects (depends on the additional_types parameter), wrapped in a paging object in JSON format and the HTTP status code in the response header is 200 OK. If an episode is unavailable in the given market, its information will not be included in the response. On error, the header status code is an error code and the response body contains an error object. Requesting playlists that you do not have the user’s authorization to access returns error 403 Forbidden.
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @param market An ISO 3166-1 alpha-2 country code or the string from_token. Provide this parameter if you want to apply Track Relinking. For episodes, if a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter. Note: If neither market or user country are provided, the episode is considered unavailable for the client.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-playlists-tracks">Get a Playlist's Items</a>
     */
    @GET("/playlists/{playlist_id}/tracks")
    Call<Paging<java.util.Map<String, Object>>> getPlaylistsTracks(@Path("playlist_id") String playlist_id, @Query("market") String market);

    /**
     * <h3>Get a Playlist's Items</h3>
     * Get full details of the items of a playlist owned by a Spotify user.
     * <h3>Response</h3>
     * On success, the response body contains an array of track objects and episode objects (depends on the additional_types parameter), wrapped in a paging object in JSON format and the HTTP status code in the response header is 200 OK. If an episode is unavailable in the given market, its information will not be included in the response. On error, the header status code is an error code and the response body contains an error object. Requesting playlists that you do not have the user’s authorization to access returns error 403 Forbidden.
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @param market An ISO 3166-1 alpha-2 country code or the string from_token. Provide this parameter if you want to apply Track Relinking. For episodes, if a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter. Note: If neither market or user country are provided, the episode is considered unavailable for the client.
     * @param queryParameters A map of optional query parameters
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-playlists-tracks">Get a Playlist's Items</a>
     */
    @GET("/playlists/{playlist_id}/tracks")
    Call<Paging<java.util.Map<String, Object>>> getPlaylistsTracks(@Path("playlist_id") String playlist_id, @Query("market") String market, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Remove Items from a Playlist</h3>
     * Remove one or more items from a user’s playlist.
     * <h3>Response</h3>
     * On success, the response body contains a snapshot_id in JSON format and the HTTP status code in the response header is 200 OK. The snapshot_id can be used to identify your playlist version in future requests. On error, the header status code is an error code and the response body contains an error object. Trying to remove an item when you do not have the user’s authorization returns error 403 Forbidden. Attempting to use several different ways to remove items returns 400 Bad Request. Other client errors returning 400 Bad Request include specifying invalid positions.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @param requestBody the request body
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-remove-tracks-playlist">Remove Items from a Playlist</a>
     */
    @DELETE("/playlists/{playlist_id}/tracks")
    Call<SnapshotId> removeTracksPlaylist(@Path("playlist_id") String playlist_id, @Body RemoveTracksPlaylistRequest requestBody);

    /**
     * <h3>Reorder a Playlist's Items</h3>
     * Reorder an item or a group of items in a playlist.
     * <h3>Response</h3>
     * On success, the response body contains a snapshot_id in JSON format and the HTTP status code in the response header is 200 OK. The snapshot_id can be used to identify your playlist version in future requests. On error, the header status code is an error code, the response body contains an error object, and the existing playlist is unmodified.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @param requestBody the request body
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-reorder-playlists-tracks">Reorder a Playlist's Items</a>
     */
    @PUT("/playlists/{playlist_id}/tracks")
    Call<SnapshotId> reorderPlaylistsTracks(@Path("playlist_id") String playlist_id, @Body ReorderPlaylistsTracksRequest requestBody);

    /**
     * <h3>Replace a Playlist's Items</h3>
     * Replace all the items in a playlist, overwriting its existing items. This powerful request can be useful for replacing items, re-ordering existing items, or clearing the playlist.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 201 Created. On error, the header status code is an error code, the response body contains an error object, and the existing playlist is unmodified. Trying to set an item when you do not have the user’s authorization returns error 403 Forbidden.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @param requestBody the request body
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-replace-playlists-tracks">Replace a Playlist's Items</a>
     */
    @PUT("/playlists/{playlist_id}/tracks")
    Call<Void> replacePlaylistsTracks(@Path("playlist_id") String playlist_id, @Body ReplacePlaylistsTracksRequest requestBody);

    /**
     * <h3>Upload a Custom Playlist Cover Image</h3>
     * Replace the image used to represent a specific playlist.
     * <h3>Response</h3>
     * If you get status code 429, it means that you have sent too many requests. If this happens, have a look in the Retry-After header, where you will see a number displayed. This is the amount of seconds that you need to wait, before you can retry sending your requests.
     * <h3>Required OAuth scopes</h3>
     * <code>ugc-image-upload, playlist-modify-public, playlist-modify-private</code>
     * 
     * @param playlist_id The Spotify ID for the playlist.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-upload-custom-playlist-cover">Upload a Custom Playlist Cover Image</a>
     */
    @PUT("/playlists/{playlist_id}/images")
    Call<Void> uploadCustomPlaylistCover(@Path("playlist_id") String playlist_id);
}