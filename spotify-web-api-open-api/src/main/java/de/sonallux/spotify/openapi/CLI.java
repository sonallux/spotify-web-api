package de.sonallux.spotify.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.parser.OpenAPIV3Parser;
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
    private static final ObjectMapper OPEN_API_YAML = Yaml.mapper();

    @Option(names = {"-f", "--file"}, required = true, description = "The official Spotify Open API file")
    Path officialOpenApiFile;

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
        var officialOpenApi = readOfficialOpenApi();

        var fixedOfficialOpenApi = applyPatches(officialOpenApi);

        var infoNode = (ObjectNode)fixedOfficialOpenApi.get("info");
        infoNode.put("version", VersionProvider.getVersion());
        infoNode.put("title", "Spotify Web API with fixes and improvements from sonallux");
        infoNode.set("contact", OPEN_API_YAML.createObjectNode()
            .put("name", "sonallux")
            .put("url", "https://github.com/sonallux/spotify-web-api"));

        if (shouldValidate) {
            validateOpenAPI(fixedOfficialOpenApi);
        }

        try (var outputStream = Files.newOutputStream(outputFile)) {
            OPEN_API_YAML.writeValue(outputStream, fixedOfficialOpenApi);
        }
        catch (IOException e) {
            System.err.println("Failed to write generated file: " + e.getMessage());
            System.exit(1);
        }
    }

    private JsonNode readOfficialOpenApi() {
        try (var inputStream = Files.newInputStream(officialOpenApiFile)) {
            return OPEN_API_YAML.readTree(inputStream);
        } catch (IOException e) {
            System.err.println("Failed to read official OpenAPI file: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    private JsonNode applyPatches(JsonNode node) {
        try {
            var openApiPatches = new OpenApiPatches();
            return openApiPatches.applyPatches(node);
        } catch (OpenApiPatchException e) {
            System.err.println("Failed to patch official OpenAPI: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    private static void validateOpenAPI(JsonNode node) {
        var parseResult = new OpenAPIV3Parser().parseJsonNode(null, node);
        for (var message : parseResult.getMessages()) {
            System.err.println(message);
        }

        var openApiJsonSchema = loadOpenApiSchema();

        var schemaFactory = JsonSchemaFactory.getInstance(SpecVersionDetector.detect(openApiJsonSchema));
        var schema = schemaFactory.getSchema(openApiJsonSchema);

        var validationMessages = schema.validate(node);
        for (var msg : validationMessages) {
            System.err.println(msg);
        }

        if (validationMessages.isEmpty() && parseResult.getMessages().isEmpty()) {
            System.out.println("No errors on OpenApi found");
            return;
        }

        System.exit(1);
    }

    private static JsonNode loadOpenApiSchema() {
        try {
            var mapper = new ObjectMapper();
            return mapper.readTree(CLI.class.getResourceAsStream("/open-api-spec-schema.json"));
        } catch (IOException e) {
            System.err.println("Failed to load OpenAPI schema: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    public static void main(String[] args) {
        var exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }

    static class ManifestVersionProvider implements IVersionProvider {
        private static final String IMPLEMENTATION_TITLE = "spotify-web-api-open-api";

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
