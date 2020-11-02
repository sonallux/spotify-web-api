package de.jsone_studios.spotify.generator.java;

import com.google.common.base.CaseFormat;
import de.jsone_studios.spotify.core.model.SpotifyApiDocumentation;
import de.jsone_studios.spotify.generator.java.util.JavaPackage;
import de.jsone_studios.spotify.generator.java.util.JavaUtils;

import java.util.Map;
import java.util.stream.Collectors;

class SpotifyApiTemplate extends AbstractTemplate<SpotifyApiDocumentation> {
    @Override
    String templateName() {
        return "SpotifyApi";
    }

    @Override
    String getFileName(SpotifyApiDocumentation apiDocu) {
        return "SpotifyApi.java";
    }

    @Override
    JavaPackage getJavaPackage(SpotifyApiDocumentation apiDocu, JavaPackage basePackage) {
        return basePackage;
    }

    @Override
    Map<String, Object> buildContext(SpotifyApiDocumentation apiDocu, Map<String, Object> context) {
        context.put("endpointUrl", apiDocu.getEndpointUrl());

        var apis = apiDocu.getCategoryList().stream()
                .map(JavaUtils::getClassName)
                .map(className -> Map.of("className", className, "fieldName", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, className)))
                .collect(Collectors.toList());

        context.put("apis", apis);
        return context;
    }
}
