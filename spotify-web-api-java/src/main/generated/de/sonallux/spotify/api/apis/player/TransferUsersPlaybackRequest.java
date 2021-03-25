package de.sonallux.spotify.api.apis.player;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-transfer-a-users-playback">Transfer a User's Playback request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-modify-playback-state</code>
 *
 * <h3>Response</h3>
 * <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
 * <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
 * <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
 */
public class TransferUsersPlaybackRequest {
    private static final TypeReference<Void> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Transfer a User's Playback request</h3>
     * @param apiClient <p>The API client</p>
     * @param deviceIds <p>A JSON array containing the ID of the device on which playback should be started/transferred.<br>For example:<code>{device_ids:[&quot;74ASZWbe4lXaubB36ztrGX&quot;]}</code><br>Note: Although an array is accepted, only a single device_id is currently supported. Supplying more than one will return <code>400 Bad Request</code></p>
     */
    public TransferUsersPlaybackRequest(ApiClient apiClient, java.util.List<String> deviceIds) {
        this.apiClient = apiClient;
        this.request = new Request("PUT", "/me/player")
            .addBodyParameter("device_ids", deviceIds)
        ;
    }

    /**
     * <p><strong>true</strong> : ensure playback happens on new device.<br><strong>false</strong> or not provided: keep the current playback state.</p>
     */
    public TransferUsersPlaybackRequest play(boolean play) {
        this.request.addBodyParameter("play", play);
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Void> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
