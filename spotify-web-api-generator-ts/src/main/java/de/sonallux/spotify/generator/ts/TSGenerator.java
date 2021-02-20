package de.sonallux.spotify.generator.ts;

import com.github.mustachejava.MustacheFactory;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.core.model.SpotifyWebApiObject;

import java.io.IOException;
import java.nio.file.Path;

public class TSGenerator {
    private final MustacheFactory mustacheFactory;

    public TSGenerator() {
        this.mustacheFactory = new NoEscapingMustacheFactory();
    }

    public void generate(SpotifyWebApi apiDocumentation, Path outputFile) throws IOException {
        // var mainSpotifyObjects = apiDocumentation.getObjects().stream()
        //         .filter(TSGenerator::isMainSpotifyObject)
        //         .collect(Collectors.toList());
        // mainSpotifyObjects.forEach(System.out::println);

        var objectTemplate = new ObjectTemplate().loadTemplate(mustacheFactory);
        objectTemplate.generate(apiDocumentation.getObjectList(), outputFile);
    }

    private static boolean isMainSpotifyObject(SpotifyWebApiObject spotifyWebApiObject) {
        return spotifyWebApiObject.getProperties().stream().anyMatch(property -> "id".equals(property.getName())) &&
                spotifyWebApiObject.getProperties().stream().anyMatch(property -> "type".equals(property.getName())) &&
                spotifyWebApiObject.getProperties().stream().anyMatch(property -> "href".equals(property.getName())) &&
                spotifyWebApiObject.getProperties().stream().anyMatch(property -> "uri".equals(property.getName()));
    }
}
