package de.sonallux.spotify.generator.java;

import com.samskivert.mustache.Mustache;
import de.sonallux.spotify.core.EndpointSplitter;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.generator.java.templates.*;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;

@Slf4j
public class JavaGenerator {
    private final Mustache.Compiler templateCompiler;

    public JavaGenerator() {
        this.templateCompiler = Mustache.compiler()
                .withLoader(this::loadTemplate)
                .escapeHTML(false);
    }

    public void generate(SpotifyWebApi spotifyWebApi, Path outputFolder, JavaPackage javaPackage) throws IOException, GeneratorException {
        try {
            EndpointSplitter.splitEndpoints(spotifyWebApi);
        } catch (IllegalArgumentException e) {
            throw new GeneratorException("Failed to split endpoints", e);
        }

        var objectTemplate = new ObjectTemplate().loadTemplate(this.templateCompiler);
        for (var object : spotifyWebApi.getObjectList()) {
            objectTemplate.generate(object, outputFolder, javaPackage);
        }

        var apiTemplate = new CategoryTemplate().loadTemplate(this.templateCompiler);
        for (var category : spotifyWebApi.getCategoryList()) {
            apiTemplate.generate(category, outputFolder, javaPackage);
        }

        var requestBodyTemplate = new RequestBodyTemplate().loadTemplate(this.templateCompiler);
        for (var object : EndpointRequestBodyHelper.getEndpointRequestBodies(spotifyWebApi)) {
            requestBodyTemplate.generate(object, outputFolder, javaPackage);
        }

        new SpotifyWebApiTemplate()
                .loadTemplate(this.templateCompiler)
                .generate(spotifyWebApi, outputFolder, javaPackage);

        new ScopeTemplate()
                .loadTemplate(this.templateCompiler)
                .generate(spotifyWebApi.getScopes(), outputFolder, javaPackage);
    }

    private Reader loadTemplate(String name) {
        String fileName = String.format("/templates/%s.mustache", name);
        return new InputStreamReader(JavaGenerator.class.getResourceAsStream(fileName));
    }
}
