package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.apis.shows.*;
import lombok.RequiredArgsConstructor;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-shows">Shows API</a>
 */
@RequiredArgsConstructor
public class ShowsApi {
    private final ApiClient apiClient;

    /**
     * <h3>Get a Show</h3>
     * <p>Get Spotify catalog information for a single show identified by its unique Spotify ID.</p>
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the show.</p>
     * @return a {@link GetShowRequest} object to build and execute the request
     */
    public GetShowRequest getShow(String id) {
        return new GetShowRequest(apiClient, id);
    }

    /**
     * <h3>Get a Show's Episodes</h3>
     * <p>Get Spotify catalog information about an show's episodes. Optional parameters can be used to limit the number of episodes returned.</p>
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the show.</p>
     * @return a {@link GetShowsEpisodesRequest} object to build and execute the request
     */
    public GetShowsEpisodesRequest getShowsEpisodes(String id) {
        return new GetShowsEpisodesRequest(apiClient, id);
    }

    /**
     * <h3>Get Multiple Shows</h3>
     * <p>Get Spotify catalog information for several shows based on their Spotify IDs.</p>
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the shows. Maximum: 50 IDs.</p>
     * @return a {@link GetMultipleShowsRequest} object to build and execute the request
     */
    public GetMultipleShowsRequest getMultipleShows(String ids) {
        return new GetMultipleShowsRequest(apiClient, ids);
    }
}
