package de.sonallux.spotify.api.apis.playlists;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-playlists-tracks">Get a Playlist's Items request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the response body contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedtrackobject">track objects</a> and <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedepisodeobject">episode objects</a> (depends on the <code>additional_types</code> parameter), wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a> in JSON format and the HTTP status code in the response header is <code>200</code> OK. If an episode is unavailable in the given <code>market</code>, its information will not be included in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>. Requesting playlists that you do not have the user's authorization to access returns error <code>403</code> Forbidden.</p>
 */
public class GetPlaylistsTracksRequest {
    private static final TypeReference<Paging<PlaylistTrack>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get a Playlist's Items request</h3>
     * @param apiClient <p>The API client</p>
     * @param playlistId <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the playlist.</p>
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>. For episodes, if a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.<br><em>Note: If neither market or user country are provided, the episode is considered unavailable for the client.</em></p>
     */
    public GetPlaylistsTracksRequest(ApiClient apiClient, String playlistId, String market) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/playlists/{playlist_id}/tracks")
            .addPathParameter("playlist_id", String.valueOf(playlistId))
            .addQueryParameter("market", String.valueOf(market))
        ;
    }

    /**
     * <p>Filters for the query: a comma-separated list of the fields to return. If omitted, all fields are returned. For example, to get just the total number of items and the request limit:<br><code>fields=total,limit</code><br>A dot separator can be used to specify non-reoccurring fields, while parentheses can be used to specify reoccurring fields within objects. For example, to get just the added date and user ID of the adder:<br><code>fields=items(added_at,added_by.id)</code><br>Use multiple parentheses to drill down into nested objects, for example:<br><code>fields=items(track(name,href,album(name,href)))</code><br>Fields can be excluded by prefixing them with an exclamation mark, for example:<br><code>fields=items.track.album(!external_urls,images)</code></p>
     */
    public GetPlaylistsTracksRequest fields(String fields) {
        this.request.addQueryParameter("fields", String.valueOf(fields));
        return this;
    }

    /**
     * <p>The maximum number of items to return. Default: 100. Minimum: 1. Maximum: 100.</p>
     */
    public GetPlaylistsTracksRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first item to return. Default: 0 (the first object).</p>
     */
    public GetPlaylistsTracksRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * <p>A comma-separated list of item types that your client supports besides the default <code>track</code> type. Valid types are: <code>track</code> and <code>episode</code>. <strong>Note</strong> : This parameter was introduced to allow existing clients to maintain their current behaviour and might be deprecated in the future. In addition to providing this parameter, make sure that your client properly handles cases of new types in the future by checking against the <code>type</code> field of each object.</p>
     */
    public GetPlaylistsTracksRequest additionalTypes(String additionalTypes) {
        this.request.addQueryParameter("additional_types", String.valueOf(additionalTypes));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Paging<PlaylistTrack>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
