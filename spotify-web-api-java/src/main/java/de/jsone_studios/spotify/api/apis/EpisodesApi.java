package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-episodes">Episodes API</a>
 */
public interface EpisodesApi {

    /**
     * <h3>Get an Episode</h3>
     * Get Spotify catalog information for a single episode identified by its unique Spotify ID.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an episode object in JSON format. On error, the header status code is an error code and the response body contains an error object. If an episode is unavailable in the given market the HTTP status code in the response header is 404 NOT FOUND.
     * 
     * @param id The Spotify ID for the episode.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-episode">Get an Episode</a>
     */
    @GET("/episodes/{id}")
    Call<Episode> getEpisode(@Path("id") String id);

    /**
     * <h3>Get an Episode</h3>
     * Get Spotify catalog information for a single episode identified by its unique Spotify ID.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an episode object in JSON format. On error, the header status code is an error code and the response body contains an error object. If an episode is unavailable in the given market the HTTP status code in the response header is 404 NOT FOUND.
     * 
     * @param id The Spotify ID for the episode.
     * @param market An ISO 3166-1 alpha-2 country code. If a country code is specified, only shows and episodes that are available in that market will be returned. If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter. Note: If neither market or user country are provided, the content is considered unavailable for the client. Users can view the country that is associated with their account in the account settings.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-an-episode">Get an Episode</a>
     */
    @GET("/episodes/{id}")
    Call<Episode> getEpisode(@Path("id") String id, @Query("market") String market);

    /**
     * <h3>Get Multiple Episodes</h3>
     * Get Spotify catalog information for several episodes based on their Spotify IDs.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is episodes and whose value is an array of episode objects in JSON format. Objects are returned in the order requested. If an object is not found, a null value is returned in the appropriate position. Duplicate ids in the query will result in duplicate objects in the response. If an episode is unavailable in the given market, a null value is returned. On error, the header status code is an error code and the response body contains an error object.
     * 
     * @param ids A comma-separated list of the Spotify IDs for the episodes. Maximum: 50 IDs.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-episodes">Get Multiple Episodes</a>
     */
    @GET("/episodes")
    Call<Episodes> getMultipleEpisodes(@Query("ids") String ids);

    /**
     * <h3>Get Multiple Episodes</h3>
     * Get Spotify catalog information for several episodes based on their Spotify IDs.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is episodes and whose value is an array of episode objects in JSON format. Objects are returned in the order requested. If an object is not found, a null value is returned in the appropriate position. Duplicate ids in the query will result in duplicate objects in the response. If an episode is unavailable in the given market, a null value is returned. On error, the header status code is an error code and the response body contains an error object.
     * 
     * @param ids A comma-separated list of the Spotify IDs for the episodes. Maximum: 50 IDs.
     * @param market An ISO 3166-1 alpha-2 country code. If a country code is specified, only shows and episodes that are available in that market will be returned. If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter. Note: If neither market or user country are provided, the content is considered unavailable for the client. Users can view the country that is associated with their account in the account settings.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-episodes">Get Multiple Episodes</a>
     */
    @GET("/episodes")
    Call<Episodes> getMultipleEpisodes(@Query("ids") String ids, @Query("market") String market);
}