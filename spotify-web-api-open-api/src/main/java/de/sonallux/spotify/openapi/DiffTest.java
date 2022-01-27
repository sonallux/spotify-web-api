package de.sonallux.spotify.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;

public class DiffTest {
    private static final ObjectMapper OPEN_API_YAML = io.swagger.v3.core.util.Yaml.mapper();

    private static final Path OFFICIAL_OPENAPI_FILE = Path.of("official-spotify-open-api.yml");
    private static final Path OFFICIAL_OPENAPI_FIXED_FILE = Path.of("spotify-web-api-open-api/spotify-web-api-openapi.yml");

    public static void main(String[] args) throws Exception {
        var officialOpenApi = OPEN_API_YAML.readTree(Files.readString(OFFICIAL_OPENAPI_FILE));

        var openApiPatches = new OpenApiPatches();
        var fixedOfficialOpenApi = openApiPatches.applyPatches(officialOpenApi);


        var openApiAsString = OPEN_API_YAML.writeValueAsString(fixedOfficialOpenApi);
        Files.writeString(OFFICIAL_OPENAPI_FIXED_FILE, openApiAsString);
    }
}
