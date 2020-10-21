package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-personalization">Personalization API</a>
 */
public interface PersonalizationApi {

    /**
     * <h3>Get a User's Top Artists</h3>
     * <p>Get the current user’s top artists based on calculated affinity.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-top-read</code>
     * 
     * @return <p>On success, the HTTP status code in the response header is 200 OK and the response body contains a paging object of Artists. On error, the header status code is an error code and the response body contains an error object.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-top-artists-and-tracks">Get a User's Top Artists</a>
     */
    @GET("/me/top/artists")
    Call<Paging<Artist>> getUsersTopArtists();

    /**
     * <h3>Get a User's Top Artists</h3>
     * <p>Get the current user’s top artists based on calculated affinity.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-top-read</code>
     * 
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is 200 OK and the response body contains a paging object of Artists. On error, the header status code is an error code and the response body contains an error object.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-top-artists-and-tracks">Get a User's Top Artists</a>
     */
    @GET("/me/top/artists")
    Call<Paging<Artist>> getUsersTopArtists(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get a User's Top Tracks</h3>
     * <p>Get the current user’s top tracks based on calculated affinity.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-top-read</code>
     * 
     * @return <p>On success, the HTTP status code in the response header is 200 OK and the response body contains a paging object of Tracks. On error, the header status code is an error code and the response body contains an error object.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-top-artists-and-tracks">Get a User's Top Tracks</a>
     */
    @GET("/me/top/tracks")
    Call<Paging<Track>> getUsersTopTracks();

    /**
     * <h3>Get a User's Top Tracks</h3>
     * <p>Get the current user’s top tracks based on calculated affinity.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-top-read</code>
     * 
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is 200 OK and the response body contains a paging object of Tracks. On error, the header status code is an error code and the response body contains an error object.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-top-artists-and-tracks">Get a User's Top Tracks</a>
     */
    @GET("/me/top/tracks")
    Call<Paging<Track>> getUsersTopTracks(@QueryMap java.util.Map<String, Object> queryParameters);
}
