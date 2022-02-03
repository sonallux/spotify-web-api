package de.sonallux.spotify.openapi;

import java.nio.file.Path;

public class OpenApiTest {
    private static final Path OFFICIAL_OPENAPI_FILE = Path.of("official-spotify-open-api.yml");
    private static final Path FIXED_OPENAPI_FILE = Path.of("spotify-web-api-open-api/spotify-web-api-openapi.yml");

    public static void main(String[] args) {
        var cli = new CLI();
        cli.officialOpenApiFile = OFFICIAL_OPENAPI_FILE;
        cli.outputFile = FIXED_OPENAPI_FILE;
        cli.shouldValidate = true;
        cli.run();
    }
}
