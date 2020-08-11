package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-search">Search API</a>
 */
public interface SearchApi {

    /**
     * <h3>Search for an Item</h3>
     * Get Spotify Catalog information about albums, artists, playlists, tracks, shows or episodes that match a keyword string.
     * <h3>Response</h3>
     * On success: On error:
     * 
     * @param q Search query keywords and optional field filters and operators. For example: q=roadhouse%20blues.
     * @param type A comma-separated list of item types to search across. Valid types are: album , artist, playlist, track, show and episode. Search results include hits from all the specified item types. For example: q=name:abacab&type=album,track returns both albums and tracks with “abacab” included in their name.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-search">Search for an Item</a>
     */
    @GET("/search")
    Call<SearchResponse> search(@Query("q") String q, @Query("type") String type);

    /**
     * <h3>Search for an Item</h3>
     * Get Spotify Catalog information about albums, artists, playlists, tracks, shows or episodes that match a keyword string.
     * <h3>Response</h3>
     * On success: On error:
     * 
     * @param q Search query keywords and optional field filters and operators. For example: q=roadhouse%20blues.
     * @param type A comma-separated list of item types to search across. Valid types are: album , artist, playlist, track, show and episode. Search results include hits from all the specified item types. For example: q=name:abacab&type=album,track returns both albums and tracks with “abacab” included in their name.
     * @param queryParameters A map of optional query parameters
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-search">Search for an Item</a>
     */
    @GET("/search")
    Call<SearchResponse> search(@Query("q") String q, @Query("type") String type, @QueryMap java.util.Map<String, Object> queryParameters);
}