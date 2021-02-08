package de.sonallux.spotify.parser;

import de.sonallux.spotify.core.Yaml;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Command(versionProvider = CLI.ManifestVersionProvider.class)
public class CLI implements Runnable {

    @Option(names = {"-o", "--output"}, required = true, description = "The file to output the generated api documentation")
    Path outputFile;

    @Option(names = "--response-types", required = true, description = "The response type mappings file")
    Path responseTypesFile;

    @Option(names = {"-i", "--interactive"}, description = "Run parser in interactive mode")
    boolean isInteractive = false;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Print usage help")
    boolean helpRequested = false;

    @Option(names = { "-v", "--version"}, versionHelp = true, description = "Print version information")
    boolean versionRequested = false;

    @Override
    public void run() {
        SpotifyWebApi apiDocumentation = null;
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

    static class ManifestVersionProvider implements IVersionProvider {
        private static final String IMPLEMENTATION_TITLE = "spotify-web-api-parser";

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
