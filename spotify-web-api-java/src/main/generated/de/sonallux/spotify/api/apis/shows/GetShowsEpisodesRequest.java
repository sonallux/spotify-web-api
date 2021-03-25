package de.sonallux.spotify.api.apis.shows;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-a-shows-episodes">Get a Show's Episodes request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedepisodeobject">simplified episode objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format.</p>
 * <p>On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#error-details">error object</a>.</p>
 * <p>If a show is unavailable in the given <code>market</code> the HTTP status code in the response header is <code>404</code> NOT FOUND. Unavailable episodes are filtered out.</p>
 */
public class GetShowsEpisodesRequest {
    private static final TypeReference<Paging<SimplifiedEpisode>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get a Show's Episodes request</h3>
     * @param apiClient <p>The API client</p>
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the show.</p>
     */
    public GetShowsEpisodesRequest(ApiClient apiClient, String id) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/shows/{id}/episodes")
            .addPathParameter("id", String.valueOf(id))
        ;
    }

    /**
     * <p>An <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>. If a country code is specified, only shows and episodes that are available in that market will be returned.<br>If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br><em>Note: If neither market or user country are provided, the content is considered unavailable for the client.</em><br>Users can view the country that is associated with their account in the <a href="https://www.spotify.com/se/account/overview/">account settings</a>.</p>
     */
    public GetShowsEpisodesRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * <p>The maximum number of episodes to return. Default: 20. Minimum: 1. Maximum: 50.</p>
     */
    public GetShowsEpisodesRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first episode to return. Default: 0 (the first object). Use with limit to get the next set of episodes.</p>
     */
    public GetShowsEpisodesRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Paging<SimplifiedEpisode>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
