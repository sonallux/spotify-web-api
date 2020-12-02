package de.sonallux.spotify.generator.java;

import com.google.common.base.CaseFormat;
import de.sonallux.spotify.core.model.SpotifyScopes;
import de.sonallux.spotify.generator.java.util.JavaPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ScopeTemplate extends AbstractTemplate<SpotifyScopes> {
    @Override
    String templateName() {
        return "Scope";
    }

    @Override
    String getFileName(SpotifyScopes spotifyScopes) {
        return "Scope.java";
    }

    @Override
    JavaPackage getJavaPackage(SpotifyScopes spotifyScopes, JavaPackage basePackage) {
        return basePackage.child("authentication");
    }

    @Override
    Map<String, Object> buildContext(SpotifyScopes spotifyScopes, Map<String, Object> context) {
        context.put("url", spotifyScopes.getUrl());
        var scopes = spotifyScopes.getScopeList().stream()
                .map(scope -> new HashMap<>(Map.of(
                        "name", scope.getId(),
                        "enumName", CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, scope.getId()),
                        "description", scope.getDescription(),
                        "link", scope.getLink(),
                        "last", false
                )))
                .collect(Collectors.toList());
        scopes.get(scopes.size() - 1).put("last", true);
        context.put("scopes", scopes);
        return context;
    }
}
