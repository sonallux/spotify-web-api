package de.sonallux.spotify.api.apis.browse;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-recommendation-genres">Get Recommendation Genres request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains a recommendations response object in JSON format.</p>
 */
public class GetRecommendationGenresRequest {
    private static final TypeReference<GenreSeeds> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get Recommendation Genres request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetRecommendationGenresRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/recommendations/available-genre-seeds")
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<GenreSeeds> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
