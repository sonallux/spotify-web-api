package de.sonallux.spotify.generator.java;

import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.sonallux.spotify.core.model.SpotifyWebApiEndpoint.ParameterLocation.*;

public class EndpointHelper {
    /**
     * Fixes duplicated endpoint parameters.
     * Some endpoints allow to pass data either via query argument or via body. As the url has a length limit,
     * passing to much data in the query string might result in an error response. Therefore this method removes
     * the option to pass the data via query argument and makes the body parameter mandatory.
     * @param endpoint the spotify api endpoint to fix
     */
    public static void fixDuplicateEndpointParameters(SpotifyWebApiEndpoint endpoint) {
        var duplicates = endpoint.getParameters().stream()
            .collect(Collectors.groupingBy(SpotifyWebApiEndpoint.Parameter::getName))
            .entrySet().stream()
            .filter(e -> e.getValue().size() > 1)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        duplicates.forEach((paramName, parameters) -> {
            if (!parameters.stream().map(SpotifyWebApiEndpoint.Parameter::getLocation).sorted().collect(Collectors.toList()).equals(List.of(QUERY, BODY))) {
                System.err.println("Endpoint " + endpoint.getName() + " has unfixable duplicate parameters");
                return;
            }
            endpoint.getParameters().removeIf(p -> p.getLocation() == QUERY && paramName.equals(p.getName()));
            for (var param : endpoint.getParameters()) {
                if (param.getLocation() == BODY && paramName.equals(param.getName())) {
                    param.setRequired(true);
                } else if (param.getLocation() == HEADER && "Content-Type".equals(param.getName())) {
                    param.setRequired(true);
                }
            }
        });
    }
}
