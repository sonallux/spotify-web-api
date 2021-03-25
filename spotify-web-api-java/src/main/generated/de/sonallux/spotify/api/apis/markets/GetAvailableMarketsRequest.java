package de.sonallux.spotify.api.apis.markets;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-available-markets">Get Available Markets request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is 200 OK and the response body contains a list of the countries in which Spotify is available, identified by their <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> with additional country codes for special territories. On error, the header status code is an error code and the response body contains an error object.</p>
 */
public class GetAvailableMarketsRequest {
    private static final TypeReference<Markets> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get Available Markets request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetAvailableMarketsRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/markets")
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Markets> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
