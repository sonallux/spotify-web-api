package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-library">Library API</a>
 */
public interface LibraryApi {

    /**
     * <h3>Check User's Saved Albums</h3>
     * <p>Check if one or more albums is already saved in the current Spotify user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     *
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the albums. Maximum: 50 IDs.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a JSON array of <code>true</code> or <code>false</code> values, in the same order in which the <code>ids</code> were specified. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-check-users-saved-albums">Check User's Saved Albums</a>
     */
    @GET("/me/albums/contains")
    Call<java.util.List<Boolean>> checkUsersSavedAlbums(@Query("ids") String ids);

    /**
     * <h3>Check User's Saved Shows</h3>
     * <p>Check if one or more shows is already saved in the current Spotify user's library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     *
     * @param ids <p>A comma-separated list of the Spotify IDs for the shows. Maximum: 50 ids.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a JSON array of <code>true</code>or <code>false</code> values, in the same order in which the <code>ids</code> were specified. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#error-details">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-check-users-saved-shows">Check User's Saved Shows</a>
     */
    @GET("/me/shows/contains")
    Call<java.util.List<Boolean>> checkUsersSavedShows(@Query("ids") String ids);

    /**
     * <h3>Check User's Saved Tracks</h3>
     * <p>Check if one or more tracks is already saved in the current Spotify user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     *
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the tracks. Maximum: 50 IDs.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a JSON array of <code>true</code> or <code>false</code> values, in the same order in which the <code>ids</code> were specified. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-check-users-saved-tracks">Check User's Saved Tracks</a>
     */
    @GET("/me/tracks/contains")
    Call<java.util.List<Boolean>> checkUsersSavedTracks(@Query("ids") String ids);

    /**
     * <h3>Get User's Saved Albums</h3>
     * <p>Get a list of the albums saved in the current Spotify user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     *
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-savedalbumobject">saved album objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. Each album object is accompanied by a <a href="https://developer.spotify.com/documentation/web-api/#timestamps">timestamp</a> (<code>added_at</code>) to show when it was added. There is also an <strong>etag</strong> in the header that can be used in future <a href="https://developer.spotify.com/documentation/web-api/#conditional-requests">conditional requests</a>.</p> <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-saved-albums">Get User's Saved Albums</a>
     */
    @GET("/me/albums")
    Call<Paging<SavedAlbum>> getUsersSavedAlbums();

    /**
     * <h3>Get User's Saved Albums</h3>
     * <p>Get a list of the albums saved in the current Spotify user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     *
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-savedalbumobject">saved album objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. Each album object is accompanied by a <a href="https://developer.spotify.com/documentation/web-api/#timestamps">timestamp</a> (<code>added_at</code>) to show when it was added. There is also an <strong>etag</strong> in the header that can be used in future <a href="https://developer.spotify.com/documentation/web-api/#conditional-requests">conditional requests</a>.</p> <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-saved-albums">Get User's Saved Albums</a>
     */
    @GET("/me/albums")
    Call<Paging<SavedAlbum>> getUsersSavedAlbums(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get User's Saved Shows</h3>
     * <p>Get a list of shows saved in the current Spotify user's library. Optional parameters can be used to limit the number of shows returned.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     *
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-savedshowobject">saved show objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. If the current user has no shows saved, the response will be an empty array. If a show is unavailable in the given <code>market</code> it is filtered out. The <code>total</code> field in the paging object represents the number of all items, filtered or not, and thus might be larger than the actual total number of observable items. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#error-details">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-saved-shows">Get User's Saved Shows</a>
     */
    @GET("/me/shows")
    Call<Paging<SavedShow>> getUsersSavedShows();

    /**
     * <h3>Get User's Saved Shows</h3>
     * <p>Get a list of shows saved in the current Spotify user's library. Optional parameters can be used to limit the number of shows returned.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     *
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-savedshowobject">saved show objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. If the current user has no shows saved, the response will be an empty array. If a show is unavailable in the given <code>market</code> it is filtered out. The <code>total</code> field in the paging object represents the number of all items, filtered or not, and thus might be larger than the actual total number of observable items. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#error-details">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-saved-shows">Get User's Saved Shows</a>
     */
    @GET("/me/shows")
    Call<Paging<SavedShow>> getUsersSavedShows(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get User's Saved Tracks</h3>
     * <p>Get a list of the songs saved in the current Spotify user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     *
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-savedtrackobject">saved track objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-saved-tracks">Get User's Saved Tracks</a>
     */
    @GET("/me/tracks")
    Call<Paging<SavedTrack>> getUsersSavedTracks();

    /**
     * <h3>Get User's Saved Tracks</h3>
     * <p>Get a list of the songs saved in the current Spotify user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-read</code>
     *
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-savedtrackobject">saved track objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-saved-tracks">Get User's Saved Tracks</a>
     */
    @GET("/me/tracks")
    Call<Paging<SavedTrack>> getUsersSavedTracks(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Remove Albums for Current User</h3>
     * <p>Remove one or more albums from the current user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     *
     * @param requestBody <p>the request body</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> Success. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to remove an album when you do not have the user's authorization returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-remove-albums-user">Remove Albums for Current User</a>
     */
    @DELETE("/me/albums")
    Call<Void> removeAlbumsUser(@Body RemoveAlbumsUserRequest requestBody);

    /**
     * <h3>Remove User's Saved Shows</h3>
     * <p>Delete one or more shows from current Spotify user's library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     *
     * @param ids <p>A comma-separated list of Spotify IDs for the shows to be deleted from the user's library.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#error-details">error object</a>. A <code>403 Forbidden</code> while trying to add a show when you do not have the user's authorisation.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-remove-shows-user">Remove User's Saved Shows</a>
     */
    @DELETE("/me/shows")
    Call<Void> removeShowsUser(@Query("ids") String ids);

    /**
     * <h3>Remove User's Saved Shows</h3>
     * <p>Delete one or more shows from current Spotify user's library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     *
     * @param ids <p>A comma-separated list of Spotify IDs for the shows to be deleted from the user's library.</p>
     * @param market <p>An <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>. If a country code is specified, only shows that are available in that market will be removed.<br> If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br> <em>Note: If neither market or user country are provided, the content is considered unavailable for the client.</em><br> Users can view the country that is associated with their account in the <a href="https://www.spotify.com/se/account/overview/">account settings</a>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#error-details">error object</a>. A <code>403 Forbidden</code> while trying to add a show when you do not have the user's authorisation.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-remove-shows-user">Remove User's Saved Shows</a>
     */
    @DELETE("/me/shows")
    Call<Void> removeShowsUser(@Query("ids") String ids, @Query("market") String market);

    /**
     * <h3>Remove User's Saved Tracks</h3>
     * <p>Remove one or more tracks from the current user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     *
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a>. For example: <code>ids=4iV5W9uYEdYUVa79Axb7Rh,1301WleyT98MSxVHPZCA6M</code>. Maximum: 50 IDs.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> Success. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to remove an album when you do not have the user's authorization returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-remove-tracks-user">Remove User's Saved Tracks</a>
     */
    @DELETE("/me/tracks")
    Call<Void> removeTracksUser(@Query("ids") String ids);

    /**
     * <h3>Remove User's Saved Tracks</h3>
     * <p>Remove one or more tracks from the current user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     *
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a>. For example: <code>ids=4iV5W9uYEdYUVa79Axb7Rh,1301WleyT98MSxVHPZCA6M</code>. Maximum: 50 IDs.</p>
     * @param requestBody <p>The request body</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> Success. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to remove an album when you do not have the user's authorization returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-remove-tracks-user">Remove User's Saved Tracks</a>
     */
    @DELETE("/me/tracks")
    Call<Void> removeTracksUser(@Query("ids") String ids, @Body RemoveTracksUserRequest requestBody);

    /**
     * <h3>Save Albums for Current User</h3>
     * <p>Save one or more albums to the current user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     *
     * @param requestBody <p>the request body</p>
     * @return <p>On success, the HTTP status code in the response header is <code>201</code> Created. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to add an album when you do not have the user's authorization returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-save-albums-user">Save Albums for Current User</a>
     */
    @PUT("/me/albums")
    Call<Void> saveAlbumsUser(@Body SaveAlbumsUserRequest requestBody);

    /**
     * <h3>Save Shows for Current User</h3>
     * <p>Save one or more shows to current Spotify user's library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     *
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a>. Maximum: 50 IDs.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#error-details">error object</a>. A <code>403 Forbidden</code> while trying to add a show when you do not have the user's authorisation or when the user already has have over 10,000 items saved in library.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-save-shows-user">Save Shows for Current User</a>
     */
    @PUT("/me/shows")
    Call<Void> saveShowsUser(@Query("ids") String ids);

    /**
     * <h3>Save Tracks for User</h3>
     * <p>Save one or more tracks to the current user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     *
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a>. For example: <code>ids=4iV5W9uYEdYUVa79Axb7Rh,1301WleyT98MSxVHPZCA6M</code>. Maximum: 50 IDs.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to add a track when you do not have the user's authorization, or when you have over 10.000 tracks in Your Music, returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-save-tracks-user">Save Tracks for User</a>
     */
    @PUT("/me/tracks")
    Call<Void> saveTracksUser(@Query("ids") String ids);

    /**
     * <h3>Save Tracks for User</h3>
     * <p>Save one or more tracks to the current user's 'Your Music' library.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-library-modify</code>
     *
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a>. For example: <code>ids=4iV5W9uYEdYUVa79Axb7Rh,1301WleyT98MSxVHPZCA6M</code>. Maximum: 50 IDs.</p>
     * @param requestBody <p>The request body</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Trying to add a track when you do not have the user's authorization, or when you have over 10.000 tracks in Your Music, returns error <code>403</code> Forbidden.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-save-tracks-user">Save Tracks for User</a>
     */
    @PUT("/me/tracks")
    Call<Void> saveTracksUser(@Query("ids") String ids, @Body SaveTracksUserRequest requestBody);
}
