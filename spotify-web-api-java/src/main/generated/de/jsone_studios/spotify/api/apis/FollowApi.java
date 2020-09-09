package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-follow">Follow API</a>
 */
public interface FollowApi {

    /**
     * <h3>Get Following State for Artists/Users</h3>
     * Check to see if the current user is following one or more artists or other Spotify users.
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-read</code>
     * 
     * @param type The ID type: either artist or user.
     * @param ids A comma-separated list of the artist or the user Spotify IDs to check. For example: ids&#x3D;74ASZWbe4lXaubB36ztrGX,08td7MxkoHQkXnWAYD8d6Q. A maximum of 50 IDs can be sent in one request.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains a JSON array of true or false values, in the same order in which the ids were specified. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-check-current-user-follows">Get Following State for Artists/Users</a>
     */
    @GET("/me/following/contains")
    Call<java.util.List<Boolean>> checkCurrentUserFollows(@Query("type") String type, @Query("ids") String ids);

    /**
     * <h3>Check if Users Follow a Playlist</h3>
     * Check to see if one or more Spotify users are following a specified playlist.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-read-private</code>
     * 
     * @param playlist_id The Spotify ID of the playlist.
     * @param ids A comma-separated list of Spotify User IDs ; the ids of the users that you want to check to see if they follow the playlist. Maximum: 5 ids.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains a JSON array of true or false values, in the same order in which the ids were specified. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-check-if-user-follows-playlist">Check if Users Follow a Playlist</a>
     */
    @GET("/playlists/{playlist_id}/followers/contains")
    Call<java.util.List<Boolean>> checkIfUserFollowsPlaylist(@Path("playlist_id") String playlist_id, @Query("ids") String ids);

    /**
     * <h3>Follow Artists or Users</h3>
     * Add the current user as a follower of one or more artists or other Spotify users.
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     * 
     * @param type The ID type: either artist or user.
     * @param requestBody the request body
     * @return On success, the HTTP status code in the response header is 204 No Content and the response body is empty. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-follow-artists-users">Follow Artists or Users</a>
     */
    @PUT("/me/following")
    Call<Void> followArtistsUsers(@Query("type") String type, @Body FollowArtistsUsersRequest requestBody);

    /**
     * <h3>Follow a Playlist</h3>
     * Add the current user as a follower of a playlist.
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     * 
     * @param playlist_id The Spotify ID of the playlist. Any playlist can be followed, regardless of its public/private status, as long as you know its playlist ID.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body is empty. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-follow-playlist">Follow a Playlist</a>
     */
    @PUT("/playlists/{playlist_id}/followers")
    Call<Void> followPlaylist(@Path("playlist_id") String playlist_id);

    /**
     * <h3>Follow a Playlist</h3>
     * Add the current user as a follower of a playlist.
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     * 
     * @param playlist_id The Spotify ID of the playlist. Any playlist can be followed, regardless of its public/private status, as long as you know its playlist ID.
     * @param requestBody The request body
     * @return On success, the HTTP status code in the response header is 200 OK and the response body is empty. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-follow-playlist">Follow a Playlist</a>
     */
    @PUT("/playlists/{playlist_id}/followers")
    Call<Void> followPlaylist(@Path("playlist_id") String playlist_id, @Body FollowPlaylistRequest requestBody);

    /**
     * <h3>Get User's Followed Artists</h3>
     * Get the current user’s followed artists.
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     * 
     * @param type The ID type: currently only artist is supported.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an artists object. The artists object in turn contains a cursor-based paging object of Artists. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-followed">Get User's Followed Artists</a>
     */
    @GET("/me/following")
    Call<FollowingArtists> getFollowed(@Query("type") String type);

    /**
     * <h3>Get User's Followed Artists</h3>
     * Get the current user’s followed artists.
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     * 
     * @param type The ID type: currently only artist is supported.
     * @param queryParameters A map of optional query parameters
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an artists object. The artists object in turn contains a cursor-based paging object of Artists. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-followed">Get User's Followed Artists</a>
     */
    @GET("/me/following")
    Call<FollowingArtists> getFollowed(@Query("type") String type, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Unfollow Artists or Users</h3>
     * Remove the current user as a follower of one or more artists or other Spotify users.
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     * 
     * @param type The ID type: either artist or user.
     * @param requestBody the request body
     * @return On success, the HTTP status code in the response header is 204 No Content and the response body is empty. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-unfollow-artists-users">Unfollow Artists or Users</a>
     */
    @DELETE("/me/following")
    Call<Void> unfollowArtistsUsers(@Query("type") String type, @Body UnfollowArtistsUsersRequest requestBody);

    /**
     * <h3>Unfollow Playlist</h3>
     * Remove the current user as a follower of a playlist.
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     * 
     * @param playlist_id The Spotify ID of the playlist that is to be no longer followed.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body is empty. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-unfollow-playlist">Unfollow Playlist</a>
     */
    @DELETE("/playlists/{playlist_id}/followers")
    Call<Void> unfollowPlaylist(@Path("playlist_id") String playlist_id);
}