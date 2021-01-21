package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-episodes">Episodes API</a>
 */
public interface EpisodesApi {

    /**
     * <h3>Get an Episode</h3>
     * <p>Get Spotify catalog information for a single episode identified by its unique Spotify ID.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the episode.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/reference/#object-episodeobject">episode object</a> in JSON format.</p> <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>If an episode is unavailable in the given <code>market</code> the HTTP status code in the response header is <code>404</code> NOT FOUND.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-an-episode">Get an Episode</a>
     */
    @GET("/episodes/{id}")
    Call<Episode> getEpisode(@Path("id") String id);

    /**
     * <h3>Get an Episode</h3>
     * <p>Get Spotify catalog information for a single episode identified by its unique Spotify ID.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the episode.</p>
     * @param market <p>An <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>. If a country code is specified, only shows and episodes that are available in that market will be returned.<br> If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br> <em>Note: If neither market or user country are provided, the content is considered unavailable for the client.</em><br> Users can view the country that is associated with their account in the <a href="https://www.spotify.com/se/account/overview/">account settings</a>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/reference/#object-episodeobject">episode object</a> in JSON format.</p> <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>If an episode is unavailable in the given <code>market</code> the HTTP status code in the response header is <code>404</code> NOT FOUND.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-an-episode">Get an Episode</a>
     */
    @GET("/episodes/{id}")
    Call<Episode> getEpisode(@Path("id") String id, @Query("market") String market);

    /**
     * <h3>Get Multiple Episodes</h3>
     * <p>Get Spotify catalog information for several episodes based on their Spotify IDs.</p>
     * 
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the episodes. Maximum: 50 IDs.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>episodes</code> and whose value is an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-episodeobject">episode objects</a> in JSON format.</p> <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. If an episode is unavailable in the given <code>market</code>, a <code>null</code> value is returned. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-multiple-episodes">Get Multiple Episodes</a>
     */
    @GET("/episodes")
    Call<Episodes> getMultipleEpisodes(@Query("ids") String ids);

    /**
     * <h3>Get Multiple Episodes</h3>
     * <p>Get Spotify catalog information for several episodes based on their Spotify IDs.</p>
     * 
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the episodes. Maximum: 50 IDs.</p>
     * @param market <p>An <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>. If a country code is specified, only shows and episodes that are available in that market will be returned.<br> If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br> <em>Note: If neither market or user country are provided, the content is considered unavailable for the client.</em><br> Users can view the country that is associated with their account in the <a href="https://www.spotify.com/se/account/overview/">account settings</a>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>episodes</code> and whose value is an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-episodeobject">episode objects</a> in JSON format.</p> <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. If an episode is unavailable in the given <code>market</code>, a <code>null</code> value is returned. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-multiple-episodes">Get Multiple Episodes</a>
     */
    @GET("/episodes")
    Call<Episodes> getMultipleEpisodes(@Query("ids") String ids, @Query("market") String market);
}
