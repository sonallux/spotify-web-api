package de.sonallux.spotify.generator.java;

import com.google.common.base.CaseFormat;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;
import de.sonallux.spotify.core.model.SpotifyWebApiObject;
import de.sonallux.spotify.generator.java.util.JavaUtils;

import java.util.List;
import java.util.stream.Collectors;

import static de.sonallux.spotify.core.model.SpotifyWebApiEndpoint.ParameterLocation.*;

public class EndpointRequestBodyHelper {
    public static List<EndpointRequestBodyObject> getEndpointRequestBodies(SpotifyWebApi documentation) {
        return documentation.getCategoryList().stream()
                .flatMap(c -> c.getEndpointList().stream())
                .filter(e -> e.getParameters().stream().anyMatch(p -> p.getLocation() == BODY))
                .map(EndpointRequestBodyHelper::getEndpointRequestBody)
                .collect(Collectors.toList());
    }

    private static EndpointRequestBodyObject getEndpointRequestBody(SpotifyWebApiEndpoint endpoint) {
        var properties = endpoint.getParameters().stream()
                .filter(p -> p.getLocation() == BODY)
                .sorted((p1, p2) -> Boolean.compare(p2.isRequired(), p1.isRequired()))
                .map(p -> new EndpointRequestBodyObject.Property(p.getName(), p.getType(), p.getDescription(), p.isRequired()))
                .collect(Collectors.toList());

        return new EndpointRequestBodyObject(getEndpointRequestBodyName(endpoint), endpoint.getName(), endpoint.getLink(), properties);
    }

    public static String getEndpointRequestBodyName(SpotifyWebApiEndpoint endpoint) {
        return CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, JavaUtils.shrinkEndpointId(endpoint)) + "Request";
    }

    /**
     * Fixes duplicated endpoint parameters.
     * Some endpoints allow to pass data either via query argument or via body. As the url has a length limit,
     * passing to much data in the query string might result in an error response. Therefore this method removes
     * the option to pass the data via query argument and makes the body parameter mandatory.
     * @param endpoint the spotify api endpoint to fix
     */
    public static void fixDuplicateEndpointParameters(SpotifyWebApiEndpoint endpoint) {
        String paramName;
        switch (endpoint.getId()) {
            case "endpoint-remove-albums-user":
            case "endpoint-save-albums-user":
            case "endpoint-follow-artists-users":
            case "endpoint-unfollow-artists-users":
                paramName = "ids";
                break;
            case "endpoint-replace-playlists-tracks":
            case "endpoint-add-tracks-to-playlist":
                paramName = "uris";
                break;
            default:
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
    }
}
