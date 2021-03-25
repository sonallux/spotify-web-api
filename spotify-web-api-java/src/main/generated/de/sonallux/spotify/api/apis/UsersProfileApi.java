package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.apis.usersprofile.*;
import lombok.RequiredArgsConstructor;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-users-profile">Users Profile API</a>
 */
@RequiredArgsConstructor
public class UsersProfileApi {
    private final ApiClient apiClient;

    /**
     * <h3>Get Current User's Profile</h3>
     * <p>Get detailed profile information about the current user (including the current user's username).</p>
     * @return a {@link GetCurrentUsersProfileRequest} object to build and execute the request
     */
    public GetCurrentUsersProfileRequest getCurrentUsersProfile() {
        return new GetCurrentUsersProfileRequest(apiClient);
    }

    /**
     * <h3>Get a User's Profile</h3>
     * <p>Get public profile information about a Spotify user.</p>
     * @param userId <p>The user's <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify user ID</a>.</p>
     * @return a {@link GetUsersProfileRequest} object to build and execute the request
     */
    public GetUsersProfileRequest getUsersProfile(String userId) {
        return new GetUsersProfileRequest(apiClient, userId);
    }
}
