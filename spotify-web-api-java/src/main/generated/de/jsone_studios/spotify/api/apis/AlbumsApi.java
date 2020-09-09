package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-albums">Albums API</a>
 */
public interface AlbumsApi {

    /**
     * <h3>Get an Album</h3>
     * Get Spotify catalog information for a single album.
     * 
     * @param id The Spotify ID of the album.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an album object in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-album">Get an Album</a>
     */
    @GET("/albums/{id}")
    Call<Album> getAlbum(@Path("id") String id);

    /**
     * <h3>Get an Album</h3>
     * Get Spotify catalog information for a single album.
     * 
     * @param id The Spotify ID of the album.
     * @param market The market you’d like to request. Synonym for country.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an album object in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-album">Get an Album</a>
     */
    @GET("/albums/{id}")
    Call<Album> getAlbum(@Path("id") String id, @Query("market") String market);

    /**
     * <h3>Get an Album's Tracks</h3>
     * Get Spotify catalog information about an album’s tracks. Optional parameters can be used to limit the number of tracks returned.
     * 
     * @param id The Spotify ID of the album.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an album object in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-albums-tracks">Get an Album's Tracks</a>
     */
    @GET("/albums/{id}/tracks")
    Call<Paging<Track>> getAlbumsTracks(@Path("id") String id);

    /**
     * <h3>Get an Album's Tracks</h3>
     * Get Spotify catalog information about an album’s tracks. Optional parameters can be used to limit the number of tracks returned.
     * 
     * @param id The Spotify ID of the album.
     * @param queryParameters A map of optional query parameters
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an album object in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-albums-tracks">Get an Album's Tracks</a>
     */
    @GET("/albums/{id}/tracks")
    Call<Paging<Track>> getAlbumsTracks(@Path("id") String id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get Multiple Albums</h3>
     * Get Spotify catalog information for multiple albums identified by their Spotify IDs.
     * 
     * @param ids A comma-separated list of the Spotify IDs for the albums. Maximum: 20 IDs.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is &quot;albums&quot; and whose value is an array of album objects in JSON format. Objects are returned in the order requested. If an object is not found, a null value is returned in the appropriate position. Duplicate ids in the query will result in duplicate objects in the response. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-albums">Get Multiple Albums</a>
     */
    @GET("/albums")
    Call<Albums> getMultipleAlbums(@Query("ids") String ids);

    /**
     * <h3>Get Multiple Albums</h3>
     * Get Spotify catalog information for multiple albums identified by their Spotify IDs.
     * 
     * @param ids A comma-separated list of the Spotify IDs for the albums. Maximum: 20 IDs.
     * @param market An ISO 3166-1 alpha-2 country code or the string from_token. Provide this parameter if you want to apply Track Relinking.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is &quot;albums&quot; and whose value is an array of album objects in JSON format. Objects are returned in the order requested. If an object is not found, a null value is returned in the appropriate position. Duplicate ids in the query will result in duplicate objects in the response. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-albums">Get Multiple Albums</a>
     */
    @GET("/albums")
    Call<Albums> getMultipleAlbums(@Query("ids") String ids, @Query("market") String market);
}