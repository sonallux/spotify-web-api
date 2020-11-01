package de.jsone_studios.spotify.generator.java;

import com.samskivert.mustache.Mustache;
import de.jsone_studios.spotify.generator.java.util.JavaPackage;
import de.jsone_studios.spotify.parser.model.SpotifyApiDocumentation;
import de.jsone_studios.spotify.parser.model.SpotifyApiEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JavaGenerator {
    private final Mustache.Compiler templateCompiler;

    public JavaGenerator() {
        this.templateCompiler = Mustache.compiler()
                .withLoader(this::loadTemplate)
                .escapeHTML(false);
    }

    public void generate(SpotifyApiDocumentation apiDocumentation, Path outputFolder, JavaPackage javaPackage) throws IOException, GeneratorException {
        var objectTemplate = new ObjectTemplate().loadTemplate(this.templateCompiler);
        var apiTemplate = new ApiTemplate().loadTemplate(this.templateCompiler);

        adjustApiDocumentation(apiDocumentation);

        for (var object : apiDocumentation.getObjectList()) {
            objectTemplate.generate(object, outputFolder, javaPackage);
        }

        for (var object : EndpointRequestBodyHelper.getEndpointRequestBodies(apiDocumentation)) {
            objectTemplate.generate(object, outputFolder, javaPackage);
        }

        for (var category : apiDocumentation.getCategoryList()) {
            apiTemplate.generate(category, outputFolder, javaPackage);
        }

        //TODO: Generate de.jsone_studios.spotify.api.authentication.Scope
    }

    private Reader loadTemplate(String name) {
        String fileName = String.format("/templates/%s.mustache", name);
        return new InputStreamReader(JavaGenerator.class.getResourceAsStream(fileName));
    }

    private void adjustApiDocumentation(SpotifyApiDocumentation apiDocumentation) throws GeneratorException {
        separateUsersTopArtistsAndTracks(apiDocumentation);
    }

    private void separateUsersTopArtistsAndTracks(SpotifyApiDocumentation apiDocumentation) throws GeneratorException {
        var category = apiDocumentation.getCategory("category-personalization")
                .orElseThrow(() -> new GeneratorException("Can not find category-personalization"));

        var topArtistsAndTracks = category.getEndpoint("endpoint-get-users-top-artists-and-tracks")
                .orElseThrow(() -> new GeneratorException("Can not find endpoint-get-users-top-artists-and-tracks"));

        var parameters = new ArrayList<>(topArtistsAndTracks.getParameters());
        parameters.removeIf(p -> "type".equals(p.getName()));

        var topArtists = new SpotifyApiEndpoint(
                "endpoint-get-users-top-artists",
                "Get a User's Top Artists",
                topArtistsAndTracks.getLink(),
                "Get the current user’s top artists based on calculated affinity.",
                "GET",
                "/me/top/artists",
                parameters,
                "On success, the HTTP status code in the response header is 200 OK and the response body contains a paging object of Artists. On error, the header status code is an error code and the response body contains an error object.",
                topArtistsAndTracks.getScopes(),
                topArtistsAndTracks.getNotes(),
                List.of(new SpotifyApiEndpoint.ResponseType("PagingObject[ArtistObject]", 200, null))
        );
        var topTracks = new SpotifyApiEndpoint(
                "endpoint-get-users-top-tracks",
                "Get a User's Top Tracks",
                topArtistsAndTracks.getLink(),
                "Get the current user’s top tracks based on calculated affinity.",
                "GET",
                "/me/top/tracks",
                parameters,
                "On success, the HTTP status code in the response header is 200 OK and the response body contains a paging object of Tracks. On error, the header status code is an error code and the response body contains an error object.",
                topArtistsAndTracks.getScopes(),
                topArtistsAndTracks.getNotes(),
                List.of(new SpotifyApiEndpoint.ResponseType("PagingObject[TrackObject]", 200, null))
        );

        category.getEndpoints().remove("endpoint-get-users-top-artists-and-tracks");
        category.getEndpoints().put(topArtists.getId(), topArtists);
        category.getEndpoints().put(topTracks.getId(), topTracks);
    }
}
