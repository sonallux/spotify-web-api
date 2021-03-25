package de.sonallux.spotify.api.apis.player;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-start-a-users-playback">Start/Resume a User's Playback request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-modify-playback-state</code>
 *
 * <h3>Response</h3>
 * <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
 * <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
 * <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
 */
public class StartUsersPlaybackRequest {
    private static final TypeReference<Void> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Start/Resume a User's Playback request</h3>
     * @param apiClient <p>The API client</p>
     */
    public StartUsersPlaybackRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("PUT", "/me/player/play")
        ;
    }

    /**
     * <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     */
    public StartUsersPlaybackRequest deviceId(String deviceId) {
        this.request.addQueryParameter("device_id", String.valueOf(deviceId));
        return this;
    }

    /**
     * <p>Spotify URI of the context to play. Valid contexts are albums, artists, playlists. Example: {&quot;context_uri&quot;: &quot;spotify:album:1Je1IMUlBXcx1Fz0WE7oPT&quot;}</p>
     */
    public StartUsersPlaybackRequest contextUri(String contextUri) {
        this.request.addBodyParameter("context_uri", contextUri);
        return this;
    }

    /**
     * <p>A JSON array of the Spotify track URIs to play. For example: {&quot;uris&quot;: [&quot;spotify:track:4iV5W9uYEdYUVa79Axb7Rh&quot;, &quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;]}</p>
     */
    public StartUsersPlaybackRequest uris(java.util.List<String> uris) {
        this.request.addBodyParameter("uris", uris);
        return this;
    }

    /**
     * <p>Indicates from where in the context playback should start. Only available when context_uri corresponds to an album or playlist object, or when the uris parameter is used. “position” is zero based and can’t be negative. Example: &quot;offset&quot;: {&quot;position&quot;: 5} “uri” is a string representing the uri of the item to start at. Example: &quot;offset&quot;: {&quot;uri&quot;: &quot;spotify:track:1301WleyT98MSxVHPZCA6M&quot;}</p>
     */
    public StartUsersPlaybackRequest offset(java.util.Map<String, Object> offset) {
        this.request.addBodyParameter("offset", offset);
        return this;
    }

    /**
     * <p>Indicates from what position to start playback. Must be a positive number. Passing in a position that is greater than the length of the track will cause the player to start playing the next song.</p>
     */
    public StartUsersPlaybackRequest positionMs(int positionMs) {
        this.request.addBodyParameter("position_ms", positionMs);
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Void> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
