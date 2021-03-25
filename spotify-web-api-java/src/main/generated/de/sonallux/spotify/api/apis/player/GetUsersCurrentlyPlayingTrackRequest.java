package de.sonallux.spotify.api.apis.player;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-the-users-currently-playing-track">Get the User's Currently Playing Track request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-read-currently-playing, user-read-playback-state</code>
 *
 * <h3>Response</h3>
 * <p>A successful request will return a <code>200 OK</code> response code with a json payload that contains information about the currently playing track or episode and its context (see below). The information returned is for the last known state, which means an inactive device could be returned if it was the last one to execute playback.</p>
 * <p>When no available devices are found, the request will return a <code>200 OK</code> response but with no data populated.</p>
 * <p>When no track is currently playing, the request will return a <code>204 NO CONTENT</code> response with no payload.</p>
 * <p>If private session is enabled the response will be a <code>204 NO CONTENT</code> with an empty payload.</p>
 */
public class GetUsersCurrentlyPlayingTrackRequest {
    private static final TypeReference<CurrentlyPlaying> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get the User's Currently Playing Track request</h3>
     * @param apiClient <p>The API client</p>
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>.</p>
     */
    public GetUsersCurrentlyPlayingTrackRequest(ApiClient apiClient, String market) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me/player/currently-playing")
            .addQueryParameter("market", String.valueOf(market))
        ;
    }

    /**
     * <p>A comma-separated list of item types that your client supports besides the default <code>track</code> type. Valid types are: <code>track</code> and <code>episode</code>. An unsupported type in the response is expected to be represented as <code>null</code> value in the <code>item</code> field. <strong>Note</strong> : This parameter was introduced to allow existing clients to maintain their current behaviour and might be deprecated in the future. In addition to providing this parameter, make sure that your client properly handles cases of new types in the future by checking against the <code>currently_playing_type</code> field.</p>
     */
    public GetUsersCurrentlyPlayingTrackRequest additionalTypes(String additionalTypes) {
        this.request.addQueryParameter("additional_types", String.valueOf(additionalTypes));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<CurrentlyPlaying> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
