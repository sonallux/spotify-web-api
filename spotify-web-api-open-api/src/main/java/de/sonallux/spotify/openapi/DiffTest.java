package de.sonallux.spotify.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;
import io.swagger.v3.core.util.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DiffTest {
    private static final ObjectMapper OPEN_API_YAML = Yaml.mapper();

    private static final Path OFFICIAL_OPENAPI_FILE = Path.of("official-spotify-open-api.yml");
    private static final Path OFFICIAL_OPENAPI_FIXED_FILE = Path.of("spotify-web-api-open-api/spotify-web-api-openapi.yml");

    public static void main(String[] args) throws Exception {
        var officialOpenApi = OPEN_API_YAML.readTree(Files.readString(OFFICIAL_OPENAPI_FILE));

        var openApiPatches = new OpenApiPatches();
        var fixedOfficialOpenApi = openApiPatches.applyPatches(officialOpenApi);

        validateOpenAPI(fixedOfficialOpenApi);

        var openApiAsString = OPEN_API_YAML.writeValueAsString(fixedOfficialOpenApi);
        Files.writeString(OFFICIAL_OPENAPI_FIXED_FILE, openApiAsString);
    }

    private static void validateOpenAPI(JsonNode node) throws IOException {
        var openApiJsonSchema = loadOpenApiSchema();

        var schemaFactory = JsonSchemaFactory.getInstance(SpecVersionDetector.detect(openApiJsonSchema));
        var schema = schemaFactory.getSchema(openApiJsonSchema);

        var validationMessages = schema.validate(node);
        for (var msg : validationMessages) {
            System.err.println(msg);
        }

    }

    private static JsonNode loadOpenApiSchema() throws IOException {
        var mapper = new ObjectMapper();
        return mapper.readTree(DiffTest.class.getResourceAsStream("/open-api-spec-schema.json"));
    }
}
