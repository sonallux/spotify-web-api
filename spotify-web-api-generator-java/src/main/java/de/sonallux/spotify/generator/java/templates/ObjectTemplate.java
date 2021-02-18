package de.sonallux.spotify.generator.java.templates;

import com.google.common.base.Strings;
import de.sonallux.spotify.core.model.SpotifyWebApiObject;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import de.sonallux.spotify.generator.java.util.JavaUtils;
import de.sonallux.spotify.generator.java.util.Markdown2Html;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (JavaUtils.RESERVED_WORDS.contains(property.getName())) {
            context.put("jsonName", property.getName());
            context.put("fieldName", "_" + property.getName());
        } else {
            context.put("fieldName", property.getName());
        }

        context.put("description", Markdown2Html.convertToSingleLine(property.getDescription()));
        context.put("type", JavaUtils.mapToJavaType(property.getType()));

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
