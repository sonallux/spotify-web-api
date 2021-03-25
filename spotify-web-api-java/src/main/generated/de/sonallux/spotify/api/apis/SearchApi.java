package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.apis.search.*;
import lombok.RequiredArgsConstructor;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-search">Search API</a>
 */
@RequiredArgsConstructor
public class SearchApi {
    private final ApiClient apiClient;

    /**
     * <h3>Search for an Item</h3>
     * <p>Get Spotify Catalog information about albums, artists, playlists, tracks, shows or episodes that match a keyword string.</p>
     * @param q <p>Search <a href="#writing-a-query---guidelines">query</a> keywords and optional field filters and operators.<br>For example:<br><code>q=roadhouse%20blues</code>.</p>
     * @param type <p>A comma-separated list of item types to search across.<br>Valid types are: <code>album</code> , <code>artist</code>, <code>playlist</code>, <code>track</code>, <code>show</code> and <code>episode</code>.<br>Search results include hits from all the specified item types.<br>For example: <code>q=name:abacab&amp;type=album,track</code> returns both albums <em><strong>and</strong></em> tracks with &quot;abacab&quot; included in their name.</p>
     * @return a {@link SearchRequest} object to build and execute the request
     */
    public SearchRequest search(String q, String type) {
        return new SearchRequest(apiClient, q, type);
    }
}
