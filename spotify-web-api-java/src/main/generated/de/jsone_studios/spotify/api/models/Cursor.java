package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-cursorobject">CursorObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Cursor {
    /**
     * The cursor to use as key to find the next page of items.
     */
    private String after;
}
