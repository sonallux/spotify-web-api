import de.jsone_studios.spotify.generator.java.JavaGenerator;
import de.jsone_studios.spotify.generator.java.util.JavaPackage;
import de.jsone_studios.spotify.parser.Yaml;
import de.jsone_studios.spotify.parser.model.SpotifyApiDocumentation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class JavaGeneratorTest {
    private static final Path FILE_PATh = Path.of("spotify-web-api-parser/api-documentation.yaml");

    public static void main(String[] args) throws Exception {
        try (var inputStream = Files.newInputStream(FILE_PATh)) {
            var apiDocumentation = Yaml.create().readValue(inputStream, SpotifyApiDocumentation.class);
            var outputFolder = Path.of("spotify-web-api-java", "src", "main", "generated");
            deleteDirectory(outputFolder);

            var javaPackage = JavaPackage.fromNames("de", "jsone_studios", "spotify", "api");

            new JavaGenerator().generate(apiDocumentation, outputFolder, javaPackage);
        }
    }

    private static void deleteDirectory(Path dir) throws IOException {
        try (Stream<Path> walk = Files.walk(dir)) {
            walk.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }
}
