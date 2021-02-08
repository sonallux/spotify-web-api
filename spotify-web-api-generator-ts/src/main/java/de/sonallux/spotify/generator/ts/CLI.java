package de.sonallux.spotify.generator.ts;

import de.sonallux.spotify.core.SpotifyWebApiUtils;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Path;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Command(versionProvider = CLI.ManifestVersionProvider.class)
public class CLI implements Runnable {

    @Option(names = {"-f", "--file"}, required = true, description = "The web API documentation file to a generate a Java wrapper for")
    Path apiDocumentationFile;

    @Option(names = {"-o", "--output"}, required = true, description = "The file the TypeScript model definitions should be written to")
    Path outputFile;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Print usage help")
    boolean helpRequested = false;

    @Option(names = { "-v", "--version"}, versionHelp = true, description = "Print version information")
    boolean versionRequested = false;

    @Override
    public void run() {
        var spotifyWebApi = readSpotifyWebApi();

        try {
            new TSGenerator().generate(spotifyWebApi, outputFile);
        } catch (IOException e) {
            System.err.println("Failed to write generated files: " + e.getMessage());
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
        private static final String IMPLEMENTATION_TITLE = "spotify-web-api-generator-ts";

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
