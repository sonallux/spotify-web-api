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

class ArtistsApiValidationTest {
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
    void validateGetArtist() {
        var response = restTemplate.getForEntity("/artists/{id}", String.class, "4lDiJcOJ2GLCK6p9q5BgfK");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void validateGetSeveralArtist() {
        var response = restTemplate.getForEntity("/artists?ids={ids}", String.class, "0Dvx6p8JDyzeOPGmaCIH1L,5Y5TRrQiqgUO4S36tzjIRZ");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void validateGetArtistsAlbums() {
        var artistId = "6XyY86QOPPrYVGvF9ch6wz";

        var responseFirstPage = restTemplate.getForEntity("/artists/{id}/albums?limit=5", String.class, artistId);
        assertEquals(responseFirstPage.getStatusCode(), HttpStatus.OK);

        var responseMiddlePage = restTemplate.getForEntity("/artists/{id}/albums?limit=5&offset=50", String.class, artistId);
        assertEquals(responseMiddlePage.getStatusCode(), HttpStatus.OK);

        var responseLastPage = restTemplate.getForEntity("/artists/{id}/albums?limit=20&offset=360", String.class, artistId);
        assertEquals(responseLastPage.getStatusCode(), HttpStatus.OK);

        var responseEmptyPage = restTemplate.getForEntity("/artists/{id}/albums?limit=20&offset=380", String.class, artistId);
        assertEquals(responseEmptyPage.getStatusCode(), HttpStatus.OK);
    }

    /*
     * disabled because of errors
     * - [Path '/tracks/0/album'] Object instance has properties which are not allowed by the schema: [\"is_playable\"]
     * - [Path '/tracks/0/album'] Object has missing required properties ([\"available_markets\"])
     */
    @Disabled
    @Test
    void validateGetArtistsTopTracks() {
        var response = restTemplate.getForEntity("/artists/{id}/top-tracks?market=DE", String.class, "0Dvx6p8JDyzeOPGmaCIH1L");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void validateGetArtistsRelatedArtists() {
        var response = restTemplate.getForEntity("/artists/{id}/related-artists", String.class, "0Dvx6p8JDyzeOPGmaCIH1L");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
