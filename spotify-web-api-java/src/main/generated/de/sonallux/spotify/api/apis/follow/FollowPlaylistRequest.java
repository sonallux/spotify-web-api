package de.sonallux.spotify.api.apis.follow;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-follow-playlist">Follow a Playlist request</a>
 *
 * <h3>Required OAuth scopes</h3>
 * <code>user-follow-modify</code>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body is empty.
 * On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class FollowPlaylistRequest {
    private static final TypeReference<Void> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Follow a Playlist request</h3>
     * @param apiClient <p>The API client</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> of the playlist. Any playlist can be followed, regardless of its <a href="https://developer.spotify.com/documentation/general/guides/working-with-playlists/#public-private-and-collaborative-status">public/private status</a>, as long as you know its playlist ID.</p>
     */
    public FollowPlaylistRequest(ApiClient apiClient, String playlistId) {
        this.apiClient = apiClient;
        this.request = new Request("PUT", "/playlists/{playlist_id}/followers")
            .addPathParameter("playlist_id", String.valueOf(playlistId))
        ;
    }

    /**
     * <p>Defaults to <code>true</code>. If <code>true</code> the playlist will be included in user's public playlists, if <code>false</code> it will remain private. To be able to follow playlists privately, the user must have granted the <code>playlist-modify-private</code> <a href="https://developer.spotify.com/documentation/general/guides/authorization-guide/#list-of-scopes">scope</a>.</p>
     */
    public FollowPlaylistRequest _public(boolean _public) {
        this.request.addBodyParameter("public", _public);
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Void> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
