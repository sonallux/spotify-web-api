package de.sonallux.spotify.api.apis.player;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-a-users-available-devices">Get a User's Available Devices request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-read-playback-state</code>
 *
 * <h3>Response</h3>
 * <p>A successful request will return a <code>200 OK</code> response code with a json payload that contains the device objects (see below).
 * When no available devices are found, the request will return a 200 OK response with an empty devices list.</p>
 */
public class GetUsersAvailableDevicesRequest {
    private static final TypeReference<Devices> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get a User's Available Devices request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetUsersAvailableDevicesRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/me/player/devices")
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Devices> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
