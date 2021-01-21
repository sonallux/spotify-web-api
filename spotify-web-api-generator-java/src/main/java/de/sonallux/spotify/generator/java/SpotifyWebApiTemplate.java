package de.sonallux.spotify.generator.java;

import com.google.common.base.CaseFormat;
import de.sonallux.spotify.core.model.SpotifyApiDocumentation;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import de.sonallux.spotify.generator.java.util.JavaUtils;

import java.util.Map;
import java.util.stream.Collectors;

class SpotifyWebApiTemplate extends AbstractTemplate<SpotifyApiDocumentation> {
    @Override
    String templateName() {
        return "SpotifyWebApi";
    }

    @Override
    String getFileName(SpotifyApiDocumentation apiDocu) {
        return "SpotifyWebApi.java";
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
