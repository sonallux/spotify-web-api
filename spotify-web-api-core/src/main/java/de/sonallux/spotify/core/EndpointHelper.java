package de.sonallux.spotify.core;

import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static de.sonallux.spotify.core.model.SpotifyWebApiEndpoint.ParameterLocation.*;

public class EndpointHelper {

    public static void splitEndpoints(SpotifyWebApi spotifyWebApi) throws IllegalArgumentException {
        EndpointSplitter.splitEndpoints(spotifyWebApi);
    }

    /**
     * Fixes duplicated endpoint parameters.
     * Some endpoints allow passing data either via query argument or via body. As the url has a length limit,
     * passing too much data in the query string might result in an error response. Therefore, this method removes
     * the option to pass the data via query argument and makes the body parameter mandatory.
     * @param spotifyWebApi the spotify web api documentation
     */
    public static void fixDuplicateEndpointParameters(SpotifyWebApi spotifyWebApi) {
        spotifyWebApi.getCategoryList().stream()
            .flatMap(c -> c.getEndpointList().stream())
            .forEach(EndpointHelper::fixDuplicateEndpointParameters);
    }

    /**
     * Fixes duplicated endpoint parameters.
     * Some endpoints allow passing data either via query argument or via body. As the url has a length limit,
     * passing too much data in the query string might result in an error response. Therefore, this method removes
     * the option to pass the data via query argument and makes the body parameter mandatory.
     * @param endpoint the spotify api endpoint to fix
     */
    public static void fixDuplicateEndpointParameters(SpotifyWebApiEndpoint endpoint) {
        if (endpoint.getRequestBody() == null || !(endpoint.getRequestBody() instanceof SpotifyWebApiEndpoint.JsonRequestBody)) {
            return;
        }
        var requestBody = ((SpotifyWebApiEndpoint.JsonRequestBody) endpoint.getRequestBody());

        var iterator = endpoint.getParameters().iterator();
        while (iterator.hasNext()) {
            var parameter = iterator.next();
            var bodyParameter = getBodyParameter(requestBody, parameter.getName());
            if (bodyParameter.isEmpty()) {
                continue;
            }

            //Remove the query parameter
            iterator.remove();

            // Parameter position in endpoint-add-tracks-to-playlist is optional
            if ("endpoint-add-tracks-to-playlist".equals(endpoint.getId()) && "position".equals(parameter.getName())) {
                continue;
            }

            // Mark body parameter and Content-Type header as required
            bodyParameter.get().setRequired(true);
            endpoint.getParameters().stream()
                .filter(p -> p.getLocation() == HEADER && "Content-Type".equals(p.getName()))
                .findFirst().ifPresent(p -> p.setRequired(true));

        }
    }

    private static Optional<SpotifyWebApiEndpoint.Parameter> getBodyParameter(SpotifyWebApiEndpoint.JsonRequestBody requestBody, String name) {
        return requestBody.getParameters().stream().filter(p -> p.getName().equals(name)).findFirst();
    }
}
