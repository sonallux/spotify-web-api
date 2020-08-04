import de.jsone_studios.spotify.generator.java.JavaGenerator;
import de.jsone_studios.spotify.parser.Yaml;
import de.jsone_studios.spotify.parser.model.SpotifyApiDocumentation;

import java.nio.file.Files;
import java.nio.file.Path;

public class JavaGeneratorTest {
    private static final Path FILE_PATh = Path.of("spotify-web-api-parser/api-documentation.yaml");

    public static void main(String[] args) throws Exception {
        try (var inputStream = Files.newInputStream(FILE_PATh)) {
            var apiDocumentation = Yaml.create().readValue(inputStream, SpotifyApiDocumentation.class);
            var javaGenerator = new JavaGenerator();
            javaGenerator.generate(apiDocumentation);
        }
    }
}
