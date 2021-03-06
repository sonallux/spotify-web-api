package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-pagingobject">PagingObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Paging<T> {
    /**
     * <p>A link to the Web API endpoint returning the full result of the request</p>
     */
    public String href;
    /**
     * <p>The requested data.</p>
     */
    public java.util.List<T> items;
    /**
     * <p>The maximum number of items in the response (as set in the query or by default).</p>
     */
    public int limit;
    /**
     * <p>URL to the next page of items. ( <code>null</code> if none)</p>
     */
    public String next;
    /**
     * <p>The offset of the items returned (as set in the query or by default)</p>
     */
    public int offset;
    /**
     * <p>URL to the previous page of items. ( <code>null</code> if none)</p>
     */
    public String previous;
    /**
     * <p>The total number of items available to return.</p>
     */
    public int total;
}
