package de.sonallux.spotify.api.apis.browse;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-categories">Get All Categories request</a>
 *
 * <h3>Response</h3>
 * <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object with a <code>categories</code> field, with an array of <a href="#categoryobject">category objects</a> (wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a>) in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
 * <p>Once you have retrieved the list, you can use <a href="https://developer.spotify.com/web-api/get-category/">Get a Category</a> to drill down further.</p>
 */
public class GetCategoriesRequest {
    private static final TypeReference<Categories> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Get All Categories request</h3>
     * @param apiClient <p>The API client</p>
     */
    public GetCategoriesRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/browse/categories")
        ;
    }

    /**
     * <p>A country: an <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>. Provide this parameter if you want to narrow the list of returned categories to those relevant to a particular country. If omitted, the returned items will be globally relevant.</p>
     */
    public GetCategoriesRequest country(String country) {
        this.request.addQueryParameter("country", String.valueOf(country));
        return this;
    }

    /**
     * <p>The desired language, consisting of an <a href="http://en.wikipedia.org/wiki/ISO_639-1">ISO 639-1</a> language code and an <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a>, joined by an underscore. For example: <code>es_MX</code>, meaning &quot;Spanish (Mexico)&quot;. Provide this parameter if you want the category metadata returned in a particular language. Note that, if <code>locale</code> is not supplied, or if the specified language is not available, all strings will be returned in the Spotify default language (American English). The <code>locale</code> parameter, combined with the <code>country</code> parameter, may give odd results if not carefully matched. For example <code>country=SE&amp;locale=de_DE</code> will return a list of categories relevant to Sweden but as German language strings.</p>
     */
    public GetCategoriesRequest locale(String locale) {
        this.request.addQueryParameter("locale", String.valueOf(locale));
        return this;
    }

    /**
     * <p>The maximum number of categories to return. Default: 20. Minimum: 1. Maximum: 50.</p>
     */
    public GetCategoriesRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first item to return. Default: 0 (the first object). Use with <code>limit</code> to get the next set of categories.</p>
     */
    public GetCategoriesRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<Categories> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
