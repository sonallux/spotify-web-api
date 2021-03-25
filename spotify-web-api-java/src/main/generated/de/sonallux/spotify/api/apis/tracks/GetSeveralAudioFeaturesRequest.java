package de.sonallux.spotify.api.apis.tracks;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-several-audio-features">Get Audio Features for Several Tracks request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200 OK</code>
 * and the response body contains an object whose key is <code>&quot;audio_features&quot;</code> and
 * whose value is an array of audio features objects in JSON format.</p>
 * <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class GetSeveralAudioFeaturesRequest {
    private static final TypeReference<AudioFeaturesArray> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get Audio Features for Several Tracks request</h3>
     * @param apiClient <p>The API client</p>
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the tracks. Maximum: 100 IDs.</p>
     */
    public GetSeveralAudioFeaturesRequest(ApiClient apiClient, String ids) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/audio-features")
            .addQueryParameter("ids", String.valueOf(ids))
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<AudioFeaturesArray> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
