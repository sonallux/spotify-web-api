package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-artists">Artists API</a>
 */
public interface ArtistsApi {

    /**
     * <h3>Get an Artist</h3>
     * Get Spotify catalog information for a single artist identified by their unique Spotify ID.
     * 
     * @param id The Spotify ID of the artist.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an artist object in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-artist">Get an Artist</a>
     */
    @GET("/artists/{id}")
    Call<Artist> getArtist(@Path("id") String id);

    /**
     * <h3>Get an Artist's Albums</h3>
     * Get Spotify catalog information about an artist’s albums.
     * 
     * @param id The Spotify ID for the artist.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of simplified album objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-artists-albums">Get an Artist's Albums</a>
     */
    @GET("/artists/{id}/albums")
    Call<Paging<SimplifiedAlbum>> getArtistsAlbums(@Path("id") String id);

    /**
     * <h3>Get an Artist's Albums</h3>
     * Get Spotify catalog information about an artist’s albums.
     * 
     * @param id The Spotify ID for the artist.
     * @param queryParameters A map of optional query parameters
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of simplified album objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-artists-albums">Get an Artist's Albums</a>
     */
    @GET("/artists/{id}/albums")
    Call<Paging<SimplifiedAlbum>> getArtistsAlbums(@Path("id") String id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get an Artist's Related Artists</h3>
     * Get Spotify catalog information about artists similar to a given artist. Similarity is based on analysis of the Spotify community’s listening history.
     * 
     * @param id The Spotify ID for the artist
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is &quot;artists&quot; and whose value is an array of up to 20 artist objects in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-artists-related-artists">Get an Artist's Related Artists</a>
     */
    @GET("/artists/{id}/related-artists")
    Call<Artists> getArtistsRelatedArtists(@Path("id") String id);

    /**
     * <h3>Get an Artist's Top Tracks</h3>
     * Get Spotify catalog information about an artist’s top tracks by country.
     * 
     * @param id The Spotify ID for the artist
     * @param market An ISO 3166-1 alpha-2 country code or the string from_token. Synonym for country.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is &quot;tracks&quot; and whose value is an array of up to 10 track objects in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-artists-top-tracks">Get an Artist's Top Tracks</a>
     */
    @GET("/artists/{id}/top-tracks")
    Call<Tracks> getArtistsTopTracks(@Path("id") String id, @Query("market") String market);

    /**
     * <h3>Get Multiple Artists</h3>
     * Get Spotify catalog information for several artists based on their Spotify IDs.
     * 
     * @param ids A comma-separated list of the Spotify IDs for the artists. Maximum: 50 IDs.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is &quot;artists&quot; and whose value is an array of artist objects in JSON format. Objects are returned in the order requested. If an object is not found, a null value is returned in the appropriate position. Duplicate ids in the query will result in duplicate objects in the response. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-artists">Get Multiple Artists</a>
     */
    @GET("/artists")
    Call<Artists> getMultipleArtists(@Query("ids") String ids);
}
