package de.sonallux.spotify.generator.java;

import com.github.mustachejava.MustacheFactory;
import de.sonallux.spotify.core.EndpointSplitter;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.generator.java.templates.*;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;

@Slf4j
public class JavaGenerator {
    private final MustacheFactory mustacheFactory;

    public JavaGenerator() {
        this.mustacheFactory = new NoEscapingMustacheFactory();
    }

    public void generate(SpotifyWebApi spotifyWebApi, Path outputFolder, JavaPackage javaPackage) throws IOException, GeneratorException {
        try {
            EndpointSplitter.splitEndpoints(spotifyWebApi);
        } catch (IllegalArgumentException e) {
            throw new GeneratorException("Failed to split endpoints", e);
        }

        var objectTemplate = new ObjectTemplate().loadTemplate(this.mustacheFactory);
        for (var object : spotifyWebApi.getObjectList()) {
            objectTemplate.generate(object, outputFolder, javaPackage);
        }

        var apiTemplate = new CategoryTemplate().loadTemplate(this.mustacheFactory);
        for (var category : spotifyWebApi.getCategoryList()) {
            apiTemplate.generate(category, outputFolder, javaPackage);
        }

        var requestBodyTemplate = new RequestBodyTemplate().loadTemplate(this.mustacheFactory);
        for (var object : EndpointRequestBodyHelper.getEndpointRequestBodies(spotifyWebApi)) {
            requestBodyTemplate.generate(object, outputFolder, javaPackage);
        }

        new SpotifyWebApiTemplate()
                .loadTemplate(this.mustacheFactory)
                .generate(spotifyWebApi, outputFolder, javaPackage);

        new ScopeTemplate()
                .loadTemplate(this.mustacheFactory)
                .generate(spotifyWebApi.getScopes(), outputFolder, javaPackage);
    }
}
