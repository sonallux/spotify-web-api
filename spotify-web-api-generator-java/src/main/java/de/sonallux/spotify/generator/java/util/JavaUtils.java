package de.sonallux.spotify.generator.java.util;

import de.sonallux.spotify.core.SpotifyWebApiUtils;
import de.sonallux.spotify.core.model.SpotifyWebApiCategory;
import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;

public class JavaUtils {
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
        if (fieldName.contains("_")) {
            return LOWER_UNDERSCORE.converterTo(LOWER_CAMEL).convert(fieldName);
        }
        return fieldName;
    }

    public static String shrinkEndpointId(SpotifyWebApiEndpoint endpoint) {
        return endpoint.getId()
                .replace("endpoint-", "")
                .replace("-the-", "-")
                .replace("-an-", "-")
                .replace("-a-", "-");
    }

    public static String mapToJavaType(String type) {
        Matcher matcher;
        if ("Timestamp".equals(type)) {
            return "java.time.Instant";
        } else if ("Object".equals(type)) {
            return "java.util.Map<String, Object>";
        } else if ("Void".equals(type)) {
            return "Void";//java.lang.Void
        } else if ((matcher = SpotifyWebApiUtils.ARRAY_TYPE_PATTERN.matcher(type)).matches()) {
            return "java.util.List<" + mapToJavaType(matcher.group(1)) + ">";
        } else if ((matcher = SpotifyWebApiUtils.PAGING_OBJECT_TYPE_PATTERN.matcher(type)).matches()) {
            return "Paging<" + mapToJavaType(matcher.group(1)) + ">";
        } else if ((matcher = SpotifyWebApiUtils.CURSOR_PAGING_OBJECT_TYPE_PATTERN.matcher(type)).matches()) {
            return "CursorPaging<" + mapToJavaType(matcher.group(1)) + ">";
        } else if (type.contains(" | ")) {
            //Can not be mapped easily, so just use Map
            return "java.util.Map<String, Object>";
        } else {
            return getObjectClassName(type);
        }
    }

    public static String mapToPrimitiveJavaType(String type) {
        if ("String".equals(type)) {
            return "String";
        } else if ("Boolean".equals(type)) {
            return "boolean";
        } else if ("Integer".equals(type)) {
            return "int";
        } else if ("Float".equals(type)) {
            return "float";
        } else {
            return mapToJavaType(type);
        }
    }

    public static String getClassName(SpotifyWebApiCategory category) {
        return category.getName().replace(" ", "").replace("API", "Api");
    }
}
