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
     * <p>Get Spotify catalog information for a single album.</p>
     * 
     * @param id <p>The Spotify ID of the album.</p>
     * @return <p>On success, the HTTP status code in the response header is 200 OK and the response body contains an album object in JSON format. On error, the header status code is an error code and the response body contains an error object.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-album">Get an Album</a>
     */
    @GET("/albums/{id}")
    Call<Album> getAlbum(@Path("id") String id);

    /**
     * <h3>Get an Album</h3>
     * <p>Get Spotify catalog information for a single album.</p>
     * 
     * @param id <p>The Spotify ID of the album.</p>
     * @param market <p>The market you'd like to request. Synonym for <code>country</code>.</p>
     * @return <p>On success, the HTTP status code in the response header is 200 OK and the response body contains an album object in JSON format. On error, the header status code is an error code and the response body contains an error object.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-album">Get an Album</a>
     */
    @GET("/albums/{id}")
    Call<Album> getAlbum(@Path("id") String id, @Query("market") String market);

    /**
     * <h3>Get an Album's Tracks</h3>
     * <p>Get Spotify catalog information about an album's tracks. Optional parameters can be used to limit the number of tracks returned.</p>
     * 
     * @param id <p>The Spotify ID of the album.</p>
     * @return <p>On success, the HTTP status code in the response header is 200 OK and the response body contains an album object in JSON format. On error, the header status code is an error code and the response body contains an error object.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-albums-tracks">Get an Album's Tracks</a>
     */
    @GET("/albums/{id}/tracks")
    Call<Paging<Track>> getAlbumsTracks(@Path("id") String id);

    /**
     * <h3>Get an Album's Tracks</h3>
     * <p>Get Spotify catalog information about an album's tracks. Optional parameters can be used to limit the number of tracks returned.</p>
     * 
     * @param id <p>The Spotify ID of the album.</p>
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is 200 OK and the response body contains an album object in JSON format. On error, the header status code is an error code and the response body contains an error object.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-albums-tracks">Get an Album's Tracks</a>
     */
    @GET("/albums/{id}/tracks")
    Call<Paging<Track>> getAlbumsTracks(@Path("id") String id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get Multiple Albums</h3>
     * <p>Get Spotify catalog information for multiple albums identified by their Spotify IDs.</p>
     * 
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the albums. Maximum: 20 IDs.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>&quot;albums&quot;</code> and whose value is an array of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#album-object-full">album objects</a> in JSON format.</p> <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-albums">Get Multiple Albums</a>
     */
    @GET("/albums")
    Call<Albums> getMultipleAlbums(@Query("ids") String ids);

    /**
     * <h3>Get Multiple Albums</h3>
     * <p>Get Spotify catalog information for multiple albums identified by their Spotify IDs.</p>
     * 
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the albums. Maximum: 20 IDs.</p>
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string from_token. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>&quot;albums&quot;</code> and whose value is an array of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#album-object-full">album objects</a> in JSON format.</p> <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-albums">Get Multiple Albums</a>
     */
    @GET("/albums")
    Call<Albums> getMultipleAlbums(@Query("ids") String ids, @Query("market") String market);
}
