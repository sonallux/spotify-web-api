package de.sonallux.spotify.api.apis.artists;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-an-artists-albums">Get an Artist's Albums request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedalbumobject">album objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 */
public class GetArtistsAlbumsRequest {
    private static final TypeReference<Paging<SimplifiedAlbum>> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get an Artist's Albums request</h3>
     * @param apiClient <p>The API client</p>
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist.</p>
     */
    public GetArtistsAlbumsRequest(ApiClient apiClient, String id) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/artists/{id}/albums")
            .addPathParameter("id", String.valueOf(id))
        ;
    }

    /**
     * <p>A comma-separated list of keywords that will be used to filter the response. If not supplied, all album types will be returned. Valid values are:</p><ul><li><code>album</code></li><li><code>single</code></li><li><code>appears_on</code></li><li><code>compilation</code><br>For example: <code>include_groups=album,single</code>.</li></ul>
     */
    public GetArtistsAlbumsRequest includeGroups(String includeGroups) {
        this.request.addQueryParameter("include_groups", String.valueOf(includeGroups));
        return this;
    }

    /**
     * <p>Synonym for <code>country</code>. An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>.<br>Supply this parameter to limit the response to one particular geographical market. For example, for albums available in Sweden: <code>market=SE</code>.<br><em>If not given, results will be returned for all markets and you are likely to get duplicate results per album, one for each market in which the album is available!</em></p>
     */
    public GetArtistsAlbumsRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * <p>The number of album objects to return. Default: 20. Minimum: 1. Maximum: 50. For example: <code>limit=2</code></p>
     */
    public GetArtistsAlbumsRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first album to return. Default: 0 (i.e., the first album). Use with <code>limit</code> to get the next set of albums.</p>
     */
    public GetArtistsAlbumsRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Paging<SimplifiedAlbum>> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
