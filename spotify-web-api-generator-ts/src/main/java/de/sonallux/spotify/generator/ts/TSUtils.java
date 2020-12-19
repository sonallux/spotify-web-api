package de.sonallux.spotify.generator.ts;

import de.sonallux.spotify.core.SpotifyWebApiUtils;

import java.util.regex.Matcher;

public class TSUtils {
    public static String getObjectClassName(String type) {
        return type.replace("Object", "");
    }

    public static String mapToTsType(String type) {
        Matcher matcher;
        if ("String".equals(type)) {
            return "string";
        } else if ("Boolean".equals(type)) {
            return "boolean";
        } else if ("Integer".equals(type)) {
            return "number";
        } else if ("Float".equals(type)) {
            return "number";
        } else if ("Timestamp".equals(type)) {
            // TODO: maybe use Date
            return "string";
        } else if ("Object".equals(type)) {
            return "Record<string, any>";
        } else if ((matcher = SpotifyWebApiUtils.ARRAY_TYPE_PATTERN.matcher(type)).matches()) {
            return mapToTsType(matcher.group(1)) + "[]";
        } else if ((matcher = SpotifyWebApiUtils.PAGING_OBJECT_TYPE_PATTERN.matcher(type)).matches()) {
            return "Paging<" + mapToTsType(matcher.group(1)) + ">";
        } else if ((matcher = SpotifyWebApiUtils.CURSOR_PAGING_OBJECT_TYPE_PATTERN.matcher(type)).matches()) {
            return "CursorPaging<" + mapToTsType(matcher.group(1)) + ">";
        } else {
            return getObjectClassName(type);
        }
    }
}
