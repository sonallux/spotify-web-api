package de.jsone_studios.spotify.generator.java.util;

import de.jsone_studios.spotify.core.model.SpotifyApiCategory;
import de.jsone_studios.spotify.core.model.SpotifyApiEndpoint;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaUtils {
    private static final Pattern ARRAY_TYPE_PATTERN = Pattern.compile("^Array\\[(.+)]$");
    private static final Pattern PAGING_OBJECT_TYPE_PATTERN = Pattern.compile("^PagingObject\\[(.+)]$");
    private static final Pattern CURSOR_PAGING_OBJECT_TYPE_PATTERN = Pattern.compile("^CursorPagingObject\\[(.+)]$");

    public static final List<String> RESERVED_WORDS = Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte",
            "case", "catch", "char", "class", "const", "continue", "default", "do", "double",
            "else", "enum", "extends", "false", "final", "finally", "float", "for",
            "goto", "if", "implements", "import", "instanceof", "int", "interface", "long",
            "native", "new", "null", "package", "private", "protected", "public", "return",
            "short", "static", "strictfp", "super", "switch", "synchronized",
            "this", "throw", "throws", "transient", "true", "try",
            "var", "void", "volatile", "while"
    );

    public static String getObjectClassName(String type) {
        return type.replace("Object", "");
    }

    public static String getFileName(String className) {
        return className + ".java";
    }

    public static String escapeFieldName(String fieldName) {
        if (RESERVED_WORDS.contains(fieldName)) {
            return "_" + fieldName;
        }
        return fieldName;
    }

    public static String shrinkEndpointId(SpotifyApiEndpoint endpoint) {
        return endpoint.getId()
                .replace("endpoint-", "")
                .replace("-the-", "-")
                .replace("-an-", "-")
                .replace("-a-", "-");
    }

    public static String mapToJavaType(String type) {
        Matcher matcher;
        if ("Timestamp".equals(type)) {
            return "java.time.LocalDateTime";
        } else if ("Object".equals(type)) {
            return "java.util.Map<String, Object>";
        } else if ("Void".equals(type)) {
            return "Void";//java.lang.Void
        } else if ((matcher = ARRAY_TYPE_PATTERN.matcher(type)).matches()) {
            return "java.util.List<" + mapToJavaType(matcher.group(1)) + ">";
        } else if ((matcher = PAGING_OBJECT_TYPE_PATTERN.matcher(type)).matches()) {
            return "Paging<" + mapToJavaType(matcher.group(1)) + ">";
        } else if ((matcher = CURSOR_PAGING_OBJECT_TYPE_PATTERN.matcher(type)).matches()) {
            return "CursorPaging<" + mapToJavaType(matcher.group(1)) + ">";
        } else if (type.contains(" | ")) {
            //Can not be mapped easily, so just use Map
            return "java.util.Map<String, Object>";
        } else {
            return getObjectClassName(type);
        }
    }

    public static String getClassName(SpotifyApiCategory category) {
        return category.getName().replace(" ", "").replace("API", "Api");
    }
}
