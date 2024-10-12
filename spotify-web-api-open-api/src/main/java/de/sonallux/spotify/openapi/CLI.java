package de.sonallux.spotify.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import de.sonallux.json.ReferenceValidator;
import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.parser.OpenAPIV3Parser;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Slf4j
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
            log.error("Failed to write generated file", e);
            System.exit(1);
        }
    }

    private JsonNode readOfficialOpenApi() {
        try (var inputStream = Files.newInputStream(officialOpenApiFile)) {
            return OPEN_API_YAML.readTree(inputStream);
        } catch (IOException e) {
            log.error("Failed to read official OpenAPI file", e);
            System.exit(1);
            return null;
        }
    }

    private JsonNode applyPatches(JsonNode node) {
        try {
            var openApiPatches = new OpenApiPatches();
            return openApiPatches.applyPatches(node);
        } catch (OpenApiPatchException e) {
            log.error("Failed to patch official OpenAPI", e);
            System.exit(1);
            return null;
        }
    }

    private static void validateOpenAPI(JsonNode node) {
        var parseResult = new OpenAPIV3Parser().parseJsonNode(null, node);
        parseResult.getMessages().forEach(log::error);

        var openApiJsonSchema = loadOpenApiJsonSchema();

        var validationMessages = openApiJsonSchema.validate(node);
        validationMessages.forEach(msg -> log.error(msg.toString()));

        var referenceValidationMessages = new ReferenceValidator().validateReference(node);
        referenceValidationMessages.forEach(log::error);

        if (validationMessages.isEmpty() && parseResult.getMessages().isEmpty() && referenceValidationMessages.isEmpty()) {
            log.info("No errors on OpenApi found");
            return;
        }

        System.exit(1);
    }

    private static JsonSchema loadOpenApiJsonSchema() {
        try {
            var mapper = new ObjectMapper();
            var schemaJsonNode = mapper.readTree(CLI.class.getResourceAsStream("/open-api-spec-schema.json"));

            return JsonSchemaFactory
                .getInstance(SpecVersionDetector.detect(schemaJsonNode))
                .getSchema(schemaJsonNode);
        } catch (IOException e) {
            log.error("Failed to load OpenAPI schema", e);
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
