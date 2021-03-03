package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-users-profile">Users Profile API</a>
 */
public interface UsersProfileApi {

    /**
     * <h3>Get Current User's Profile</h3>
     * <p>Get detailed profile information about the current user (including the current user's username).</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-email, user-read-private</code>
     *
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-privateuserobject">user object</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. When requesting fields that you don't have the user's authorization to access, it will return error <code>403</code> Forbidden.</p>
     *         <p><strong>Important!</strong> If the <code>user-read-email</code> scope is authorized, the returned JSON will include the email address that was entered when the user created their Spotify account. <strong>This email address is unverified</strong>; do not assume that the email address belongs to the user.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-current-users-profile">Get Current User's Profile</a>
     */
    @GET("/me")
    Call<PrivateUser> getCurrentUsersProfile();

    /**
     * <h3>Get a User's Profile</h3>
     * <p>Get public profile information about a Spotify user.</p>
     *
     * @param userId <p>The user's <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify user ID</a>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-publicuserobject">user object</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. If a user with that user_id doesn't exist, the status code is <code>404</code> NOT FOUND.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-users-profile">Get a User's Profile</a>
     */
    @GET("/users/{user_id}")
    Call<PublicUser> getUsersProfile(@Path("user_id") String userId);
}
