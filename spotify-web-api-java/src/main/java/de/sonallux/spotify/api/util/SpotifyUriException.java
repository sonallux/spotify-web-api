package de.sonallux.spotify.api.util;

public class SpotifyUriException extends Exception {
    public SpotifyUriException(String message) {
        super(message);
    }

    public SpotifyUriException(String message, Throwable cause) {
        super(message, cause);
    }
}
