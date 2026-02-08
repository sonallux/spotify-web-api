package de.sonallux.spotify.validator;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springweb.client.OpenApiValidationClientHttpRequestInterceptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArtistsApiValidationTest {
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
    void validateGetArtist() {
        var response = restClient.get().uri("/artists/{id}", "4lDiJcOJ2GLCK6p9q5BgfK").retrieve().toBodilessEntity();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void validateGetSeveralArtist() {
        var response = restClient.get().uri("/artists?ids={ids}", "0Dvx6p8JDyzeOPGmaCIH1L,5Y5TRrQiqgUO4S36tzjIRZ").retrieve().toBodilessEntity();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void validateGetArtistsAlbums() {
        var artistId = "6XyY86QOPPrYVGvF9ch6wz";

        var responseFirstPage = restClient.get().uri("/artists/{id}/albums?limit=10", artistId).retrieve().toBodilessEntity();
        assertEquals(HttpStatus.OK, responseFirstPage.getStatusCode());
    }

    /*
     * disabled because of errors
     * - [Path '/tracks/0/album'] Object instance has properties which are not allowed by the schema: [\"is_playable\"]
     * - [Path '/tracks/0/album'] Object has missing required properties ([\"available_markets\"])
     */
    @Disabled
    @Test
    void validateGetArtistsTopTracks() {
        var response = restClient.get().uri("/artists/{id}/top-tracks?market=DE", "0Dvx6p8JDyzeOPGmaCIH1L").retrieve().toBodilessEntity();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Disabled("The used spotify application is not longer allowed to call this endpoint.")
    void validateGetArtistsRelatedArtists() {
        var response = restClient.get().uri("/artists/{id}/related-artists", "0Dvx6p8JDyzeOPGmaCIH1L").retrieve().toBodilessEntity();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
