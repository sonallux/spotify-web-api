package de.sonallux.spotify.generator.ts;

import com.samskivert.mustache.Mustache;
import de.sonallux.spotify.core.model.SpotifyApiDocumentation;
import de.sonallux.spotify.core.model.SpotifyObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;

public class TSGenerator {
    private final Mustache.Compiler templateCompiler;

    public TSGenerator() {
        this.templateCompiler = Mustache.compiler()
                .withLoader(this::loadTemplate)
                .escapeHTML(false);
    }

    private Reader loadTemplate(String name) {
        String fileName = String.format("/templates/%s.mustache", name);
        return new InputStreamReader(TSGenerator.class.getResourceAsStream(fileName));
    }

    public void generate(SpotifyApiDocumentation apiDocumentation, Path outputFile) throws IOException {
        //var mainSpotifyObjects = apiDocumentation.getObjects().stream()
        //        .filter(TSGenerator::isMainSpotifyObject)
        //        .collect(Collectors.toList());
        //mainSpotifyObjects.forEach(System.out::println);

        var objectTemplate = new ObjectTemplate().loadTemplate(templateCompiler);
        objectTemplate.generate(apiDocumentation.getObjectList(), outputFile);
    }

    private static boolean isMainSpotifyObject(SpotifyObject spotifyObject) {
        return spotifyObject.getProperties().stream().anyMatch(property -> "id".equals(property.getName())) &&
                spotifyObject.getProperties().stream().anyMatch(property -> "type".equals(property.getName())) &&
                spotifyObject.getProperties().stream().anyMatch(property -> "href".equals(property.getName())) &&
                spotifyObject.getProperties().stream().anyMatch(property -> "uri".equals(property.getName()));
    }
}
