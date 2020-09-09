package de.jsone_studios.spotify.generator.openapi;

import de.jsone_studios.spotify.generator.openapi.validation.OpenApiValidator;
import de.jsone_studios.spotify.parser.Yaml;
import de.jsone_studios.spotify.parser.model.SpotifyApiDocumentation;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

public class CLI implements Runnable {

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

        if (shouldValidate) {
            validate(apiDocumentation);
        }

        try (var outputStream = Files.newOutputStream(outputFile, CREATE, TRUNCATE_EXISTING, WRITE)) {
            Yaml.create().writeValue(outputStream, apiDocumentation);
        } catch (IOException e) {
            System.err.println("Failed to write generated file: " + e.getMessage());
            System.exit(1);
        }
    }

    private void validate(SpotifyApiDocumentation apiDocumentation) {
        try {
            String spotifyApiString = Yaml.create().writeValueAsString(apiDocumentation);

            var hasErrors = false;
            var result = new OpenApiValidator().validateByContent(spotifyApiString);
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
