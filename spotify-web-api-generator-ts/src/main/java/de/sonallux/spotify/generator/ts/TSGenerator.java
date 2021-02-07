package de.sonallux.spotify.generator.ts;

import com.samskivert.mustache.Mustache;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.core.model.SpotifyWebApiObject;

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
        var fileName = String.format("/templates/%s.mustache", name);
        return new InputStreamReader(TSGenerator.class.getResourceAsStream(fileName));
    }

    public void generate(SpotifyWebApi apiDocumentation, Path outputFile) throws IOException {
        // var mainSpotifyObjects = apiDocumentation.getObjects().stream()
        //         .filter(TSGenerator::isMainSpotifyObject)
        //         .collect(Collectors.toList());
        // mainSpotifyObjects.forEach(System.out::println);

        var objectTemplate = new ObjectTemplate().loadTemplate(templateCompiler);
        objectTemplate.generate(apiDocumentation.getObjectList(), outputFile);
    }

    private static boolean isMainSpotifyObject(SpotifyWebApiObject spotifyWebApiObject) {
        return spotifyWebApiObject.getProperties().stream().anyMatch(property -> "id".equals(property.getName())) &&
                spotifyWebApiObject.getProperties().stream().anyMatch(property -> "type".equals(property.getName())) &&
                spotifyWebApiObject.getProperties().stream().anyMatch(property -> "href".equals(property.getName())) &&
                spotifyWebApiObject.getProperties().stream().anyMatch(property -> "uri".equals(property.getName()));
    }
}
