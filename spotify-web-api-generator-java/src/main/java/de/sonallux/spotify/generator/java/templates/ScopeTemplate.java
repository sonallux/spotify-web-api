package de.sonallux.spotify.generator.java.templates;

import com.google.common.base.CaseFormat;
import de.sonallux.spotify.core.model.SpotifyAuthorizationScopes;
import de.sonallux.spotify.generator.java.util.JavaPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ScopeTemplate extends AbstractTemplate<SpotifyAuthorizationScopes> {
    @Override
    String templateName() {
        return "Scope";
    }

    @Override
    String getFileName(SpotifyAuthorizationScopes spotifyScopes) {
        return "Scope.java";
    }

    @Override
    JavaPackage getJavaPackage(SpotifyAuthorizationScopes spotifyScopes, JavaPackage basePackage) {
        return basePackage.child("authorization");
    }

    @Override
    Map<String, Object> buildContext(SpotifyAuthorizationScopes spotifyScopes, Map<String, Object> context) {
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
