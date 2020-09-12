package de.jsone_studios.spotify.parser;

import java.nio.file.Path;

public class ParserTest {
    private static final Path OUTPUT_FILE = Path.of("spotify-web-api-parser/api-documentation.yaml");
    private static final Path RESPONSE_TYPES_FILE = Path.of("spotify-web-api-parser/response-types.yaml");

    public static void main(String[] args) {
        var cli = new CLI();
        cli.outputFile = OUTPUT_FILE;
        cli.responseTypesFile = RESPONSE_TYPES_FILE;
        cli.isInteractive = true;
        cli.run();
    }
}
