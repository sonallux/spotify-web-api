package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.apis.personalization.*;
import lombok.RequiredArgsConstructor;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-personalization">Personalization API</a>
 */
@RequiredArgsConstructor
public class PersonalizationApi {
    private final ApiClient apiClient;

    /**
     * <h3>Get a User's Top Artists</h3>
     * <p>Get the current user’s top artists based on calculated affinity.</p>
     * @return a {@link GetUsersTopArtistsRequest} object to build and execute the request
     */
    public GetUsersTopArtistsRequest getUsersTopArtists() {
        return new GetUsersTopArtistsRequest(apiClient);
    }

    /**
     * <h3>Get a User's Top Tracks</h3>
     * <p>Get the current user’s top tracks based on calculated affinity.</p>
     * @return a {@link GetUsersTopTracksRequest} object to build and execute the request
     */
    public GetUsersTopTracksRequest getUsersTopTracks() {
        return new GetUsersTopTracksRequest(apiClient);
    }
}
