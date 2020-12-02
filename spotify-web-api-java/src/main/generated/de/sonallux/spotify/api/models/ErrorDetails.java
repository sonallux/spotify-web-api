package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDetails {
    /**
     * <p>The HTTP status code that is also returned in the response header.</p>
     */
    private Integer status;
    /**
     * <p>A short description of the cause of the error.</p>
     */
    private String message;
}
