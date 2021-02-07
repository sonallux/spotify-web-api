package de.sonallux.spotify.generator.openapi;

import java.nio.file.Path;

public class OpenApiGeneratorTest {
    private static final Path INPUT_FILE_PATH = Path.of("spotify-web-api-core/src/main/resources/spotify-web-api.yml");
    private static final Path SWAGGER_FILE = Path.of("spotify-web-api-generator-open-api/spotify-open-api-spec.yml");

    public static void main(String[] args) {
        var cli = new CLI();
        cli.apiDocumentationFile = INPUT_FILE_PATH;
        cli.outputFile = SWAGGER_FILE;
        cli.shouldValidate = true;
        cli.run();
    }
}
