package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-browse">Browse API</a>
 */
public interface BrowseApi {

    /**
     * <h3>Get a Category's Playlists</h3>
     * Get a list of Spotify playlists tagged with a particular category.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an array of simplified playlist objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. Once you have retrieved the list, you can use Get a Playlist and Get a Playlist’s Tracks to drill down further.
     * 
     * @param category_id The Spotify category ID for the category.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-categories-playlists">Get a Category's Playlists</a>
     */
    @GET("/browse/categories/{category_id}/playlists")
    Call<PlaylistPaging> getCategoriesPlaylists(@Path("category_id") String category_id);

    /**
     * <h3>Get a Category's Playlists</h3>
     * Get a list of Spotify playlists tagged with a particular category.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an array of simplified playlist objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. Once you have retrieved the list, you can use Get a Playlist and Get a Playlist’s Tracks to drill down further.
     * 
     * @param category_id The Spotify category ID for the category.
     * @param queryParameters A map of optional query parameters
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-categories-playlists">Get a Category's Playlists</a>
     */
    @GET("/browse/categories/{category_id}/playlists")
    Call<PlaylistPaging> getCategoriesPlaylists(@Path("category_id") String category_id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get a Category</h3>
     * Get a single category used to tag items in Spotify (on, for example, the Spotify player’s “Browse” tab).
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains a category object in JSON format. On error, the header status code is an error code and the response body contains an error object. Once you have retrieved the category, you can use Get a Category’s Playlists to drill down further.
     * 
     * @param category_id The Spotify category ID for the category.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-category">Get a Category</a>
     */
    @GET("/browse/categories/{category_id}")
    Call<Category> getCategory(@Path("category_id") String category_id);

    /**
     * <h3>Get a Category</h3>
     * Get a single category used to tag items in Spotify (on, for example, the Spotify player’s “Browse” tab).
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains a category object in JSON format. On error, the header status code is an error code and the response body contains an error object. Once you have retrieved the category, you can use Get a Category’s Playlists to drill down further.
     * 
     * @param category_id The Spotify category ID for the category.
     * @param queryParameters A map of optional query parameters
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-category">Get a Category</a>
     */
    @GET("/browse/categories/{category_id}")
    Call<Category> getCategory(@Path("category_id") String category_id, @QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get All Categories</h3>
     * Get a list of categories used to tag items in Spotify (on, for example, the Spotify player’s “Browse” tab).
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an object with a categories field, with an array of category objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. Once you have retrieved the list, you can use Get a Category to drill down further.
     * 
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-categories">Get All Categories</a>
     */
    @GET("/browse/categories")
    Call<Categories> getCategories();

    /**
     * <h3>Get All Categories</h3>
     * Get a list of categories used to tag items in Spotify (on, for example, the Spotify player’s “Browse” tab).
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains an object with a categories field, with an array of category objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. Once you have retrieved the list, you can use Get a Category to drill down further.
     * 
     * @param queryParameters A map of optional query parameters
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-categories">Get All Categories</a>
     */
    @GET("/browse/categories")
    Call<Categories> getCategories(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get All Featured Playlists</h3>
     * Get a list of Spotify featured playlists (shown, for example, on a Spotify player’s ‘Browse’ tab).
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains a message and a playlists object. The playlists object contains an array of simplified playlist objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. Once you have retrieved the list of playlist objects, you can use Get a Playlist and Get a Playlist’s Tracks to drill down further.
     * 
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-featured-playlists">Get All Featured Playlists</a>
     */
    @GET("/browse/featured-playlists")
    Call<FeaturedPlaylist> getFeaturedPlaylists();

    /**
     * <h3>Get All Featured Playlists</h3>
     * Get a list of Spotify featured playlists (shown, for example, on a Spotify player’s ‘Browse’ tab).
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains a message and a playlists object. The playlists object contains an array of simplified playlist objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. Once you have retrieved the list of playlist objects, you can use Get a Playlist and Get a Playlist’s Tracks to drill down further.
     * 
     * @param queryParameters A map of optional query parameters
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-featured-playlists">Get All Featured Playlists</a>
     */
    @GET("/browse/featured-playlists")
    Call<FeaturedPlaylist> getFeaturedPlaylists(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get All New Releases</h3>
     * Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player’s “Browse” tab).
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains a message and analbums object. The albums object contains an array of simplified album objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. Once you have retrieved the list, you can use Get an Album’s Tracks to drill down further. The results are returned in an order reflected within the Spotify clients, and therefore may not be ordered by date.
     * 
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-new-releases">Get All New Releases</a>
     */
    @GET("/browse/new-releases")
    Call<NewReleases> getNewReleases();

    /**
     * <h3>Get All New Releases</h3>
     * Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player’s “Browse” tab).
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains a message and analbums object. The albums object contains an array of simplified album objects (wrapped in a paging object) in JSON format. On error, the header status code is an error code and the response body contains an error object. Once you have retrieved the list, you can use Get an Album’s Tracks to drill down further. The results are returned in an order reflected within the Spotify clients, and therefore may not be ordered by date.
     * 
     * @param queryParameters A map of optional query parameters
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-new-releases">Get All New Releases</a>
     */
    @GET("/browse/new-releases")
    Call<NewReleases> getNewReleases(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get Recommendation Genres</h3>
     * Retrieve a list of available genres seed parameter values for recommendations.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains a recommendations response object in JSON format.
     * 
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-recommendation-genres">Get Recommendation Genres</a>
     */
    @GET("/recommendations/available-genre-seeds")
    Call<GenreSeeds> getRecommendationGenres();

    /**
     * <h3>Get Recommendations</h3>
     * Recommendations are generated based on the available information for a given seed entity and matched against similar artists and tracks. If there is sufficient information about the provided seeds, a list of tracks will be returned together with pool size details.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains a recommendations response object in JSON format.
     * 
     * @param seed_artists A comma separated list of Spotify IDs for seed artists. Up to 5 seed values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
     * @param seed_genres A comma separated list of any genres in the set of available genre seeds. Up to 5 seed values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
     * @param seed_tracks A comma separated list of Spotify IDs for a seed track. Up to 5 seed values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-recommendations">Get Recommendations</a>
     */
    @GET("/recommendations")
    Call<RecommendationsResponse> getRecommendations(@Query("seed_artists") String seed_artists, @Query("seed_genres") String seed_genres, @Query("seed_tracks") String seed_tracks);

    /**
     * <h3>Get Recommendations</h3>
     * Recommendations are generated based on the available information for a given seed entity and matched against similar artists and tracks. If there is sufficient information about the provided seeds, a list of tracks will be returned together with pool size details.
     * <h3>Response</h3>
     * On success, the HTTP status code in the response header is 200 OK and the response body contains a recommendations response object in JSON format.
     * 
     * @param seed_artists A comma separated list of Spotify IDs for seed artists. Up to 5 seed values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
     * @param seed_genres A comma separated list of any genres in the set of available genre seeds. Up to 5 seed values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
     * @param seed_tracks A comma separated list of Spotify IDs for a seed track. Up to 5 seed values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
     * @param queryParameters A map of optional query parameters
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-recommendations">Get Recommendations</a>
     */
    @GET("/recommendations")
    Call<RecommendationsResponse> getRecommendations(@Query("seed_artists") String seed_artists, @Query("seed_genres") String seed_genres, @Query("seed_tracks") String seed_tracks, @QueryMap java.util.Map<String, Object> queryParameters);
}