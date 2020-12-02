package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-shows">Shows API</a>
 */
public interface ShowsApi {

    /**
     * <h3>Get a Show</h3>
     * <p>Get Spotify catalog information for a single show identified by its unique Spotify ID.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the show.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#show-object-full">show object</a> in JSON format.</p> <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>If a show is unavailable in the given <code>market</code> the HTTP status code in the response header is <code>404</code> NOT FOUND.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-show">Get a Show</a>
     */
    @GET("/shows/{id}")
    Call<Show> getShow(@Path("id") String id);

    /**
     * <h3>Get a Show</h3>
     * <p>Get Spotify catalog information for a single show identified by its unique Spotify ID.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the show.</p>
     * @param market <p>An <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>. If a country code is specified, only shows and episodes that are available in that market will be returned.<br> If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br> <em>Note: If neither market or user country are provided, the content is considered unavailable for the client.</em><br> Users can view the country that is associated with their account in the <a href="https://www.spotify.com/se/account/overview/">account settings</a>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#show-object-full">show object</a> in JSON format.</p> <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>If a show is unavailable in the given <code>market</code> the HTTP status code in the response header is <code>404</code> NOT FOUND.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-show">Get a Show</a>
     */
    @GET("/shows/{id}")
    Call<Show> getShow(@Path("id") String id, @Query("market") String market);

    /**
     * <h3>Get a Show's Episodes</h3>
     * <p>Get Spotify catalog information about an show's episodes. Optional parameters can be used to limit the number of episodes returned.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the show.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#episode-object-simplified">simplified episode objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format.</p> <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#error-details">error object</a>.</p> <p>If a show is unavailable in the given <code>market</code> the HTTP status code in the response header is <code>404</code> NOT FOUND. Unavailable episodes are filtered out.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-shows-episodes">Get a Show's Episodes</a>
     */
    @GET("/shows/{id}/episodes")
    Call<Paging<SimplifiedEpisode>> getShowsEpisodes(@Path("id") String id);

    /**
     * <h3>Get a Show's Episodes</h3>
     * <p>Get Spotify catalog information about an show's episodes. Optional parameters can be used to limit the number of episodes returned.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the show.</p>
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#episode-object-simplified">simplified episode objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format.</p> <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#error-details">error object</a>.</p> <p>If a show is unavailable in the given <code>market</code> the HTTP status code in the response header is <code>404</code> NOT FOUND. Unavailable episodes are filtered out.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-shows-episodes">Get a Show's Episodes</a>
     */
    @GET("/shows/{id}/episodes")
    Call<Paging<SimplifiedEpisode>> getShowsEpisodes(@Path("id") String id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get Multiple Shows</h3>
     * <p>Get Spotify catalog information for several shows based on their Spotify IDs.</p>
     * 
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the shows. Maximum: 50 IDs.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>shows</code> and whose value is an array of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#show-object-simplified">simple show object</a> in JSON format.</p> <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. If a show is unavailable in the given <code>market</code>, a <code>null</code> value is returned. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-shows">Get Multiple Shows</a>
     */
    @GET("/shows")
    Call<Shows> getMultipleShows(@Query("ids") String ids);

    /**
     * <h3>Get Multiple Shows</h3>
     * <p>Get Spotify catalog information for several shows based on their Spotify IDs.</p>
     * 
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the shows. Maximum: 50 IDs.</p>
     * @param market <p>An <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>. If a country code is specified, only shows and episodes that are available in that market will be returned.<br> If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br> <em>Note: If neither market or user country are provided, the content is considered unavailable for the client.</em><br> Users can view the country that is associated with their account in the <a href="https://www.spotify.com/se/account/overview/">account settings</a>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>shows</code> and whose value is an array of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#show-object-simplified">simple show object</a> in JSON format.</p> <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. If a show is unavailable in the given <code>market</code>, a <code>null</code> value is returned. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-multiple-shows">Get Multiple Shows</a>
     */
    @GET("/shows")
    Call<Shows> getMultipleShows(@Query("ids") String ids, @Query("market") String market);
}
