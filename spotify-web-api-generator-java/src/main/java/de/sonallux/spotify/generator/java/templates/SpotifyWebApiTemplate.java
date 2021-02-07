package de.sonallux.spotify.generator.java.templates;

import com.google.common.base.CaseFormat;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import de.sonallux.spotify.generator.java.util.JavaUtils;

import java.util.Map;
import java.util.stream.Collectors;

public class SpotifyWebApiTemplate extends AbstractTemplate<SpotifyWebApi> {
    @Override
    String templateName() {
        return "SpotifyWebApi";
    }

    @Override
    String getFileName(SpotifyWebApi apiDocu) {
        return "SpotifyWebApi.java";
    }

    @Override
    JavaPackage getJavaPackage(SpotifyWebApi apiDocu, JavaPackage basePackage) {
        return basePackage;
    }

    @Override
    Map<String, Object> buildContext(SpotifyWebApi apiDocu, Map<String, Object> context) {
        context.put("endpointUrl", apiDocu.getEndpointUrl());

        var apis = apiDocu.getCategoryList().stream()
                .map(JavaUtils::getClassName)
                .map(className -> Map.of("className", className, "fieldName", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, className)))
                .collect(Collectors.toList());

        context.put("apis", apis);
        return context;
    }
}
