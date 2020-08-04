package de.jsone_studios.spotify.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Error {
    /**
     * The HTTP status code that is also returned in the response header.
     */
    private Integer status;
    /**
     * A short description of the cause of the error.
     */
    private String message;
}