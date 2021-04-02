package de.sonallux.spotify.generator.openapi;

import com.fasterxml.jackson.databind.ObjectWriter;
import de.sonallux.spotify.core.SpotifyWebApiUtils;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.generator.openapi.validation.OpenApiValidator;
import io.swagger.v3.oas.models.OpenAPI;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import static java.nio.file.StandardOpenOption.*;

@Command(versionProvider = CLI.ManifestVersionProvider.class)
public class CLI implements Runnable {

    private static final ObjectWriter OPEN_API_YAML = io.swagger.v3.core.util.Yaml.pretty();

    @Option(names = {"-f", "--file"}, required = true, description = "The web API documentation file to a generate a Java wrapper for")
    Path apiDocumentationFile;

    @Option(names = {"-o", "--output"}, required = true, description = "The file the OpenApi Specification should be written to")
    Path outputFile;

    @Option(names = {"--validate"}, description = "Validate the generated OpenApi Specification against the schema")
    boolean shouldValidate = false;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Print usage help")
    boolean helpRequested = false;

    @Option(names = { "-v", "--version"}, versionHelp = true, description = "Print version information")
    boolean versionRequested = false;

    @Override
    public void run() {
        var spotifyWebApi = readSpotifyWebApi();

        var openAPI = new OpenApiGenerator().generate(spotifyWebApi);

        if (shouldValidate) {
            validate(openAPI);
        }

        try (var outputStream = Files.newOutputStream(outputFile, CREATE, TRUNCATE_EXISTING, WRITE)) {
            OPEN_API_YAML.writeValue(outputStream, openAPI);
        } catch (IOException e) {
            System.err.println("Failed to write generated file: " + e.getMessage());
            System.exit(1);
        }
    }

    private void validate(OpenAPI openAPI) {
        try {
            String openApiString = OPEN_API_YAML.writeValueAsString(openAPI);

            var hasErrors = false;
            var result = new OpenApiValidator().validateByContent(openApiString);
            for (var msg : result.getMessages()) {
                hasErrors = true;
                System.err.println("Validation result: " + msg);
            }
            for (var msg : result.getSchemaValidationMessages()) {
                hasErrors = true;
                System.err.println("Validation result: " + msg.getMessage() + " at " + msg.getSchema().getPointer());
            }
            if (!hasErrors) {
                System.out.println("No errors on OpenApi Definition found");
                return;
            }
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Failed to execute validation: " + e.getMessage());
            System.exit(1);
        }
    }

    private SpotifyWebApi readSpotifyWebApi() {
        try {
            return SpotifyWebApiUtils.load(apiDocumentationFile);
        } catch (IOException e) {
            System.err.println("Failed to read web API documentation file: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    public static void main(String[] args) {
        var exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }

    static class ManifestVersionProvider implements IVersionProvider {
        private static final String IMPLEMENTATION_TITLE = "spotify-web-api-generator-open-api";

        @Override
        public String[] getVersion() throws Exception {
            var resources = CLI.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                var url = resources.nextElement();
                try {
                    var manifest = new Manifest(url.openStream());
                    var attributes = manifest.getMainAttributes();
                    var title = get(attributes, "Implementation-Title");
                    if (IMPLEMENTATION_TITLE.equals(title)) {
                        var version = get(attributes, "Implementation-Version");
                        return new String[] { IMPLEMENTATION_TITLE, version.toString() };
                    }
                } catch (IOException e) {
                    return new String[] { "Unable to read manifest from " + url + ": " + e };
                }
            }
            return new String[] { IMPLEMENTATION_TITLE, "unknown" };
        }

        private static Object get(Attributes attributes, String key) {
            return attributes.get(new Attributes.Name(key));
        }
    }
}
