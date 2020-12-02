package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-artists">Artists API</a>
 */
public interface ArtistsApi {

    /**
     * <h3>Get an Artist</h3>
     * <p>Get Spotify catalog information for a single artist identified by their unique Spotify ID.</p>
     * 
     * @param id <p>The Spotify ID of the artist.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#artist-object-full">artist object</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-artist">Get an Artist</a>
     */
    @GET("/artists/{id}")
    Call<Artist> getArtist(@Path("id") String id);

    /**
     * <h3>Get an Artist's Albums</h3>
     * <p>Get Spotify catalog information about an artist's albums.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#album-object-simplified">album objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-artists-albums">Get an Artist's Albums</a>
     */
    @GET("/artists/{id}/albums")
    Call<Paging<SimplifiedAlbum>> getArtistsAlbums(@Path("id") String id);

    /**
     * <h3>Get an Artist's Albums</h3>
     * <p>Get Spotify catalog information about an artist's albums.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist.</p>
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#album-object-simplified">album objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-artists-albums">Get an Artist's Albums</a>
     */
    @GET("/artists/{id}/albums")
    Call<Paging<SimplifiedAlbum>> getArtistsAlbums(@Path("id") String id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get an Artist's Related Artists</h3>
     * <p>Get Spotify catalog information about artists similar to a given artist. Similarity is based on analysis of the Spotify community's <a href="http://news.spotify.com/se/2010/02/03/related-artists/">listening history</a>.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>&quot;artists&quot;</code> and whose value is an array of up to 20 <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#artist-object-full">artist objects</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-artists-related-artists">Get an Artist's Related Artists</a>
     */
    @GET("/artists/{id}/related-artists")
    Call<Artists> getArtistsRelatedArtists(@Path("id") String id);

    /**
     * <h3>Get an Artist's Top Tracks</h3>
     * <p>Get Spotify catalog information about an artist's top tracks by country.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist</p>
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Synonym for <code>country</code>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>&quot;tracks&quot;</code> and whose value is an array of up to 10 <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#track-object-full">track objects</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-artists-top-tracks">Get an Artist's Top Tracks</a>
     */
    @GET("/artists/{id}/top-tracks")
    Call<Tracks> getArtistsTopTracks(@Path("id") String id, @Query("market") String market);

    /**
     * <h3>Get Multiple Artists</h3>
     * <p>Get Spotify catalog information for several artists based on their Spotify IDs.</p>
     * 
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the artists. Maximum: 50 IDs.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>&quot;artists&quot;</code> and whose value is an array of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#artist-object-full">artist objects</a> in JSON format.</p> <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-artists">Get Multiple Artists</a>
     */
    @GET("/artists")
    Call<Artists> getMultipleArtists(@Query("ids") String ids);
}
