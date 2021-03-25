package de.sonallux.spotify.api.apis.artists;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-an-artist">Get an Artist request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/reference/#object-artistobject">artist object</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class GetArtistRequest {
    private static final TypeReference<Artist> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get an Artist request</h3>
     * @param apiClient <p>The API client</p>
     * @param id <p>The Spotify ID of the artist.</p>
     */
    public GetArtistRequest(ApiClient apiClient, String id) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/artists/{id}")
            .addPathParameter("id", String.valueOf(id))
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Artist> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
