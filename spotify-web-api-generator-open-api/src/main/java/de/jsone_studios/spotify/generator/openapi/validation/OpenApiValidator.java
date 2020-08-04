package de.jsone_studios.spotify.generator.openapi.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonschema.core.report.ListProcessingReport;
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

    private static ObjectMapper yamlMapper = Yaml.mapper();
    private static ObjectMapper jsonMapper = Json.mapper();

    public ValidationResponse validateByContent(String content) throws Exception {
        ValidationResponse output = new ValidationResponse();

        JsonNode spec = readNode(content);
        if (spec == null) {
            ProcessingMessage pm = new ProcessingMessage();
            pm.setLogLevel(LogLevel.ERROR);
            pm.setMessage("Unable to read content.  It may be invalid JSON or YAML");
            output.addValidationMessage(new SchemaValidationError(pm.asJson()));
            return output;
        }

        SwaggerParseResult result;
        try {
            result = readOpenApi(content);
        } catch (Exception e) {
            ProcessingMessage pm = new ProcessingMessage();
            pm.setLogLevel(LogLevel.ERROR);
            pm.setMessage("unable to parse OpenAPI: " + e.getMessage());
            output.addValidationMessage(new SchemaValidationError(pm.asJson()));
            return output;
        }
        if (result != null) {
            for (String message : result.getMessages()) {
                output.addMessage(message);
            }
        }

        // do actual JSON schema validation
        JsonSchema schema = getSchemaV3();
        ProcessingReport report = schema.validate(spec);
        ListProcessingReport lp = new ListProcessingReport();
        lp.mergeWith(report);

        for (ProcessingMessage pm : lp) {
            output.addValidationMessage(new SchemaValidationError(pm.asJson()));
        }

        return output;
    }

    private JsonSchema getSchemaV3() throws Exception {
        JsonNode schemaObject = jsonMapper.readTree(OpenApiValidator.class.getResourceAsStream(SCHEMA_FILE));
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

    private SwaggerParseResult readOpenApi(String content) throws IllegalArgumentException {
        OpenAPIV3Parser parser = new OpenAPIV3Parser();
        return parser.readContents(content, null, null);

    }

    private JsonNode readNode(String text) {
        try {
            return yamlMapper.readTree(text);
        } catch (IOException e) {
            return null;
        }
    }
}
