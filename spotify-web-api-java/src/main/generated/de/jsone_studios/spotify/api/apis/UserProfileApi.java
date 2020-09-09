package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-user-profile">User Profile API</a>
 */
public interface UserProfileApi {

    /**
     * <h3>Get Current User's Profile</h3>
     * Get detailed profile information about the current user (including the current user’s username).
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-email, user-read-private</code>
     * 
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains a user object in JSON format. On error, the header status code is an error code and the response body contains an error object. When requesting fields that you don’t have the user’s authorization to access, it will return error 403 Forbidden. Important! If the user-read-email scope is authorized, the returned JSON will include the email address that was entered when the user created their Spotify account. This email address is unverified; do not assume that the email address belongs to the user.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-current-users-profile">Get Current User's Profile</a>
     */
    @GET("/me")
    Call<PrivateUser> getCurrentUsersProfile();

    /**
     * <h3>Get a User's Profile</h3>
     * Get public profile information about a Spotify user.
     * 
     * @param user_id The user’s Spotify user ID.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains a user object in JSON format. On error, the header status code is an error code and the response body contains an error object. If a user with that user_id doesn’t exist, the status code is 404 NOT FOUND.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-profile">Get a User's Profile</a>
     */
    @GET("/users/{user_id}")
    Call<PublicUser> getUsersProfile(@Path("user_id") String user_id);
}