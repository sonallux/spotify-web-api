package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.apis.markets.*;
import lombok.RequiredArgsConstructor;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-markets">Markets API</a>
 */
@RequiredArgsConstructor
public class MarketsApi {
    private final ApiClient apiClient;

    /**
     * <h3>Get Available Markets</h3>
     * <p>Get the list of markets where Spotify is available.</p>
     * @return a {@link GetAvailableMarketsRequest} object to build and execute the request
     */
    public GetAvailableMarketsRequest getAvailableMarkets() {
        return new GetAvailableMarketsRequest(apiClient);
    }
}
