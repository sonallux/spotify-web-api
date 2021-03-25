package de.sonallux.spotify.api.apis.episodes;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-multiple-episodes">Get Multiple Episodes request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code>
 * OK and the response body contains an object whose key is <code>episodes</code> and whose
 * value is an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-episodeobject">episode objects</a>
 * in JSON format.</p>
 * <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position.
 * Duplicate <code>ids</code> in the query will result in duplicate objects in the response. If an episode is unavailable in the given <code>market</code>, a <code>null</code> value is returned.
 * On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class GetMultipleEpisodesRequest {
    private static final TypeReference<Episodes> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get Multiple Episodes request</h3>
     * @param apiClient <p>The API client</p>
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the episodes. Maximum: 50 IDs.</p>
     */
    public GetMultipleEpisodesRequest(ApiClient apiClient, String ids) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/episodes")
            .addQueryParameter("ids", String.valueOf(ids))
        ;
    }

    /**
     * <p>An <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>. If a country code is specified, only shows and episodes that are available in that market will be returned.<br>If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br><em>Note: If neither market or user country are provided, the content is considered unavailable for the client.</em><br>Users can view the country that is associated with their account in the <a href="https://www.spotify.com/se/account/overview/">account settings</a>.</p>
     */
    public GetMultipleEpisodesRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Episodes> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
