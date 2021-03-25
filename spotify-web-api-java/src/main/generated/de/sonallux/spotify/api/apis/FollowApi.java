package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.apis.follow.*;
import lombok.RequiredArgsConstructor;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-follow">Follow API</a>
 */
@RequiredArgsConstructor
public class FollowApi {
    private final ApiClient apiClient;

    /**
     * <h3>Get Following State for Artists/Users</h3>
     * <p>Check to see if the current user is following one or more artists or other Spotify users.</p>
     * @param type <p>The ID type: either <code>artist</code> or <code>user</code>.</p>
     * @param ids <p>A comma-separated list of the artist or the user <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> to check. For example: <code>ids=74ASZWbe4lXaubB36ztrGX,08td7MxkoHQkXnWAYD8d6Q</code>. A maximum of 50 IDs can be sent in one request.</p>
     * @return a {@link CheckCurrentUserFollowsRequest} object to build and execute the request
     */
    public CheckCurrentUserFollowsRequest checkCurrentUserFollows(String type, String ids) {
        return new CheckCurrentUserFollowsRequest(apiClient, type, ids);
    }

    /**
     * <h3>Check if Users Follow a Playlist</h3>
     * <p>Check to see if one or more Spotify users are following a specified playlist.</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> of the playlist.</p>
     * @param ids <p>A comma-separated list of <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify User IDs</a> ; the ids of the users that you want to check to see if they follow the playlist. Maximum: 5 ids.</p>
     * @return a {@link CheckIfUserFollowsPlaylistRequest} object to build and execute the request
     */
    public CheckIfUserFollowsPlaylistRequest checkIfUserFollowsPlaylist(String playlistId, String ids) {
        return new CheckIfUserFollowsPlaylistRequest(apiClient, playlistId, ids);
    }

    /**
     * <h3>Follow Artists or Users</h3>
     * <p>Add the current user as a follower of one or more artists or other Spotify users.</p>
     * @param type <p>The ID type: either <code>artist</code> or <code>user</code>.</p>
     * @param ids <p>A JSON array of the artist or user <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a>. For example: <code>{ids:[&quot;74ASZWbe4lXaubB36ztrGX&quot;, &quot;08td7MxkoHQkXnWAYD8d6Q&quot;]}</code>. A maximum of 50 IDs can be sent in one request. <em>Note: if the <code>ids</code> parameter is present in the query string, any IDs listed here in the body will be ignored.</em></p>
     * @return a {@link FollowArtistsUsersRequest} object to build and execute the request
     */
    public FollowArtistsUsersRequest followArtistsUsers(String type, java.util.List<String> ids) {
        return new FollowArtistsUsersRequest(apiClient, type, ids);
    }

    /**
     * <h3>Follow a Playlist</h3>
     * <p>Add the current user as a follower of a playlist.</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> of the playlist. Any playlist can be followed, regardless of its <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/#public-private-and-collaborative-status">public/private status</a>, as long as you know its playlist ID.</p>
     * @return a {@link FollowPlaylistRequest} object to build and execute the request
     */
    public FollowPlaylistRequest followPlaylist(String playlistId) {
        return new FollowPlaylistRequest(apiClient, playlistId);
    }

    /**
     * <h3>Get User's Followed Artists</h3>
     * <p>Get the current user's followed artists.</p>
     * @param type <p>The ID type: currently only <code>artist</code> is supported.</p>
     * @return a {@link GetFollowedRequest} object to build and execute the request
     */
    public GetFollowedRequest getFollowed(String type) {
        return new GetFollowedRequest(apiClient, type);
    }

    /**
     * <h3>Unfollow Artists or Users</h3>
     * <p>Remove the current user as a follower of one or more artists or other Spotify users.</p>
     * @param type <p>The ID type: either <code>artist</code> or <code>user</code>.</p>
     * @param ids <p>A JSON array of the artist or user <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a>. For example: <code>{ids:[&quot;74ASZWbe4lXaubB36ztrGX&quot;, &quot;08td7MxkoHQkXnWAYD8d6Q&quot;]}</code>. A maximum of 50 IDs can be sent in one request. <em>Note: if the <code>ids</code> parameter is present in the query string, any IDs listed here in the body will be ignored.</em></p>
     * @return a {@link UnfollowArtistsUsersRequest} object to build and execute the request
     */
    public UnfollowArtistsUsersRequest unfollowArtistsUsers(String type, java.util.List<String> ids) {
        return new UnfollowArtistsUsersRequest(apiClient, type, ids);
    }

    /**
     * <h3>Unfollow Playlist</h3>
     * <p>Remove the current user as a follower of a playlist.</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> of the playlist that is to be no longer followed.</p>
     * @return a {@link UnfollowPlaylistRequest} object to build and execute the request
     */
    public UnfollowPlaylistRequest unfollowPlaylist(String playlistId) {
        return new UnfollowPlaylistRequest(apiClient, playlistId);
    }
}
