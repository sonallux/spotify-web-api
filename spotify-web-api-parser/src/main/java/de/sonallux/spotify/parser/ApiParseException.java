package de.sonallux.spotify.parser;

public class ApiParseException extends Exception {
    public ApiParseException(String message) {
        super(message);
    }

    public ApiParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
