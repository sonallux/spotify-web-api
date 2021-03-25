package de.sonallux.spotify.api.apis.follow;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-unfollow-artists-users">Unfollow Artists or Users request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-follow-modify</code>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>204</code> No Content and the response body is empty.
 * On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class UnfollowArtistsUsersRequest {
    private static final TypeReference<Void> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Unfollow Artists or Users request</h3>
     * @param apiClient <p>The API client</p>
     * @param type <p>The ID type: either <code>artist</code> or <code>user</code>.</p>
     * @param ids <p>A JSON array of the artist or user <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a>. For example: <code>{ids:[&quot;74ASZWbe4lXaubB36ztrGX&quot;, &quot;08td7MxkoHQkXnWAYD8d6Q&quot;]}</code>. A maximum of 50 IDs can be sent in one request. <em>Note: if the <code>ids</code> parameter is present in the query string, any IDs listed here in the body will be ignored.</em></p>
     */
    public UnfollowArtistsUsersRequest(ApiClient apiClient, String type, java.util.List<String> ids) {
        this.apiClient = apiClient;
        this.request = new Request("DELETE", "/me/following")
            .addQueryParameter("type", String.valueOf(type))
            .addBodyParameter("ids", ids)
        ;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Void> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
