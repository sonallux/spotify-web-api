package de.sonallux.spotify.api.apis.artists;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-an-artists-top-tracks">Get an Artist's Top Tracks request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>&quot;tracks&quot;</code> and whose value is an array of up to 10 <a href="https://developer.spotify.com/documentation/web-api/reference/#object-trackobject">track objects</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class GetArtistsTopTracksRequest {
    private static final TypeReference<Tracks> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get an Artist's Top Tracks request</h3>
     * @param apiClient <p>The API client</p>
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist</p>
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Synonym for <code>country</code>.</p>
     */
    public GetArtistsTopTracksRequest(ApiClient apiClient, String id, String market) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/artists/{id}/top-tracks")
            .addPathParameter("id", String.valueOf(id))
            .addQueryParameter("market", String.valueOf(market))
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Tracks> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
