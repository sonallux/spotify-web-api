package de.sonallux.spotify.generator.java;

import de.sonallux.spotify.core.SpotifyWebApiUtils;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.stream.Stream;

@Command(versionProvider = CLI.ManifestVersionProvider.class)
public class CLI implements Runnable {

    @Option(names = {"-f", "--file"}, required = true, description = "The web API documentation file to a generate a Java wrapper for")
    Path apiDocumentationFile;

    @Option(names = {"-o", "--output"}, required = true, description = "The folder to output the generated files to")
    Path outputFolder;

    @Option(names = {"--clean"}, description = "Deletes all content from the output folder")
    boolean shouldClean = false;

    @Option(names = {"-p", "--package"}, required = true, description = "The Java package name")
    String packageName = null;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Print usage help")
    boolean helpRequested = false;

    @Option(names = { "-v", "--version"}, versionHelp = true, description = "Print version information")
    boolean versionRequested = false;

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

    static class ManifestVersionProvider implements IVersionProvider {
        private static final String IMPLEMENTATION_TITLE = "spotify-web-api-generator-java";

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
                        return new String[] { title + " version \"" + version + "\"" };
                    }
                } catch (IOException e) {
                    return new String[] { "Unable to read manifest from " + url + ": " + e };
                }
            }
            return new String[] { IMPLEMENTATION_TITLE + " version \"unknown\"" };
        }

        private static Object get(Attributes attributes, String key) {
            return attributes.get(new Attributes.Name(key));
        }
    }
}
