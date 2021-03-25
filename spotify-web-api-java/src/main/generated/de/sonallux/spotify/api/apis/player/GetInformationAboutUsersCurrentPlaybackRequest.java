package de.sonallux.spotify.api.apis.player;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-read-playback-state</code>
 *
 * <h3>Response</h3>
 * <p>A successful request will return a <code>200 OK</code> response code with a json payload that contains information about the current playback. The information returned is for the last known state, which means an inactive device could be returned if it was the last one to execute playback.
 * When no available devices are found, the request will return a <code>200 OK</code> response but with no data populated.</p>
 */
public class GetInformationAboutUsersCurrentPlaybackRequest {
    private static final TypeReference<CurrentlyPlayingContext> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get Information About The User's Current Playback request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetInformationAboutUsersCurrentPlaybackRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me/player")
        ;
    }

    /**
     * <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>.</p>
     */
    public GetInformationAboutUsersCurrentPlaybackRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * <p>A comma-separated list of item types that your client supports besides the default <code>track</code> type. Valid types are: <code>track</code> and <code>episode</code>. An unsupported type in the response is expected to be represented as <code>null</code> value in the <code>item</code> field. <strong>Note</strong>: This parameter was introduced to allow existing clients to maintain their current behaviour and might be deprecated in the future. In addition to providing this parameter, make sure that your client properly handles cases of new</p>
     */
    public GetInformationAboutUsersCurrentPlaybackRequest additionalTypes(String additionalTypes) {
        this.request.addQueryParameter("additional_types", String.valueOf(additionalTypes));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<CurrentlyPlayingContext> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
