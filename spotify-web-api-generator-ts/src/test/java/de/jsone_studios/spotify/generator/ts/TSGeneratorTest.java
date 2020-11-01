package de.jsone_studios.spotify.generator.ts;

import java.nio.file.Path;

public class TSGeneratorTest {
    private static final Path INPUT_FILE_PATH = Path.of("spotify-web-api-parser/api-documentation.yaml");
    private static final Path OUTPUT_PATH = Path.of("spotify-web-api-generator-ts", "models.d.ts");

    public static void main(String[] args) {
        var cli = new CLI();
        cli.apiDocumentationFile = INPUT_FILE_PATH;
        cli.outputFile = OUTPUT_PATH;
        cli.run();
    }
}
