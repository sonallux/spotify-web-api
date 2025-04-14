package de.sonallux.spotify.validator;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springweb.client.OpenApiValidationClientHttpRequestInterceptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumApiValidationTest {
    private static RestClient restClient;

    @BeforeAll
    static void setupRestTemplate() {
        var spotifyAuthInterceptor = new SpotifyClientCredentialsAuthInterceptor();
        var validationInterceptor = new OpenApiValidationClientHttpRequestInterceptor(
            OpenApiInteractionValidator.createForSpecificationUrl("../fixed-spotify-open-api.yml")
                // https://bitbucket.org/atlassian/swagger-request-validator/src/30f00b42a4bcc6bad7a68fe0c7491dd4aa5c3a67/docs/FAQ.md
                .withResolveCombinators(true)
                .build());

        restClient = RestClient.builder()
            .requestInterceptor(spotifyAuthInterceptor)
            .requestInterceptor(validationInterceptor)
            .baseUrl("https://api.spotify.com/v1")
            .build();
    }

    @Test
    void validateGetAlbum() {
        var response = restClient.get().uri("/albums/{id}", "3Q9wXhEAX7NYCPP0hxIuDz").retrieve().toBodilessEntity();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void validateGetSeveralAlbums() {
        var response = restClient.get().uri("/albums?ids={ids}", "3Q9wXhEAX7NYCPP0hxIuDz,5Eevxp2BCbWq25ZdiXRwYd").retrieve().toBodilessEntity();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void validateGetAlbumTracks() {
        var response = restClient.get().uri("/albums/{id}/tracks", "3Q9wXhEAX7NYCPP0hxIuDz").retrieve().toBodilessEntity();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
