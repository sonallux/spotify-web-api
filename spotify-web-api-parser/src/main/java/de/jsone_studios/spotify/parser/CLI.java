package de.jsone_studios.spotify.parser;

import de.jsone_studios.spotify.parser.model.SpotifyApiDocumentation;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CLI implements Runnable {

    @Option(names = {"-o", "--output"}, required = true, description = "The file to output the generated api documentation")
    Path outputFile;

    @Option(names = "--response-types", required = true, description = "The response type mappings file")
    Path responseTypesFile;

    @Option(names = {"-i", "--interactive"}, description = "Run parser in interactive mode")
    boolean isInteractive = false;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    boolean helpRequested = false;

    @Override
    public void run() {
        SpotifyApiDocumentation apiDocumentation = null;
        try {
            var webApiParser = new WebApiParser(isInteractive);
            apiDocumentation = webApiParser.parse(responseTypesFile);
        } catch (ApiParseException | IOException e) {
            System.err.println("Failed to parse web api documentation: " + e.getMessage());
            System.exit(1);
        }

        try (var outputStream = Files.newOutputStream(outputFile)) {
            Yaml.create().writeValue(outputStream, apiDocumentation);
        } catch (IOException e) {
            System.err.println("Failed to write output file: " + e.getMessage());
            System.exit(1);
        }
    }


    public static void main(String[] args) {
        var exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }
}
