import de.jsone_studios.spotify.parser.WebApiParser;
import de.jsone_studios.spotify.parser.Yaml;
import de.jsone_studios.spotify.parser.model.SpotifyApiDocumentation;

import java.nio.file.Files;
import java.nio.file.Path;

public class ParserTest {
    private static final Path FILE_PATh = Path.of("spotify-web-api-parser/api-documentation.yaml");

    public static void main(String[] args) throws Exception {
        var documentation = new WebApiParser().parse();
        System.out.println(documentation);
        try (var outputStream = Files.newOutputStream(FILE_PATh)) {
            Yaml.create().writeValue(outputStream, documentation);
        }

        try (var inputStream = Files.newInputStream(FILE_PATh)) {
            var readDocumentation = Yaml.create().readValue(inputStream, SpotifyApiDocumentation.class);
            System.out.println(readDocumentation);
        }

    }
}
