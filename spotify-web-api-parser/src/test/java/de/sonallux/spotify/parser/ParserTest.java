package de.sonallux.spotify.parser;

import java.nio.file.Path;

public class ParserTest {
    private static final Path OUTPUT_FILE = Path.of("spotify-web-api-core/src/main/resources/spotify-web-api.yml");
    private static final Path RESPONSE_TYPES_FILE = Path.of("spotify-web-api-parser/response-types.yml");

    public static void main(String[] args) {
        var cli = new CLI();
        cli.outputFile = OUTPUT_FILE;
        cli.responseTypesFile = RESPONSE_TYPES_FILE;
        cli.isInteractive = true;
        cli.run();
    }
}
