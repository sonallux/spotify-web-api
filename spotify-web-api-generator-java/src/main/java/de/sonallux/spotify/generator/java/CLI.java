package de.sonallux.spotify.generator.java;

import de.sonallux.spotify.core.SpotifyWebApiUtils;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class CLI implements Runnable {

    @Option(names = {"-f", "--file"}, required = true, description = "The web API documentation file to a generate a Java wrapper for")
    Path apiDocumentationFile;

    @Option(names = {"-o", "--output"}, required = true, description = "The folder to output the generated files to")
    Path outputFolder;

    @Option(names = {"--clean"}, description = "Deletes all content from the output folder")
    boolean shouldClean = false;

    @Option(names = {"-p", "--package"}, required = true, description = "The Java package name")
    String packageName = null;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    boolean helpRequested = false;

    @Override
    public void run() {
        var apiDocumentation = readSpotifyWebApi();

        try {
            if (hasFiles(outputFolder)) {
                if (shouldClean) {
                    deleteDirectory(outputFolder);
                } else {
                    System.err.println("Warning: output folder is not empty. Existing files will be overridden.");
                }
            }

            var javaPackage = JavaPackage.fromPackage(packageName);

            new JavaGenerator().generate(apiDocumentation, outputFolder, javaPackage);
        } catch (IOException e) {
            System.err.println("Failed to write generated files: " + e.getMessage());
            System.exit(1);
        } catch (GeneratorException e) {
            System.err.println("Failed to generate Java wrapper: " + e.getMessage());
            System.exit(1);
        }
    }

    private static boolean hasFiles(Path dir) throws IOException {
        return Files.exists(dir) && Files.list(dir).findAny().isPresent();
    }

    private static void deleteDirectory(Path dir) throws IOException {
        try (Stream<Path> walk = Files.walk(dir)) {
            walk.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
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
}
