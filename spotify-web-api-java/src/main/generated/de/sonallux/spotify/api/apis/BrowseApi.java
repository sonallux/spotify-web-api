package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-browse">Browse API</a>
 */
public interface BrowseApi {

    /**
     * <h3>Get a Category's Playlists</h3>
     * <p>Get a list of Spotify playlists tagged with a particular category.</p>
     * 
     * @param category_id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify category ID</a> for the category.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#playlist-object-simplified">playlist objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>Once you have retrieved the list, you can use <a href="https://developer.spotify.com/web-api/get-playlist/">Get a Playlist</a> and <a href="https://developer.spotify.com/web-api/get-playlists-tracks/">Get a Playlist's Tracks</a> to drill down further.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-categories-playlists">Get a Category's Playlists</a>
     */
    @GET("/browse/categories/{category_id}/playlists")
    Call<PlaylistPaging> getCategoriesPlaylists(@Path("category_id") String category_id);

    /**
     * <h3>Get a Category's Playlists</h3>
     * <p>Get a list of Spotify playlists tagged with a particular category.</p>
     * 
     * @param category_id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify category ID</a> for the category.</p>
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#playlist-object-simplified">playlist objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>Once you have retrieved the list, you can use <a href="https://developer.spotify.com/web-api/get-playlist/">Get a Playlist</a> and <a href="https://developer.spotify.com/web-api/get-playlists-tracks/">Get a Playlist's Tracks</a> to drill down further.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-categories-playlists">Get a Category's Playlists</a>
     */
    @GET("/browse/categories/{category_id}/playlists")
    Call<PlaylistPaging> getCategoriesPlaylists(@Path("category_id") String category_id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get a Category</h3>
     * <p>Get a single category used to tag items in Spotify (on, for example, the Spotify player's &quot;Browse&quot; tab).</p>
     * 
     * @param category_id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify category ID</a> for the category.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <a href="#categoryobject">category object</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>Once you have retrieved the category, you can use <a href="https://developer.spotify.com/web-api/get-categorys-playlists/">Get a Category's Playlists</a> to drill down further.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-category">Get a Category</a>
     */
    @GET("/browse/categories/{category_id}")
    Call<Category> getCategory(@Path("category_id") String category_id);

    /**
     * <h3>Get a Category</h3>
     * <p>Get a single category used to tag items in Spotify (on, for example, the Spotify player's &quot;Browse&quot; tab).</p>
     * 
     * @param category_id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify category ID</a> for the category.</p>
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <a href="#categoryobject">category object</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>Once you have retrieved the category, you can use <a href="https://developer.spotify.com/web-api/get-categorys-playlists/">Get a Category's Playlists</a> to drill down further.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-category">Get a Category</a>
     */
    @GET("/browse/categories/{category_id}")
    Call<Category> getCategory(@Path("category_id") String category_id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get All Categories</h3>
     * <p>Get a list of categories used to tag items in Spotify (on, for example, the Spotify player's &quot;Browse&quot; tab).</p>
     * 
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object with a <code>categories</code> field, with an array of <a href="#categoryobject">category objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>Once you have retrieved the list, you can use <a href="https://developer.spotify.com/web-api/get-category/">Get a Category</a> to drill down further.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-categories">Get All Categories</a>
     */
    @GET("/browse/categories")
    Call<Categories> getCategories();

    /**
     * <h3>Get All Categories</h3>
     * <p>Get a list of categories used to tag items in Spotify (on, for example, the Spotify player's &quot;Browse&quot; tab).</p>
     * 
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object with a <code>categories</code> field, with an array of <a href="#categoryobject">category objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>Once you have retrieved the list, you can use <a href="https://developer.spotify.com/web-api/get-category/">Get a Category</a> to drill down further.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-categories">Get All Categories</a>
     */
    @GET("/browse/categories")
    Call<Categories> getCategories(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get All Featured Playlists</h3>
     * <p>Get a list of Spotify featured playlists (shown, for example, on a Spotify player's 'Browse' tab).</p>
     * 
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <code>message</code> and a <code>playlists</code> object. The <code>playlists</code> object contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#playlist-object-simplified">playlist objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>Once you have retrieved the list of playlist objects, you can use <a href="https://developer.spotify.com/web-api/get-playlist/">Get a Playlist</a> and <a href="https://developer.spotify.com/web-api/get-playlists-tracks/">Get a Playlist's Tracks</a> to drill down further.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-featured-playlists">Get All Featured Playlists</a>
     */
    @GET("/browse/featured-playlists")
    Call<FeaturedPlaylist> getFeaturedPlaylists();

    /**
     * <h3>Get All Featured Playlists</h3>
     * <p>Get a list of Spotify featured playlists (shown, for example, on a Spotify player's 'Browse' tab).</p>
     * 
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <code>message</code> and a <code>playlists</code> object. The <code>playlists</code> object contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#playlist-object-simplified">playlist objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>Once you have retrieved the list of playlist objects, you can use <a href="https://developer.spotify.com/web-api/get-playlist/">Get a Playlist</a> and <a href="https://developer.spotify.com/web-api/get-playlists-tracks/">Get a Playlist's Tracks</a> to drill down further.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-featured-playlists">Get All Featured Playlists</a>
     */
    @GET("/browse/featured-playlists")
    Call<FeaturedPlaylist> getFeaturedPlaylists(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get All New Releases</h3>
     * <p>Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player's &quot;Browse&quot; tab).</p>
     * 
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <code>message</code> and an<code>albums</code> object. The <code>albums</code> object contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#album-object-simplified">album objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>Once you have retrieved the list, you can use <a href="https://developer.spotify.com/documentation/web-api/reference/albums/get-albums-tracks/">Get an Album's Tracks</a> to drill down further.</p> <p>The results are returned in an order reflected within the Spotify clients, and therefore may not be ordered by date.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-new-releases">Get All New Releases</a>
     */
    @GET("/browse/new-releases")
    Call<NewReleases> getNewReleases();

    /**
     * <h3>Get All New Releases</h3>
     * <p>Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player's &quot;Browse&quot; tab).</p>
     * 
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <code>message</code> and an<code>albums</code> object. The <code>albums</code> object contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#album-object-simplified">album objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p> <p>Once you have retrieved the list, you can use <a href="https://developer.spotify.com/documentation/web-api/reference/albums/get-albums-tracks/">Get an Album's Tracks</a> to drill down further.</p> <p>The results are returned in an order reflected within the Spotify clients, and therefore may not be ordered by date.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-new-releases">Get All New Releases</a>
     */
    @GET("/browse/new-releases")
    Call<NewReleases> getNewReleases(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get Recommendation Genres</h3>
     * <p>Retrieve a list of available genres seed parameter values for <a href="https://developer.spotify.com/documentation/web-api/reference/browse/get-recommendations/">recommendations</a>.</p>
     * 
     * @return <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains a recommendations response object in JSON format.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-recommendation-genres">Get Recommendation Genres</a>
     */
    @GET("/recommendations/available-genre-seeds")
    Call<GenreSeeds> getRecommendationGenres();

    /**
     * <h3>Get Recommendations</h3>
     * <p>Recommendations are generated based on the available information for a given seed entity and matched against similar artists and tracks. If there is sufficient information about the provided seeds, a list of tracks will be returned together with pool size details.</p>
     * 
     * @param seed_artists <p>A comma separated list of <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for seed artists. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     * @param seed_genres <p>A comma separated list of any genres in the set of <a href="#available-genre-seeds">available genre seeds</a>. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     * @param seed_tracks <p>A comma separated list of <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for a seed track. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains a recommendations response object in JSON format.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-recommendations">Get Recommendations</a>
     */
    @GET("/recommendations")
    Call<RecommendationsResponse> getRecommendations(@Query("seed_artists") String seed_artists, @Query("seed_genres") String seed_genres, @Query("seed_tracks") String seed_tracks);

    /**
     * <h3>Get Recommendations</h3>
     * <p>Recommendations are generated based on the available information for a given seed entity and matched against similar artists and tracks. If there is sufficient information about the provided seeds, a list of tracks will be returned together with pool size details.</p>
     * 
     * @param seed_artists <p>A comma separated list of <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for seed artists. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     * @param seed_genres <p>A comma separated list of any genres in the set of <a href="#available-genre-seeds">available genre seeds</a>. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     * @param seed_tracks <p>A comma separated list of <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for a seed track. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains a recommendations response object in JSON format.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-recommendations">Get Recommendations</a>
     */
    @GET("/recommendations")
    Call<RecommendationsResponse> getRecommendations(@Query("seed_artists") String seed_artists, @Query("seed_genres") String seed_genres, @Query("seed_tracks") String seed_tracks, @QueryMap java.util.Map<String, Object> queryParameters);
}
