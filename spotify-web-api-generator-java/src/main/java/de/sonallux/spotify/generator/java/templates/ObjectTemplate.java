package de.sonallux.spotify.generator.java.templates;

import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import de.sonallux.spotify.core.model.SpotifyWebApiObject;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import de.sonallux.spotify.generator.java.util.JavaUtils;
import de.sonallux.spotify.generator.java.util.Markdown2Html;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;

public class ObjectTemplate extends AbstractTemplate<SpotifyWebApiObject> {

    @Override
    public String templateName() {
        return "object";
    }

    @Override
    JavaPackage getJavaPackage(SpotifyWebApiObject object, JavaPackage basePackage) {
        return basePackage.child("models");
    }

    @Override
    public String getFileName(SpotifyWebApiObject object) {
        return JavaUtils.getFileName(getClassName(object));
    }

    @Override
    public Map<String, Object> buildContext(SpotifyWebApiObject object, Map<String, Object> context) {
        context.put("name", object.getName());
        context.put("className", getClassName(object));
        context.put("properties", object.getProperties().stream().map(this::buildPropertyContext).collect(Collectors.toList()));
        if (!Strings.isNullOrEmpty(object.getLink())) {
            context.put("documentationLink", object.getLink());
        }

        if (object.getProperties().size() == 0) {
            context.put("superClass", "java.util.HashMap<String, String>");
        }

        if ("PagingObject".equals(object.getName()) || "CursorPagingObject".equals(object.getName())) {
            fixContextForPaging(context);
        }
        return context;
    }

    private String getClassName(SpotifyWebApiObject object) {
        return JavaUtils.getObjectClassName(object.getName());
    }

    private Map<String, Object> buildPropertyContext(SpotifyWebApiObject.Property property) {
        var context = new HashMap<String, Object>();
        var propertyName = property.getName();
        if (JavaUtils.RESERVED_WORDS.contains(propertyName)) {
            context.put("isReservedKeywordProperty", true);
            context.put("fieldName", "_" + propertyName);
        } else {
            // spotify property names are in lower underscore case (e.g album_type)
            // but java convention is lower camel case for fields, therefore transform
            // JsonProperty annotation is not needed, because the object mapper is configured
            // with the correct property naming strategy
            context.put("fieldName", LOWER_UNDERSCORE.converterTo(LOWER_CAMEL).convert(propertyName));
        }

        var description = property.getDescription();
        if (description != null && !description.isBlank()) {
            context.put("hasDescription", true);
            context.put("description", Markdown2Html.convertToLines(description));
        }
        context.put("type", JavaUtils.mapToPrimitiveJavaType(property.getType()));

        return context;
    }

    private void fixContextForPaging(Map<String, Object> context) {
        context.put("className", context.get("className") + "<T>");
        @SuppressWarnings("unchecked")
        var properties = (List<Map<String, Object>>)context.get("properties");
        for (var property : properties) {
            if ("items".equals(property.get("fieldName"))) {
                property.put("type", "java.util.List<T>");
            }
        }
    }

}
