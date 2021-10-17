package de.sonallux.spotify.parser;

import de.sonallux.spotify.core.model.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Injects the scopes from the {@link de.sonallux.spotify.core.model.SpotifyAuthorizationScopes} into the scopes field of
 * {@link de.sonallux.spotify.core.model.SpotifyWebApiEndpoint}
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ScopeInjector {
    private static final String API_NAME = "web-api";

    private final Map<String, SpotifyWebApiEndpoint> endpointMap;

    static void run(SpotifyWebApi spotifyWebApi) {
        var endpointMap = spotifyWebApi.getCategoryList().stream()
            .flatMap(category -> category.getEndpointList().stream())
            .collect(Collectors.toMap(SpotifyWebApiEndpoint::getId, e -> e));

        var scopeInjector = new ScopeInjector(endpointMap);
        scopeInjector.injectScopes(spotifyWebApi.getScopes());
    }

    void injectScopes(SpotifyAuthorizationScopes scopes) {
        scopes.getScopeList().stream()
            .sorted(Comparator.comparing(SpotifyScope::getId))
            .forEach(this::injectScope);
    }

    private void injectScope(SpotifyScope scope) {
        scope.getEndpoints().stream()
            .filter(endpointLink -> API_NAME.equals(endpointLink.getApi()))
            .map(SpotifyScope.EndpointLink::getEndpoint)
            .map(endpointMap::get)
            .forEach(endpoint -> endpoint.addScope(scope.getId()));
    }
}
