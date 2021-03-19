package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-follow">Follow API</a>
 */
public interface FollowApi {

    /**
     * <h3>Get Following State for Artists/Users</h3>
     * <p>Check to see if the current user is following one or more artists or other Spotify users.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-read</code>
     *
     * @param type <p>The ID type: either <code>artist</code> or <code>user</code>.</p>
     * @param ids <p>A comma-separated list of the artist or the user <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> to check. For example: <code>ids=74ASZWbe4lXaubB36ztrGX,08td7MxkoHQkXnWAYD8d6Q</code>. A maximum of 50 IDs can be sent in one request.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a JSON array of <code>true</code> or <code>false</code> values, in the same order in which the <code>ids</code> were specified.
     *         On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-check-current-user-follows">Get Following State for Artists/Users</a>
     */
    @GET("/me/following/contains")
    Call<java.util.List<Boolean>> checkCurrentUserFollows(@Query("type") String type, @Query("ids") String ids);

    /**
     * <h3>Check if Users Follow a Playlist</h3>
     * <p>Check to see if one or more Spotify users are following a specified playlist.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-read-private</code>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> of the playlist.</p>
     * @param ids <p>A comma-separated list of <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify User IDs</a> ; the ids of the users that you want to check to see if they follow the playlist. Maximum: 5 ids.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a JSON array of <code>true</code> or <code>false</code> values, in the same order in which the <code>ids</code> were specified.
     *         On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-check-if-user-follows-playlist">Check if Users Follow a Playlist</a>
     */
    @GET("/playlists/{playlist_id}/followers/contains")
    Call<java.util.List<Boolean>> checkIfUserFollowsPlaylist(@Path("playlist_id") String playlistId, @Query("ids") String ids);

    /**
     * <h3>Follow Artists or Users</h3>
     * <p>Add the current user as a follower of one or more artists or other Spotify users.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     *
     * @param type <p>The ID type: either <code>artist</code> or <code>user</code>.</p>
     * @param requestBody <p>The request body</p>
     * @return <p>On success, the HTTP status code in the response header is <code>204</code> No Content and the response body is empty.
     *         On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-follow-artists-users">Follow Artists or Users</a>
     */
    @PUT("/me/following")
    Call<Void> followArtistsUsers(@Query("type") String type, @Body FollowArtistsUsersRequest requestBody);

    /**
     * <h3>Follow a Playlist</h3>
     * <p>Add the current user as a follower of a playlist.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> of the playlist. Any playlist can be followed, regardless of its <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/#public-private-and-collaborative-status">public/private status</a>, as long as you know its playlist ID.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body is empty.
     *         On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-follow-playlist">Follow a Playlist</a>
     */
    default Call<Void> followPlaylist(String playlistId) {
        return followPlaylist(playlistId, new FollowPlaylistRequest());
    }

    /**
     * <h3>Follow a Playlist</h3>
     * <p>Add the current user as a follower of a playlist.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> of the playlist. Any playlist can be followed, regardless of its <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/#public-private-and-collaborative-status">public/private status</a>, as long as you know its playlist ID.</p>
     * @param requestBody <p>The request body</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body is empty.
     *         On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-follow-playlist">Follow a Playlist</a>
     */
    @PUT("/playlists/{playlist_id}/followers")
    Call<Void> followPlaylist(@Path("playlist_id") String playlistId, @Body FollowPlaylistRequest requestBody);

    /**
     * <h3>Get User's Followed Artists</h3>
     * <p>Get the current user's followed artists.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     *
     * @param type <p>The ID type: currently only <code>artist</code> is supported.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an <code>artists</code> object.
     *         The <code>artists</code> object in turn contains a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-cursorpagingobject">cursor-based paging object</a> of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-artistobject">Artists</a>.
     *         On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-followed">Get User's Followed Artists</a>
     */
    default Call<FollowingArtists> getFollowed(String type) {
        return getFollowed(type, java.util.Map.of());
    }

    /**
     * <h3>Get User's Followed Artists</h3>
     * <p>Get the current user's followed artists.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     *
     * @param type <p>The ID type: currently only <code>artist</code> is supported.</p>
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an <code>artists</code> object.
     *         The <code>artists</code> object in turn contains a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-cursorpagingobject">cursor-based paging object</a> of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-artistobject">Artists</a>.
     *         On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-followed">Get User's Followed Artists</a>
     */
    @GET("/me/following")
    Call<FollowingArtists> getFollowed(@Query("type") String type, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Unfollow Artists or Users</h3>
     * <p>Remove the current user as a follower of one or more artists or other Spotify users.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-follow-modify</code>
     *
     * @param type <p>The ID type: either <code>artist</code> or <code>user</code>.</p>
     * @param requestBody <p>The request body</p>
     * @return <p>On success, the HTTP status code in the response header is <code>204</code> No Content and the response body is empty.
     *         On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-unfollow-artists-users">Unfollow Artists or Users</a>
     */
    @HTTP(method = "DELETE", hasBody = true, path = "/me/following")
    Call<Void> unfollowArtistsUsers(@Query("type") String type, @Body UnfollowArtistsUsersRequest requestBody);

    /**
     * <h3>Unfollow Playlist</h3>
     * <p>Remove the current user as a follower of a playlist.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>playlist-modify-public, playlist-modify-private</code>
     *
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> of the playlist that is to be no longer followed.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body is empty.
     *         On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-unfollow-playlist">Unfollow Playlist</a>
     */
    @DELETE("/playlists/{playlist_id}/followers")
    Call<Void> unfollowPlaylist(@Path("playlist_id") String playlistId);
}
