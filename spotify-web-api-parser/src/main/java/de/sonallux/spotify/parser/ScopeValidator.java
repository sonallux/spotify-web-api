package de.sonallux.spotify.parser;

import de.sonallux.spotify.core.model.SpotifyWebApi;

import java.util.stream.Collectors;

class ScopeValidator {
    static void validateScopes(SpotifyWebApi spotifyWebApi) throws ApiParseException {
        var scopes = spotifyWebApi.getScopes();
        var categories = spotifyWebApi.getCategories();

        var error = new StringBuilder();

        //Validate if endpoints referenced by scopes are present
        for (var scope : scopes.getScopeList()) {
            for (var link : scope.getEndpoints()) {
                if (link.getEndpoint() == null) {
                    continue;
                }
                var endpoint = categories.values().stream()
                    .flatMap(c -> c.getEndpointList().stream())
                    .filter(e -> e.getId().equals(link.getEndpoint()))
                    .findFirst();
                if (endpoint.isEmpty()) {
                    error.append(String.format("Scope %s has unknown endpoint reference: %s", scope.getId(), link.getEndpoint())).append("\n");
                }
            }
        }

        //Validate if scopes referenced by endpoints are present
        var endpointScopes = categories.values().stream().flatMap(c -> c.getEndpointList().stream())
            .flatMap(e -> e.getScopes().stream())
            .distinct()
            .collect(Collectors.toList());
        for (var endpointScope : endpointScopes) {
            var scope = scopes.getScope(endpointScope);
            if (scope.isEmpty()) {
                error.append(String.format("Endpoint has unknown scope: %s", endpointScope)).append("\n");
            }
        }
        var errorText = error.toString();
        if (!errorText.isEmpty()) {
            throw new ApiParseException(errorText);
        }
    }
}
