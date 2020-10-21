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
     * <p>The cursor to use as key to find the next page of items.</p>
     */
    private String after;
}
