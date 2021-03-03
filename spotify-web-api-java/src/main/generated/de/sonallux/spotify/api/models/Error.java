package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-errorobject">ErrorObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Error {
    /**
     * <p>A short description of the cause of the error.</p>
     */
    private String message;
    /**
     * <p>The HTTP status code (also returned in the response header; see <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">Response Status Codes</a> for more information).</p>
     */
    private int status;
}
