package de.sonallux.spotify.api.util;

public final class TextUtil {
    private TextUtil() {}

    public static boolean hasText(String str) {
        return str != null && str.length() > 0;
    }
}
