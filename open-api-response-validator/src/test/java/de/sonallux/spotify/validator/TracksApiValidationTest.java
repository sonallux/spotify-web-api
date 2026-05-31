package de.sonallux.spotify.validator;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springweb.client.OpenApiValidationClientHttpRequestInterceptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TracksApiValidationTest {
    private static RestClient restClient;

    @BeforeAll
    static void setupRestTemplate() {
        var spotifyAuthInterceptor = new SpotifyClientCredentialsAuthInterceptor();
        var validationInterceptor = new OpenApiValidationClientHttpRequestInterceptor(
            OpenApiInteractionValidator.createForSpecificationUrl("../fixed-spotify-open-api.yml")
                // https://github.com/atlassian/openapi-request-validator/blob/master/docs/FAQ.md
                .withResolveCombinators(true)
                .build());

        restClient = RestClient.builder()
            .requestInterceptor(spotifyAuthInterceptor)
            .requestInterceptor(validationInterceptor)
            .baseUrl("https://api.spotify.com/v1")
            .build();
    }

    @Test
    void validateGetTracks() {
        var response = restClient.get().uri("/tracks/{id}", "11dFghVXANMlKmJXsNCbNl").retrieve().toBodilessEntity();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void validateGetSeveralTracks() {
        var response = restClient.get().uri("/tracks?ids={ids}", "11dFghVXANMlKmJXsNCbNl,7ouMYWpwJ422jRcDASZB7P").retrieve().toBodilessEntity();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
