package de.jsone_studios.spotify.api;

import de.jsone_studios.spotify.api.models.ErrorDetails;

import java.io.IOException;

public class SpotifyApiException extends IOException {

    private ErrorDetails error;

    public SpotifyApiException(ErrorDetails error) {
        super(error.getStatus() + " " + error.getMessage());
        this.error = error;
    }

    public SpotifyApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpotifyApiException(String message, ErrorDetails error) {
        super(message + ": " + error.getStatus() + " " + error.getMessage());
        this.error = error;
    }

    public SpotifyApiException(String message, ErrorDetails error, Throwable cause) {
        super(message + ": " + error.getStatus() + " " + error.getMessage(), cause);
        this.error = error;
    }

    public ErrorDetails getError() {
        return error;
    }
}
