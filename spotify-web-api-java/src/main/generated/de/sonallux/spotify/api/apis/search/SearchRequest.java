package de.sonallux.spotify.api.apis.search;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.http.ApiCall;
import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.http.Request;
import de.sonallux.spotify.api.models.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-search">Search for an Item request</a>
 *
 * <h3>Notes</h3>
 * <p><strong>Writing a Query - Guidelines</strong></p>
 * <p><strong>Encode spaces</strong> with the hex code <code>%20</code> or <code>+</code>.</p>
 * <p><strong>Keyword matching</strong> : Matching of search keywords is <em>not</em> case-sensitive. Operators, however, should be specified in uppercase.
 * Unless surrounded by double quotation marks, keywords are matched in any order. For example:
 * <code>q=roadhouse&amp;20blues</code> matches both &quot;Blues Roadhouse&quot; and &quot;Roadhouse of the Blues&quot;.
 * <code>q=&quot;roadhouse&amp;20blues&quot;</code> matches &quot;My Roadhouse Blues&quot; but not &quot;Roadhouse of the Blues&quot;.</p>
 * <p>Searching for <strong>playlists</strong> returns results where the query keyword(s) match any part of the playlist's name or description. Only popular public playlists are returned.</p>
 * <p><strong>Operator</strong>: The operator NOT can be used to exclude results.</p>
 * <p>For example: <code>q=roadhouse%20NOT%20blues</code> returns items that match &quot;roadhouse&quot; but excludes those that also contain the keyword &quot;blues&quot;.</p>
 * <p><em>Note</em>: The operator must be specified in uppercase. Otherwise, they are handled as normal keywords to be matched.</p>
 * <p><strong>Field filters</strong> : By default, results are returned when a match is found in <em>any</em> field of the target object type. Searches can be made more specific by specifying an <code>album</code>, <code>artist</code> or <code>track</code> field filter.</p>
 * <p>For example: The query <code>q=album:gold%20artist:abba&amp;type=album</code> returns only albums with the text &quot;gold&quot; in the album name and the text &quot;abba&quot; in the artist name.</p>
 * <p>To limit the results to a particular <code>year</code>, use the field filter year with album, artist, and track searches.</p>
 * <p>For example: <code>q=bob%20year:2014</code></p>
 * <p>Or with a date range. For example: <code>q=bob%20year:1980-2020</code></p>
 * <p>To retrieve only albums released in the last two weeks, use the field filter tag:new in album searches.</p>
 * <p>To retrieve only albums with the lowest 10% popularity, use the field filter tag:hipster in album searches.
 * <em>Note</em>: This field filter only works with album searches.</p>
 * <p>Depending on object types being searched for, other field filters, include genre (applicable to tracks and artists), <code>upc</code>, and <code>isrc</code>.
 * For example: <code>q=lil%20genre:%22southern%20hip%20hop%22&amp;type=artist</code>.
 * Use double quotation marks around the genre keyword string if it contains spaces.</p>
 * <p><strong>Notes</strong></p>
 * <ul>
 * <li>
 * <p>Currently, you cannot fetch sorted results.</p>
 * </li>
 * <li>
 * <p>You cannot search for playlists that contain a certain track.</p>
 * </li>
 * <li>
 * <p>You can search only one genre at a time.</p>
 * </li>
 * <li>
 * <p>You cannot search for playlists within a user's library.</p>
 * </li>
 * <li>
 * <p>In an effort to keep the response small, but include as much information as possible, Spotify has expanded the response and intends to continue and improve the Search endpoint.</p>
 * </li>
 * <li>
 * <p>To query based on a release date query at a year level using the year scope. For example:</p>
 * <p>The query</p>
 * <p><code>https://api.spotify.com/v1/search?q=bob%20year:2014&amp;type=album</code></p>
 * <p>Returns albums released in 2014 with their names or artist names containing &quot;bob&quot;. You can also use the tag:new field filter to get just these albums, as well as compilations and singles, released in the last 2 weeks.</p>
 * </li>
 * </ul>
 *
 * <h3>Response</h3>
 * <p><strong>On success</strong>:</p>
 * <ul>
 * <li>In the response <em><strong>header</strong></em> the HTTP status code is <code>200</code> OK.</li>
 * <li>For each type provided in the <code>type</code> parameter, the response <em><strong>body</strong></em> contains an array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-artistobject">artist objects</a> / <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedalbumobject">simplified album objects</a> / <a href="https://developer.spotify.com/documentation/web-api/reference/#object-trackobject">track objects</a> / <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedshowobject">simplified show objects</a> / <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedepisodeobject">simplified episode objects</a> wrapped in a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">paging object</a> in JSON.</li>
 * </ul>
 * <p><strong>On error</strong>:</p>
 * <ul>
 * <li>The <em><strong>header</strong></em> status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a>.</li>
 * <li>The response <em><strong>body</strong></em> contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</li>
 * </ul>
 */
public class SearchRequest {
    private static final TypeReference<SearchResponse> RESPONSE_TYPE = new TypeReference<>() {};
    private final ApiClient apiClient;
    private final Request request;

    /**
     * <h3>Search for an Item request</h3>
     * @param apiClient <p>The API client</p>
     * @param q <p>Search <a href="#writing-a-query---guidelines">query</a> keywords and optional field filters and operators.<br>For example:<br><code>q=roadhouse%20blues</code>.</p>
     * @param type <p>A comma-separated list of item types to search across.<br>Valid types are: <code>album</code> , <code>artist</code>, <code>playlist</code>, <code>track</code>, <code>show</code> and <code>episode</code>.<br>Search results include hits from all the specified item types.<br>For example: <code>q=name:abacab&amp;type=album,track</code> returns both albums <em><strong>and</strong></em> tracks with &quot;abacab&quot; included in their name.</p>
     */
    public SearchRequest(ApiClient apiClient, String q, String type) {
        this.apiClient = apiClient;
        this.request = new Request("GET", "/search")
            .addQueryParameter("q", String.valueOf(q))
            .addQueryParameter("type", String.valueOf(type))
        ;
    }

    /**
     * <p>An <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>.<br>If a country code is specified, only content that is playable in that market is returned.<br><strong>Note</strong> :</p><ul><li>Playlist results are not affected by the market parameter.</li><li>If market is set to <code>from_token</code>, and a valid access token is specified in the request header, only content playable in the country associated with the user account, is returned.</li><li>Users can view the country that is associated with their account in the <a href="https://www.spotify.com/se/account/overview/">account settings</a>. A user must grant access to the <code>user-read-private</code> scope prior to when the access token is issued.</li></ul>
     */
    public SearchRequest market(String market) {
        this.request.addQueryParameter("market", String.valueOf(market));
        return this;
    }

    /**
     * <p>Maximum number of results to return.<br>Default: 20<br>Minimum: 1<br>Maximum: 50<br><strong>Note</strong> : The limit is applied within each type, not on the total response.<br>For example, if the limit value is 3 and the type is <code>artist,album</code>, the response contains 3 artists and 3 albums.</p>
     */
    public SearchRequest limit(int limit) {
        this.request.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * <p>The index of the first result to return.<br>Default: 0 (the first result).<br>Maximum offset (including limit): 1,000.<br>Use with limit to get the next page of search results.</p>
     */
    public SearchRequest offset(int offset) {
        this.request.addQueryParameter("offset", String.valueOf(offset));
        return this;
    }

    /**
     * <p>Possible values: <em>audio</em><br>If <em>include_external=audio</em> is specified the response will include any relevant audio content that is hosted externally.<br>By default external content is filtered out from responses.</p>
     */
    public SearchRequest includeExternal(String includeExternal) {
        this.request.addQueryParameter("include_external", String.valueOf(includeExternal));
        return this;
    }

    /**
     * Build the request into an executable call
     */
    public ApiCall<SearchResponse> build() {
        return apiClient.createApiCall(request, RESPONSE_TYPE);
    }
}
