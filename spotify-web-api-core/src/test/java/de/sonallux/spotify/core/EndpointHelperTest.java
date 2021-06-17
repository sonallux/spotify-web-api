package de.sonallux.spotify.core;

import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EndpointHelperTest {

    SpotifyWebApi spotifyWebApi;

    @BeforeEach
    void setup() throws IOException {
        spotifyWebApi = SpotifyWebApiUtils.load();
    }

    @Test
    void testFixDuplicateEndpointParameters() {
        assertTrue(spotifyWebApi.getCategoryList().stream().flatMap(c -> c.getEndpointList().stream())
            .anyMatch(this::hasDuplicateEndpointParameters));

        EndpointHelper.fixDuplicateEndpointParameters(spotifyWebApi);

        assertTrue(spotifyWebApi.getCategoryList().stream().flatMap(c -> c.getEndpointList().stream())
            .noneMatch(this::hasDuplicateEndpointParameters));
    }

    private boolean hasDuplicateEndpointParameters(SpotifyWebApiEndpoint endpoint) {
        return endpoint.getParameters().stream()
            .collect(Collectors.groupingBy(SpotifyWebApiEndpoint.Parameter::getName))
            .entrySet().stream()
            .anyMatch(entry -> entry.getValue().size() > 1);
    }

    @Test
    void testSplitEndpoints() {
        var endpointCount = spotifyWebApi.getCategoryList().stream().mapToLong(c -> c.getEndpointList().size()).sum();

        EndpointHelper.splitEndpoints(spotifyWebApi);

        var endpointCountAfterSplit = spotifyWebApi.getCategoryList().stream().mapToLong(c -> c.getEndpointList().size()).sum();

        assertEquals(endpointCount + 2, endpointCountAfterSplit);
    }
}
