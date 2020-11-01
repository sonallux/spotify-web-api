package de.jsone_studios.spotify.generator.openapi;

import com.fasterxml.jackson.databind.ObjectWriter;
import de.jsone_studios.spotify.generator.openapi.validation.OpenApiValidator;
import de.jsone_studios.spotify.core.Yaml;
import de.jsone_studios.spotify.core.model.SpotifyApiDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

public class CLI implements Runnable {

    private static final ObjectWriter OPEN_API_YAML = io.swagger.v3.core.util.Yaml.pretty();

    @Option(names = {"-f", "--file"}, required = true, description = "The web API documentation file to a generate a Java wrapper for")
    Path apiDocumentationFile;

    @Option(names = {"-o", "--output"}, required = true, description = "The file the OpenApi Specification should be written to")
    Path outputFile;

    @Option(names = {"--validate"}, description = "Validate the generated OpenApi Specification against the schema")
    boolean shouldValidate = false;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    boolean helpRequested = false;

    @Override
    public void run() {
        var apiDocumentation = readApiDocumentation();

        var openApiGenerator = new OpenApiGenerator();
        var openAPI = openApiGenerator.generate(apiDocumentation);

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

    private SpotifyApiDocumentation readApiDocumentation() {
        SpotifyApiDocumentation apiDocumentation = null;
        try (var inputStream = Files.newInputStream(apiDocumentationFile)) {
            apiDocumentation = Yaml.create().readValue(inputStream, SpotifyApiDocumentation.class);
        } catch (IOException e) {
            System.err.println("Failed to read web API documentation file: " + e.getMessage());
            System.exit(1);
        }
        return apiDocumentation;
    }

    public static void main(String[] args) {
        var exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }
}
