package de.sonallux.spotify.openapi;

public class OpenApiPatchException extends Exception {
    public OpenApiPatchException(String message) {
        super(message);
    }

    public OpenApiPatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
