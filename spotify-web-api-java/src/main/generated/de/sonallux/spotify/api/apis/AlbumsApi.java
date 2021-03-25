package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.apis.albums.*;
import lombok.RequiredArgsConstructor;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-albums">Albums API</a>
 */
@RequiredArgsConstructor
public class AlbumsApi {
    private final ApiClient apiClient;

    /**
     * <h3>Get an Album</h3>
     * <p>Get Spotify catalog information for a single album.</p>
     * @param id <p>The Spotify ID of the album.</p>
     * @return a {@link GetAlbumRequest} object to build and execute the request
     */
    public GetAlbumRequest getAlbum(String id) {
        return new GetAlbumRequest(apiClient, id);
    }

    /**
     * <h3>Get an Album's Tracks</h3>
     * <p>Get Spotify catalog information about an album's tracks. Optional parameters can be used to limit the number of tracks returned.</p>
     * @param id <p>The Spotify ID of the album.</p>
     * @return a {@link GetAlbumsTracksRequest} object to build and execute the request
     */
    public GetAlbumsTracksRequest getAlbumsTracks(String id) {
        return new GetAlbumsTracksRequest(apiClient, id);
    }

    /**
     * <h3>Get Multiple Albums</h3>
     * <p>Get Spotify catalog information for multiple albums identified by their Spotify IDs.</p>
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the albums. Maximum: 20 IDs.</p>
     * @return a {@link GetMultipleAlbumsRequest} object to build and execute the request
     */
    public GetMultipleAlbumsRequest getMultipleAlbums(String ids) {
        return new GetMultipleAlbumsRequest(apiClient, ids);
    }
}
