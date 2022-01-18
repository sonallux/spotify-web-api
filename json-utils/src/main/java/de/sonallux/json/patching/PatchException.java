package de.sonallux.json.patching;

public class PatchException extends Exception {
    public PatchException(String message) {
        super(message);
    }

    public PatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
