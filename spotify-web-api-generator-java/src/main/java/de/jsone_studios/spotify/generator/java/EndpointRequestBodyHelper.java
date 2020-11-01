package de.jsone_studios.spotify.generator.java;

import com.google.common.base.CaseFormat;
import de.jsone_studios.spotify.generator.java.util.JavaUtils;
import de.jsone_studios.spotify.parser.model.SpotifyApiDocumentation;
import de.jsone_studios.spotify.parser.model.SpotifyApiEndpoint;
import de.jsone_studios.spotify.parser.model.SpotifyObject;

import java.util.List;
import java.util.stream.Collectors;

import static de.jsone_studios.spotify.parser.model.SpotifyApiEndpoint.ParameterLocation.BODY;

class EndpointRequestBodyHelper {
    public static List<SpotifyObject> getEndpointRequestBodies(SpotifyApiDocumentation documentation) {
        return documentation.getCategoryList().stream()
                .flatMap(c -> c.getEndpointList().stream())
                .filter(e -> e.getParameters().stream().anyMatch(p -> p.getLocation() == BODY))
                .map(EndpointRequestBodyHelper::getEndpointRequestBody)
                .collect(Collectors.toList());
    }

    private static SpotifyObject getEndpointRequestBody(SpotifyApiEndpoint endpoint) {
        var properties = endpoint.getParameters().stream()
                .filter(p -> p.getLocation() == BODY)
                .sorted((p1, p2) -> Boolean.compare(p2.isRequired(), p1.isRequired()))
                .map(p -> new SpotifyObject.Property(p.getName(), p.getType(), p.getDescription(), p.isRequired()))
                .collect(Collectors.toList());

        return new SpotifyObject(getEndpointRequestBodyName(endpoint), properties);
    }

    public static String getEndpointRequestBodyName(SpotifyApiEndpoint endpoint) {
        return CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, JavaUtils.shrinkEndpointId(endpoint)) + "Request";
    }
}
