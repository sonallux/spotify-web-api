package de.sonallux.spotify.api.apis.player;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-set-volume-for-users-playback">Set Volume For User's Playback request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-modify-playback-state</code>
 *
 * <h3>Response</h3>
 * <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
 * <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
 * <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
 */
public class SetVolumeForUsersPlaybackRequest {
    private static final TypeReference<Void> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Set Volume For User's Playback request</h3>
     * @param apiClient <p>The API client</p>
     * @param volumePercent <p>The volume to set. Must be a value from 0 to 100 inclusive.</p>
     */
    public SetVolumeForUsersPlaybackRequest(ApiClient apiClient, int volumePercent) {
        this.apiClient = apiClient;
        this.request = new Request("PUT", "/me/player/volume")
            .addQueryParameter("volume_percent", String.valueOf(volumePercent))
        ;
    }

    /**
     * <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     */
    public SetVolumeForUsersPlaybackRequest deviceId(String deviceId) {
        this.request.addQueryParameter("device_id", String.valueOf(deviceId));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Void> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
