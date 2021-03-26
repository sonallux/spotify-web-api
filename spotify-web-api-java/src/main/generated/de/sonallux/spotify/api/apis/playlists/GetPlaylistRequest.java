package de.sonallux.spotify.api.apis.playlists;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-playlist">Get a Playlist request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-playlistobject">playlist object</a> in JSON format and the HTTP status code in the response header is <code>200</code> OK. If an episode is unavailable in the given <code>market</code>, its information will not be included in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Requesting playlists that you do not have the user's authorization to access returns error <code>403</code> Forbidden.</p>
 */
public class GetPlaylistRequest {
    private static final TypeReference<Playlist> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get a Playlist request</h3>
     * @param apiClient <p>The API client</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     */
    public GetPlaylistRequest(ApiClient apiClient, String playlistId) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/playlists/{playlist_id}")
            .addPathParameter("playlist_id", String.valueOf(playlistId))
        ;
        this.additionalTypes("track,episode");
    }

    /**
     * <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>. For episodes, if a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br><em>Note: If neither market or user country are provided, the episode is considered unavailable for the client.</em></p>
     */
    public GetPlaylistRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * <p>Filters for the query: a comma-separated list of the fields to return. If omitted, all fields are returned. For example, to get just the playlist''s description and URI: <code>fields=description,uri</code>. A dot separator can be used to specify non-reoccurring fields, while parentheses can be used to specify reoccurring fields within objects. For example, to get just the added date and user ID of the adder: <code>fields=tracks.items(added_at,added_by.id)</code>. Use multiple parentheses to drill down into nested objects, for example: <code>fields=tracks.items(track(name,href,album(name,href)))</code>. Fields can be excluded by prefixing them with an exclamation mark, for example: <code>fields=tracks.items(track(name,href,album(!name,href)))</code></p>
     */
    public GetPlaylistRequest fields(String fields) {
        this.request.addQueryParameter("fields", String.valueOf(fields));
        return this;
    }

    /**
     * <p>A comma-separated list of item types that your client supports besides the default <code>track</code> type. Valid types are: <code>track</code> and <code>episode</code>. <strong>Note</strong> : This parameter was introduced to allow existing clients to maintain their current behaviour and might be deprecated in the future. In addition to providing this parameter, make sure that your client properly handles cases of new types in the future by checking against the <code>type</code> field of each object.</p>
     */
    public GetPlaylistRequest additionalTypes(String additionalTypes) {
        this.request.addQueryParameter("additional_types", String.valueOf(additionalTypes));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Playlist> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
