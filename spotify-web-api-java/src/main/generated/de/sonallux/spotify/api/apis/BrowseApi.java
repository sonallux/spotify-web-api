package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.apis.browse.*;
import lombok.RequiredArgsConstructor;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-browse">Browse API</a>
 */
@RequiredArgsConstructor
public class BrowseApi {
    private final ApiClient apiClient;

    /**
     * <h3>Get a Category's Playlists</h3>
     * <p>Get a list of Spotify playlists tagged with a particular category.</p>
     * @param categoryId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify category ID</a> for the category.</p>
     * @return a {@link GetCategoriesPlaylistsRequest} object to build and execute the request
     */
    public GetCategoriesPlaylistsRequest getCategoriesPlaylists(String categoryId) {
        return new GetCategoriesPlaylistsRequest(apiClient, categoryId);
    }

    /**
     * <h3>Get a Category</h3>
     * <p>Get a single category used to tag items in Spotify (on, for example, the Spotify player's &quot;Browse&quot; tab).</p>
     * @param categoryId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify category ID</a> for the category.</p>
     * @return a {@link GetCategoryRequest} object to build and execute the request
     */
    public GetCategoryRequest getCategory(String categoryId) {
        return new GetCategoryRequest(apiClient, categoryId);
    }

    /**
     * <h3>Get All Categories</h3>
     * <p>Get a list of categories used to tag items in Spotify (on, for example, the Spotify player's &quot;Browse&quot; tab).</p>
     * @return a {@link GetCategoriesRequest} object to build and execute the request
     */
    public GetCategoriesRequest getCategories() {
        return new GetCategoriesRequest(apiClient);
    }

    /**
     * <h3>Get All Featured Playlists</h3>
     * <p>Get a list of Spotify featured playlists (shown, for example, on a Spotify player's 'Browse' tab).</p>
     * @return a {@link GetFeaturedPlaylistsRequest} object to build and execute the request
     */
    public GetFeaturedPlaylistsRequest getFeaturedPlaylists() {
        return new GetFeaturedPlaylistsRequest(apiClient);
    }

    /**
     * <h3>Get All New Releases</h3>
     * <p>Get a list of new album releases featured in Spotify (shown, for example, on a Spotify player's &quot;Browse&quot; tab).</p>
     * @return a {@link GetNewReleasesRequest} object to build and execute the request
     */
    public GetNewReleasesRequest getNewReleases() {
        return new GetNewReleasesRequest(apiClient);
    }

    /**
     * <h3>Get Recommendation Genres</h3>
     * <p>Retrieve a list of available genres seed parameter values for <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-recommendations">recommendations</a>.</p>
     * @return a {@link GetRecommendationGenresRequest} object to build and execute the request
     */
    public GetRecommendationGenresRequest getRecommendationGenres() {
        return new GetRecommendationGenresRequest(apiClient);
    }

    /**
     * <h3>Get Recommendations</h3>
     * <p>Recommendations are generated based on the available information for a given seed entity and matched against similar artists and tracks. If there is sufficient information about the provided seeds, a list of tracks will be returned together with pool size details.</p>
     * @param seedArtists <p>A comma separated list of <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for seed artists. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     * @param seedGenres <p>A comma separated list of any genres in the set of <a href="#available-genre-seeds">available genre seeds</a>. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     * @param seedTracks <p>A comma separated list of <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for a seed track. Up to 5 seed values may be provided in any combination of <code>seed_artists</code>, <code>seed_tracks</code> and <code>seed_genres</code>.</p>
     * @return a {@link GetRecommendationsRequest} object to build and execute the request
     */
    public GetRecommendationsRequest getRecommendations(String seedArtists, String seedGenres, String seedTracks) {
        return new GetRecommendationsRequest(apiClient, seedArtists, seedGenres, seedTracks);
    }
}
