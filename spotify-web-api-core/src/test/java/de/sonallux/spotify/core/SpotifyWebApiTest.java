package de.sonallux.spotify.core;

import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static de.sonallux.spotify.core.model.SpotifyWebApiEndpoint.ParameterLocation.BODY;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpotifyWebApiTest {
    static Stream<SpotifyWebApiEndpoint> allEndpoints() throws IOException {
        return SpotifyWebApiUtils.load()
            .getCategoryList().stream()
            .flatMap(c -> c.getEndpointList().stream());
    }

    @ParameterizedTest
    @MethodSource("allEndpoints")
    void testEndpointParametersContainsNoBodyParameters(SpotifyWebApiEndpoint endpoint) {
        assertTrue(endpoint.getParameters().stream().noneMatch(p -> p.getLocation() == BODY),
            () -> "Parameters of " + endpoint.getId() + " contain a body parameter");
    }

    @ParameterizedTest
    @MethodSource("allEndpoints")
    void testEndpointRequestBodyContainsOnlyBodyParameters(SpotifyWebApiEndpoint endpoint) {
        if (endpoint.getRequestBody() != null && endpoint.getRequestBody() instanceof SpotifyWebApiEndpoint.JsonRequestBody) {
            var requestBody = ((SpotifyWebApiEndpoint.JsonRequestBody) endpoint.getRequestBody());
            assertTrue(requestBody.getParameters().stream().allMatch(p -> p.getLocation() == BODY));
        }
    }
}
