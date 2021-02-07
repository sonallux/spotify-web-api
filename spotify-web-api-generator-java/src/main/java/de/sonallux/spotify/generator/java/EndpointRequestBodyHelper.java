package de.sonallux.spotify.generator.java;

import com.google.common.base.CaseFormat;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;
import de.sonallux.spotify.core.model.SpotifyWebApiObject;
import de.sonallux.spotify.generator.java.util.JavaUtils;

import java.util.List;
import java.util.stream.Collectors;

import static de.sonallux.spotify.core.model.SpotifyWebApiEndpoint.ParameterLocation.BODY;

public class EndpointRequestBodyHelper {
    public static List<SpotifyWebApiObject> getEndpointRequestBodies(SpotifyWebApi documentation) {
        return documentation.getCategoryList().stream()
                .flatMap(c -> c.getEndpointList().stream())
                .filter(e -> e.getParameters().stream().anyMatch(p -> p.getLocation() == BODY))
                .map(EndpointRequestBodyHelper::getEndpointRequestBody)
                .collect(Collectors.toList());
    }

    private static SpotifyWebApiObject getEndpointRequestBody(SpotifyWebApiEndpoint endpoint) {
        var properties = endpoint.getParameters().stream()
                .filter(p -> p.getLocation() == BODY)
                .sorted((p1, p2) -> Boolean.compare(p2.isRequired(), p1.isRequired()))
                .map(p -> new SpotifyWebApiObject.Property(p.getName(), p.getType(), p.getDescription(), p.isRequired()))
                .collect(Collectors.toList());

        return new SpotifyWebApiObject(getEndpointRequestBodyName(endpoint), properties);
    }

    public static String getEndpointRequestBodyName(SpotifyWebApiEndpoint endpoint) {
        return CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, JavaUtils.shrinkEndpointId(endpoint)) + "Request";
    }
}
