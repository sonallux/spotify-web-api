package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-cursorpagingobject">CursorPagingObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class CursorPaging<T> {
    /**
     * The cursors used to find the next set of items.
     */
    private Cursor cursors;
    /**
     * A link to the Web API endpoint returning the full result of the request.
     */
    private String href;
    /**
     * The requested data.
     */
    private java.util.List<T> items;
    /**
     * The maximum number of items in the response (as set in the query or by default).
     */
    private Integer limit;
    /**
     * URL to the next page of items. ( null if none)
     */
    private String next;
    /**
     * The total number of items available to return.
     */
    private Integer total;
}
