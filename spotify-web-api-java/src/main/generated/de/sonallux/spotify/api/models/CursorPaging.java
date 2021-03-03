package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-cursorpagingobject">CursorPagingObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class CursorPaging<T> {
    /**
     * <p>The cursors used to find the next set of items.</p>
     */
    private Cursor cursors;
    /**
     * <p>A link to the Web API endpoint returning the full result of the request.</p>
     */
    private String href;
    /**
     * <p>The requested data.</p>
     */
    private java.util.List<T> items;
    /**
     * <p>The maximum number of items in the response (as set in the query or by default).</p>
     */
    private int limit;
    /**
     * <p>URL to the next page of items. ( <code>null</code> if none)</p>
     */
    private String next;
    /**
     * <p>The total number of items available to return.</p>
     */
    private int total;
}
