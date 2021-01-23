package de.sonallux.spotify.api.authorization;

import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.models.Error;

public class SpotifyAuthorizationException extends SpotifyApiException {
    public SpotifyAuthorizationException(Error error) {
        super(error);
    }

    public SpotifyAuthorizationException(String message) {
        super(message);
    }

    public SpotifyAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
