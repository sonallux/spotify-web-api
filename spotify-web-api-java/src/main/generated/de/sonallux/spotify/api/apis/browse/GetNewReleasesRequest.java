package de.sonallux.spotify.api.apis.browse;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-new-releases">Get All New Releases request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <code>message</code> and an<code>albums</code> object. The <code>albums</code> object contains an array of simplified <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedalbumobject">album objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 * <p>Once you have retrieved the list, you can use <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-an-albums-tracks">Get an Album's Tracks</a> to drill down further.</p>
 * <p>The results are returned in an order reflected within the Spotify clients, and therefore may not be ordered by date.</p>
 */
public class GetNewReleasesRequest {
    private static final TypeReference<NewReleases> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get All New Releases request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetNewReleasesRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/browse/new-releases")
        ;
    }

    /**
     * <p>A country: an <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>. Provide this parameter if you want the list of returned items to be relevant to a particular country. If omitted, the returned items will be relevant to all countries.</p>
     */
    public GetNewReleasesRequest country(String country) {
        this.request.addQueryParameter("country", String.valueOf(country));
        return this;
    }

    /**
     * <p>The maximum number of items to return. Default: 20. Minimum: 1. Maximum: 50.</p>
     */
    public GetNewReleasesRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first item to return. Default: 0 (the first object). Use with <code>limit</code> to get the next set of items.</p>
     */
    public GetNewReleasesRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<NewReleases> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
