package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
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
     * @return <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a> of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#artist-object-full">Artists</a>. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
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
     * @return <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a> of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#artist-object-full">Artists</a>. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
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
     * @return <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a> of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#track-object-full">Tracks</a>. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
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
     * @return <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a> of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#track-object-full">Tracks</a>. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-users-top-artists-and-tracks">Get a User's Top Tracks</a>
     */
    @GET("/me/top/tracks")
    Call<Paging<Track>> getUsersTopTracks(@QueryMap java.util.Map<String, Object> queryParameters);
}
