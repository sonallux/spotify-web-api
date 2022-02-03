package de.sonallux.spotify.generator.openapi.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

import java.io.IOException;

public class OpenApiValidator {
    private static final String SCHEMA_FILE = "/open-api-spec-schema.json";

    private static final ObjectMapper YAML_MAPPER = Yaml.mapper();
    private static final ObjectMapper JSON_MAPPER = Json.mapper();

    public ValidationResponse validateByContent(String content) throws Exception {
        ValidationResponse output = new ValidationResponse();

        JsonNode spec = readNode(content);
        if (spec == null) {
            ProcessingMessage pm = new ProcessingMessage()
                .setLogLevel(LogLevel.ERROR)
                .setMessage("Unable to read content.  It may be invalid JSON or YAML");
            output.addValidationMessage(convertProcessingMessage(pm));
            return output;
        }

        var result = readOpenApi(content);
        for (var message : result.getMessages()) {
            ProcessingMessage pm = new ProcessingMessage()
                .setLogLevel(LogLevel.ERROR)
                .setMessage("OpenAPI parse error: " + message);
            output.addValidationMessage(convertProcessingMessage(pm));
        }

        // do actual JSON schema validation
        JsonSchema schema = getSchemaV3();
        ProcessingReport report = schema.validate(spec);

        for (ProcessingMessage pm : report) {
            output.addValidationMessage(convertProcessingMessage(pm));
        }

        return output;
    }

    private JsonSchema getSchemaV3() throws Exception {
        JsonNode schemaObject = JSON_MAPPER.readTree(OpenApiValidator.class.getResourceAsStream(SCHEMA_FILE));
        ObjectNode oNode = (ObjectNode) schemaObject;
        if (oNode.get("id") != null) {
            oNode.remove("id");
        }
        if (oNode.get("$schema") != null) {
            oNode.remove("$schema");
        }
        if (oNode.get("description") != null) {
            oNode.remove("description");
        }

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        return factory.getJsonSchema(schemaObject);
    }

    private SwaggerParseResult readOpenApi(String content) {
        OpenAPIV3Parser parser = new OpenAPIV3Parser();
        return parser.readContents(content, null, null);

    }

    private SchemaValidationError convertProcessingMessage(ProcessingMessage processingMessage) {
        try {
            var jsonNode = processingMessage.asJson();
            return JSON_MAPPER.treeToValue(jsonNode, SchemaValidationError.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            var schemaValidationError = new SchemaValidationError();
            schemaValidationError.setLevel("error");
            schemaValidationError.setMessage("Failed to parse processing message: " + e.getMessage());
            return schemaValidationError;
        }
    }

    private JsonNode readNode(String text) {
        try {
            return YAML_MAPPER.readTree(text);
        } catch (IOException e) {
            return null;
        }
    }
}
