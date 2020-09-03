package de.jsone_studios.spotify.generator.ts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TSUtils {

    private static final Pattern ARRAY_TYPE_PATTERN = Pattern.compile("^Array\\[(.+)]$");
    private static final Pattern PAGING_OBJECT_TYPE_PATTERN = Pattern.compile("^PagingObject\\[(.+)]$");
    private static final Pattern CURSOR_PAGING_OBJECT_TYPE_PATTERN = Pattern.compile("^CursorPagingObject\\[(.+)]$");

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
        } else if ((matcher = ARRAY_TYPE_PATTERN.matcher(type)).matches()) {
            return mapToTsType(matcher.group(1)) + "[]";
        } else if ((matcher = PAGING_OBJECT_TYPE_PATTERN.matcher(type)).matches()) {
            return "Paging<" + mapToTsType(matcher.group(1)) + ">";
        } else if ((matcher = CURSOR_PAGING_OBJECT_TYPE_PATTERN.matcher(type)).matches()) {
            return "CursorPaging<" + mapToTsType(matcher.group(1)) + ">";
        } else {
            return getObjectClassName(type);
        }
    }
}
