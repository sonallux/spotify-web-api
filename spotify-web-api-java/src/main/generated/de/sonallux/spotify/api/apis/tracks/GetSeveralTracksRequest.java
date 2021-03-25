package de.sonallux.spotify.api.apis.tracks;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-several-tracks">Get Several Tracks request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code>
 * OK and the response body contains an object whose key is <code>tracks</code> and whose
 * value is an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-trackobject">track objects</a>
 * in JSON format.</p>
 * <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class GetSeveralTracksRequest {
    private static final TypeReference<Tracks> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get Several Tracks request</h3>
     * @param apiClient <p>The API client</p>
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the tracks. Maximum: 50 IDs.</p>
     */
    public GetSeveralTracksRequest(ApiClient apiClient, String ids) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/tracks")
            .addQueryParameter("ids", String.valueOf(ids))
        ;
    }

    /**
     * <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>.</p>
     */
    public GetSeveralTracksRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Tracks> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
