import de.jsone_studios.spotify.generator.openapi.OpenApiGenerator;
import de.jsone_studios.spotify.generator.openapi.validation.OpenApiValidator;
import de.jsone_studios.spotify.parser.Yaml;
import de.jsone_studios.spotify.parser.model.SpotifyApiDocumentation;

import java.nio.file.Files;
import java.nio.file.Path;

public class OpenApiGeneratorTest {
    private static final Path INPUT_FILE_PATH = Path.of("spotify-web-api-parser/api-documentation.yaml");
    private static final String SWAGGER_FILE = "spotify-web-api-generator-open-api/spotify-open-api.yaml";

    public static void main(String[] args) throws Exception {
        var inputStream = Files.newInputStream(INPUT_FILE_PATH);
        var apiDocumentation = Yaml.create().readValue(inputStream, SpotifyApiDocumentation.class);

        var openApiGenerator = new OpenApiGenerator();
        var openApi = openApiGenerator.generate(apiDocumentation);
        String spotifyApiString = Yaml.create().writeValueAsString(openApi);

        var hasErrors = false;
        var result = new OpenApiValidator().validateByContent(spotifyApiString);
        for (var msg : result.getMessages()) {
            hasErrors = true;
            System.out.println("Validation result: " + msg);
        }
        for (var msg : result.getSchemaValidationMessages()) {
            hasErrors = true;
            System.out.println("Validation result: " + msg.getMessage() + " at " + msg.getSchema().getPointer());
        }
        if (!hasErrors) {
            System.out.println("No errors on OpenApi Definition found");
            try (var outputStream = Files.newOutputStream(Path.of(SWAGGER_FILE))) {
                Yaml.create().writeValue(outputStream, openApi);
            }
        }
    }
}
