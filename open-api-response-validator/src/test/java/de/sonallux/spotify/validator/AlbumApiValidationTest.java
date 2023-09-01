package de.sonallux.spotify.validator;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springweb.client.OpenApiValidationClientHttpRequestInterceptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumApiValidationTest {
    private static RestTemplate restTemplate;

    @BeforeAll
    static void setupRestTemplate() {
        var spotifyAuthInterceptor = new SpotifyClientCredentialsAuthInterceptor();
        var validationInterceptor = new OpenApiValidationClientHttpRequestInterceptor(
            OpenApiInteractionValidator.createForSpecificationUrl("../fixed-spotify-open-api.yml")
                // https://bitbucket.org/atlassian/swagger-request-validator/src/30f00b42a4bcc6bad7a68fe0c7491dd4aa5c3a67/docs/FAQ.md
                .withResolveCombinators(true)
                .build());

        restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(spotifyAuthInterceptor, validationInterceptor));
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("https://api.spotify.com/v1"));
    }

    @Test
    void validateGetAlbum() {
        var response = restTemplate.getForEntity("/albums/{id}", String.class, "3Q9wXhEAX7NYCPP0hxIuDz");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void validateGetSeveralAlbums() {
        var response = restTemplate.getForEntity("/albums?ids={ids}", String.class, "3Q9wXhEAX7NYCPP0hxIuDz,5Eevxp2BCbWq25ZdiXRwYd");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void validateGetAlbumTracks() {
        var response = restTemplate.getForEntity("/albums/{id}/tracks", String.class, "3Q9wXhEAX7NYCPP0hxIuDz");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
