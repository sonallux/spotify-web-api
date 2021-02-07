package de.sonallux.spotify.generator.java;

import com.samskivert.mustache.Mustache;
import de.sonallux.spotify.core.EndpointSplitter;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.generator.java.templates.CategoryTemplate;
import de.sonallux.spotify.generator.java.templates.ObjectTemplate;
import de.sonallux.spotify.generator.java.templates.ScopeTemplate;
import de.sonallux.spotify.generator.java.templates.SpotifyWebApiTemplate;
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

    public void generate(SpotifyWebApi apiDocumentation, Path outputFolder, JavaPackage javaPackage) throws IOException, GeneratorException {
        var objectTemplate = new ObjectTemplate().loadTemplate(this.templateCompiler);
        var apiTemplate = new CategoryTemplate().loadTemplate(this.templateCompiler);

        try {
            EndpointSplitter.splitEndpoints(apiDocumentation);
        } catch (IllegalArgumentException e) {
            throw new GeneratorException("Failed to split endpoints", e);
        }

        for (var object : apiDocumentation.getObjectList()) {
            objectTemplate.generate(object, outputFolder, javaPackage);
        }

        for (var object : EndpointRequestBodyHelper.getEndpointRequestBodies(apiDocumentation)) {
            objectTemplate.generate(object, outputFolder, javaPackage);
        }

        for (var category : apiDocumentation.getCategoryList()) {
            apiTemplate.generate(category, outputFolder, javaPackage);
        }

        new SpotifyWebApiTemplate()
                .loadTemplate(this.templateCompiler)
                .generate(apiDocumentation, outputFolder, javaPackage);

        new ScopeTemplate()
                .loadTemplate(this.templateCompiler)
                .generate(apiDocumentation.getScopes(), outputFolder, javaPackage);
    }

    private Reader loadTemplate(String name) {
        String fileName = String.format("/templates/%s.mustache", name);
        return new InputStreamReader(JavaGenerator.class.getResourceAsStream(fileName));
    }
}
