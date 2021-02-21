package de.sonallux.spotify.generator.java.templates;

import com.google.common.base.Strings;
import de.sonallux.spotify.generator.java.EndpointRequestBodyObject;
import de.sonallux.spotify.generator.java.EndpointRequestBodyObject.Property;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import de.sonallux.spotify.generator.java.util.JavaUtils;
import de.sonallux.spotify.generator.java.util.Markdown2Html;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestBodyTemplate extends AbstractTemplate<EndpointRequestBodyObject> {
    @Override
    String templateName() {
        return "request-body";
    }

    @Override
    String getFileName(EndpointRequestBodyObject object) {
        return JavaUtils.getFileName(object.getName());
    }

    @Override
    JavaPackage getJavaPackage(EndpointRequestBodyObject object, JavaPackage basePackage) {
        return basePackage.child("models");
    }

    @Override
    Map<String, Object> buildContext(EndpointRequestBodyObject object, Map<String, Object> context) {
        context.put("name", object.getName());
        context.put("className", object.getName());
        context.put("properties", object.getProperties().stream().map(this::buildPropertyContext).collect(Collectors.toList()));

        // Having both @RequiredArgsConstructor and @AllArgsConstructor annotation will fail if the request body does
        // only contain required properties. That's because Lombok does not check the existence of a constructor and
        // generates the same constructor twice. See this issue https://github.com/rzwitserloot/lombok/issues/1410
        // To solve this issue we only add the @RequiredArgsConstructor if at least one property is not required.
        context.put("hasNonRequiredProperty", object.getProperties().stream().anyMatch(p -> !p.isRequired()));

        context.put("endpointName", object.getEndpointName());
        if (!Strings.isNullOrEmpty(object.getEndpointLink())) {
            context.put("endpointLink", object.getEndpointLink());
        }

        return context;
    }

    private Map<String, Object> buildPropertyContext(Property property) {
        var context = new HashMap<String, Object>();
        if (JavaUtils.RESERVED_WORDS.contains(property.getName())) {
            context.put("jsonName", property.getName());
            context.put("fieldName", "_" + property.getName());
        } else {
            context.put("fieldName", property.getName());
        }

        var description = property.getDescription();
        if (description != null && !description.isBlank()) {
            context.put("hasDescription", true);
            context.put("description", Markdown2Html.convertToLines(description));
        }
        context.put("type", JavaUtils.mapToJavaType(property.getType()));
        context.put("required", property.isRequired());

        return context;
    }
}
