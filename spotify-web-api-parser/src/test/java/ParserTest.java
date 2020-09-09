import de.jsone_studios.spotify.parser.WebApiParser;
import de.jsone_studios.spotify.parser.Yaml;
import de.jsone_studios.spotify.parser.model.SpotifyApiDocumentation;

import java.nio.file.Files;
import java.nio.file.Path;

public class ParserTest {
    private static final Path OUTPUT_FILE = Path.of("spotify-web-api-parser/api-documentation.yaml");
    private static final Path RESPONSE_TYPES_FILE = Path.of("spotify-web-api-parser/response-types.yaml");

    public static void main(String[] args) throws Exception {
        var documentation = new WebApiParser(true).parse(RESPONSE_TYPES_FILE);
        System.out.println(documentation);
        try (var outputStream = Files.newOutputStream(OUTPUT_FILE)) {
            Yaml.create().writeValue(outputStream, documentation);
        }
    }
}
