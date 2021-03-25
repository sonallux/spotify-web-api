package de.sonallux.spotify.api.apis.browse;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-featured-playlists">Get All Featured Playlists request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <code>message</code> and a <code>playlists</code> object. The <code>playlists</code> object contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedplaylistobject">playlist objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 * <p>Once you have retrieved the list of playlist objects, you can use <a href="https://developer.spotify.com/web-api/get-playlist/">Get a Playlist</a> and <a href="https://developer.spotify.com/web-api/get-playlists-tracks/">Get a Playlist's Tracks</a> to drill down further.</p>
 */
public class GetFeaturedPlaylistsRequest {
    private static final TypeReference<FeaturedPlaylist> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get All Featured Playlists request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetFeaturedPlaylistsRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/browse/featured-playlists")
        ;
    }

    /**
     * <p>A country: an <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>. Provide this parameter if you want the list of returned items to be relevant to a particular country. If omitted, the returned items will be relevant to all countries.</p>
     */
    public GetFeaturedPlaylistsRequest country(String country) {
        this.request.addQueryParameter("country", String.valueOf(country));
        return this;
    }

    /**
     * <p>The desired language, consisting of a lowercase <a href="http://en.wikipedia.org/wiki/ISO_639-1">ISO 639-1 language code</a> and an uppercase <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>, joined by an underscore. For example: <code>es_MX</code>, meaning &quot;Spanish (Mexico)&quot;. Provide this parameter if you want the results returned in a particular language (where available). Note that, if <code>locale</code> is not supplied, or if the specified language is not available, all strings will be returned in the Spotify default language (American English). The <code>locale</code> parameter, combined with the <code>country</code> parameter, may give odd results if not carefully matched. For example <code>country=SE&amp;locale=de_DE</code> will return a list of categories relevant to Sweden but as German language strings.</p>
     */
    public GetFeaturedPlaylistsRequest locale(String locale) {
        this.request.addQueryParameter("locale", String.valueOf(locale));
        return this;
    }

    /**
     * <p>A timestamp in <a href="http://en.wikipedia.org/wiki/ISO_8601">ISO 8601 format</a>: <code>yyyy-MM-ddTHH:mm:ss</code>. Use this parameter to specify the user's local time to get results tailored for that specific date and time in the day. If not provided, the response defaults to the current UTC time. Example: &quot;2014-10-23T09:00:00&quot; for a user whose local time is 9AM. If there were no featured playlists (or there is no data) at the specified time, the response will revert to the current UTC time.</p>
     */
    public GetFeaturedPlaylistsRequest timestamp(String timestamp) {
        this.request.addQueryParameter("timestamp", String.valueOf(timestamp));
        return this;
    }

    /**
     * <p>The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.</p>
     */
    public GetFeaturedPlaylistsRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first item to return. Default: 0 (the first object). Use with <code>limit</code> to get the next set of items.</p>
     */
    public GetFeaturedPlaylistsRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<FeaturedPlaylist> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
