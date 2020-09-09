package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-shows">Shows API</a>
 */
public interface ShowsApi {

    /**
     * <h3>Get a Show</h3>
     * Get Spotify catalog information for a single show identified by its unique Spotify ID.
     * 
     * @param id The Spotify ID for the show.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains a show object in JSON format. On error, the header status code is an error code and the response body contains an error object. If a show is unavailable in the given market the HTTP status code in the response header is 404 NOT FOUND.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-show">Get a Show</a>
     */
    @GET("/shows/{id}")
    Call<Show> getShow(@Path("id") String id);

    /**
     * <h3>Get a Show</h3>
     * Get Spotify catalog information for a single show identified by its unique Spotify ID.
     * 
     * @param id The Spotify ID for the show.
     * @param market An ISO 3166-1 alpha-2 country code. If a country code is specified, only shows and episodes that are available in that market will be returned. If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter. Note: If neither market or user country are provided, the content is considered unavailable for the client. Users can view the country that is associated with their account in the account settings.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains a show object in JSON format. On error, the header status code is an error code and the response body contains an error object. If a show is unavailable in the given market the HTTP status code in the response header is 404 NOT FOUND.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-show">Get a Show</a>
     */
    @GET("/shows/{id}")
    Call<Show> getShow(@Path("id") String id, @Query("market") String market);

    /**
     * <h3>Get a Show's Episodes</h3>
     * Get Spotify catalog information about an show’s episodes. Optional parameters can be used to limit the number of episodes returned.
     * 
     * @param id The Spotify ID for the show.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of simplified episode objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. If a show is unavailable in the given market the HTTP status code in the response header is 404 NOT FOUND. Unavailable episodes are filtered out.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-shows-episodes">Get a Show's Episodes</a>
     */
    @GET("/shows/{id}/episodes")
    Call<Paging<SimplifiedEpisode>> getShowsEpisodes(@Path("id") String id);

    /**
     * <h3>Get a Show's Episodes</h3>
     * Get Spotify catalog information about an show’s episodes. Optional parameters can be used to limit the number of episodes returned.
     * 
     * @param id The Spotify ID for the show.
     * @param queryParameters A map of optional query parameters
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of simplified episode objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. If a show is unavailable in the given market the HTTP status code in the response header is 404 NOT FOUND. Unavailable episodes are filtered out.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-shows-episodes">Get a Show's Episodes</a>
     */
    @GET("/shows/{id}/episodes")
    Call<Paging<SimplifiedEpisode>> getShowsEpisodes(@Path("id") String id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get Multiple Shows</h3>
     * Get Spotify catalog information for several shows based on their Spotify IDs.
     * 
     * @param ids A comma-separated list of the Spotify IDs for the shows. Maximum: 50 IDs.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is shows and whose value is an array of simple show object in JSON format. Objects are returned in the order requested. If an object is not found, a null value is returned in the appropriate position. If a show is unavailable in the given market, a null value is returned. Duplicate ids in the query will result in duplicate objects in the response. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-shows">Get Multiple Shows</a>
     */
    @GET("/shows")
    Call<Shows> getMultipleShows(@Query("ids") String ids);

    /**
     * <h3>Get Multiple Shows</h3>
     * Get Spotify catalog information for several shows based on their Spotify IDs.
     * 
     * @param ids A comma-separated list of the Spotify IDs for the shows. Maximum: 50 IDs.
     * @param market An ISO 3166-1 alpha-2 country code. If a country code is specified, only shows and episodes that are available in that market will be returned. If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter. Note: If neither market or user country are provided, the content is considered unavailable for the client. Users can view the country that is associated with their account in the account settings.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is shows and whose value is an array of simple show object in JSON format. Objects are returned in the order requested. If an object is not found, a null value is returned in the appropriate position. If a show is unavailable in the given market, a null value is returned. Duplicate ids in the query will result in duplicate objects in the response. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-shows">Get Multiple Shows</a>
     */
    @GET("/shows")
    Call<Shows> getMultipleShows(@Query("ids") String ids, @Query("market") String market);
}