package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-library">Library API</a>
 */
public interface LibraryApi {

    /**
     * <h3>Check User's Saved Albums</h3>
     * Check if one or more albums is already saved in the current Spotify user’s ‘Your Music’ library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     * 
     * @param ids A comma-separated list of the Spotify IDs for the albums. Maximum: 50 IDs.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains a JSON array of true or false values, in the same order in which the ids were specified. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-check-users-saved-albums">Check User's Saved Albums</a>
     */
    @GET("/me/albums/contains")
    Call<java.util.List<Boolean>> checkUsersSavedAlbums(@Query("ids") String ids);

    /**
     * <h3>Check User's Saved Shows</h3>
     * Check if one or more shows is already saved in the current Spotify user’s library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     * 
     * @param ids A comma-separated list of the Spotify IDs for the shows. Maximum: 50 ids.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains a JSON array of trueor false values, in the same order in which the ids were specified. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-check-users-saved-shows">Check User's Saved Shows</a>
     */
    @GET("/me/shows/contains")
    Call<java.util.List<Boolean>> checkUsersSavedShows(@Query("ids") String ids);

    /**
     * <h3>Check User's Saved Tracks</h3>
     * Check if one or more tracks is already saved in the current Spotify user’s ‘Your Music’ library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     * 
     * @param ids A comma-separated list of the Spotify IDs for the tracks. Maximum: 50 IDs.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains a JSON array of true or false values, in the same order in which the ids were specified. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-check-users-saved-tracks">Check User's Saved Tracks</a>
     */
    @GET("/me/tracks/contains")
    Call<java.util.List<Boolean>> checkUsersSavedTracks(@Query("ids") String ids);

    /**
     * <h3>Get User's Saved Albums</h3>
     * Get a list of the albums saved in the current Spotify user’s ‘Your Music’ library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     * 
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of saved album objects (wrapped in a paging object) in JSON format. Each album object is accompanied by a timestamp (added_at) to show when it was added. There is also an etag in the header that can be used in future conditional requests. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-saved-albums">Get User's Saved Albums</a>
     */
    @GET("/me/albums")
    Call<Paging<SavedAlbum>> getUsersSavedAlbums();

    /**
     * <h3>Get User's Saved Albums</h3>
     * Get a list of the albums saved in the current Spotify user’s ‘Your Music’ library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     * 
     * @param queryParameters A map of optional query parameters
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of saved album objects (wrapped in a paging object) in JSON format. Each album object is accompanied by a timestamp (added_at) to show when it was added. There is also an etag in the header that can be used in future conditional requests. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-saved-albums">Get User's Saved Albums</a>
     */
    @GET("/me/albums")
    Call<Paging<SavedAlbum>> getUsersSavedAlbums(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get User's Saved Shows</h3>
     * Get a list of shows saved in the current Spotify user’s library. Optional parameters can be used to limit the number of shows returned.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     * 
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of saved show objects (wrapped in a paging object) in JSON format. If the current user has no shows saved, the response will be an empty array. If a show is unavailable in the given market it is filtered out. The total field in the paging object represents the number of all items, filtered or not, and thus might be larger than the actual total number of observable items. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-saved-shows">Get User's Saved Shows</a>
     */
    @GET("/me/shows")
    Call<Paging<SavedShow>> getUsersSavedShows();

    /**
     * <h3>Get User's Saved Shows</h3>
     * Get a list of shows saved in the current Spotify user’s library. Optional parameters can be used to limit the number of shows returned.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     * 
     * @param queryParameters A map of optional query parameters
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of saved show objects (wrapped in a paging object) in JSON format. If the current user has no shows saved, the response will be an empty array. If a show is unavailable in the given market it is filtered out. The total field in the paging object represents the number of all items, filtered or not, and thus might be larger than the actual total number of observable items. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-saved-shows">Get User's Saved Shows</a>
     */
    @GET("/me/shows")
    Call<Paging<SavedShow>> getUsersSavedShows(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get User's Saved Tracks</h3>
     * Get a list of the songs saved in the current Spotify user’s ‘Your Music’ library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     * 
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of saved track objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-saved-tracks">Get User's Saved Tracks</a>
     */
    @GET("/me/tracks")
    Call<Paging<SavedTrack>> getUsersSavedTracks();

    /**
     * <h3>Get User's Saved Tracks</h3>
     * Get a list of the songs saved in the current Spotify user’s ‘Your Music’ library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     * 
     * @param queryParameters A map of optional query parameters
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of saved track objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-saved-tracks">Get User's Saved Tracks</a>
     */
    @GET("/me/tracks")
    Call<Paging<SavedTrack>> getUsersSavedTracks(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Remove Albums for Current User</h3>
     * Remove one or more albums from the current user’s ‘Your Music’ library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     * 
     * @param requestBody the request body
     * @return On success, the HTTP status code in the response header is 200 Success. On error, the header status code is an error code and the response body contains an error object. Trying to remove an album when you do not have the user’s authorization returns error 403 Forbidden.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-remove-albums-user">Remove Albums for Current User</a>
     */
    @DELETE("/me/albums")
    Call<Void> removeAlbumsUser(@Body RemoveAlbumsUserRequest requestBody);

    /**
     * <h3>Remove User's Saved Tracks</h3>
     * Delete one or more shows from current Spotify user’s library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     * 
     * @param ids A comma-separated list of Spotify IDs for the shows to be deleted from the user’s library.
     * @return On success, the HTTP status code in the response header is 200 OK. On error, the header status code is an error code and the response body contains an error object. A 403 Forbidden while trying to add a show when you do not have the user’s authorisation.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-remove-shows-user">Remove User's Saved Tracks</a>
     */
    @DELETE("/me/shows")
    Call<Void> removeShowsUser(@Query("ids") String ids);

    /**
     * <h3>Remove User's Saved Tracks</h3>
     * Delete one or more shows from current Spotify user’s library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     * 
     * @param ids A comma-separated list of Spotify IDs for the shows to be deleted from the user’s library.
     * @param market An ISO 3166-1 alpha-2 country code. If a country code is specified, only shows that are available in that market will be removed. If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter. Note: If neither market or user country are provided, the content is considered unavailable for the client. Users can view the country that is associated with their account in the account settings.
     * @return On success, the HTTP status code in the response header is 200 OK. On error, the header status code is an error code and the response body contains an error object. A 403 Forbidden while trying to add a show when you do not have the user’s authorisation.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-remove-shows-user">Remove User's Saved Tracks</a>
     */
    @DELETE("/me/shows")
    Call<Void> removeShowsUser(@Query("ids") String ids, @Query("market") String market);

    /**
     * <h3>Remove User's Saved Tracks</h3>
     * Remove one or more tracks from the current user’s ‘Your Music’ library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     * 
     * @param ids A comma-separated list of the Spotify IDs. For example: ids&#x3D;4iV5W9uYEdYUVa79Axb7Rh,1301WleyT98MSxVHPZCA6M. Maximum: 50 IDs.
     * @return On success, the HTTP status code in the response header is 200 Success. On error, the header status code is an error code and the response body contains an error object. Trying to remove an album when you do not have the user’s authorization returns error 403 Forbidden.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-remove-tracks-user">Remove User's Saved Tracks</a>
     */
    @DELETE("/me/tracks")
    Call<Void> removeTracksUser(@Query("ids") String ids);

    /**
     * <h3>Save Albums for Current User</h3>
     * Save one or more albums to the current user’s ‘Your Music’ library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     * 
     * @param requestBody the request body
     * @return On success, the HTTP status code in the response header is 201 Created. On error, the header status code is an error code and the response body contains an error object. Trying to add an album when you do not have the user’s authorization returns error 403 Forbidden.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-save-albums-user">Save Albums for Current User</a>
     */
    @PUT("/me/albums")
    Call<Void> saveAlbumsUser(@Body SaveAlbumsUserRequest requestBody);

    /**
     * <h3>Save Shows for Current User</h3>
     * Save one or more shows to current Spotify user’s library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     * 
     * @param ids A comma-separated list of Spotify IDs for the shows to be added to the user’s library.
     * @return On success, the HTTP status code in the response header is 200 OK. On error, the header status code is an error code and the response body contains an error object. A 403 Forbidden while trying to add a show when you do not have the user’s authorisation or when the user already has have over 10,000 items saved in library.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-save-shows-user">Save Shows for Current User</a>
     */
    @PUT("/me/shows")
    Call<Void> saveShowsUser(@Query("ids") String ids);

    /**
     * <h3>Save Tracks for User</h3>
     * Save one or more tracks to the current user’s ‘Your Music’ library.
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     * 
     * @param ids A comma-separated list of the Spotify IDs. For example: ids&#x3D;4iV5W9uYEdYUVa79Axb7Rh,1301WleyT98MSxVHPZCA6M. Maximum: 50 IDs.
     * @return On success, the HTTP status code in the response header is 200 OK. On error, the header status code is an error code and the response body contains an error object. Trying to add a track when you do not have the user’s authorization, or when you have over 10.000 tracks in Your Music, returns error 403 Forbidden.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-save-tracks-user">Save Tracks for User</a>
     */
    @PUT("/me/tracks")
    Call<Void> saveTracksUser(@Query("ids") String ids);
}