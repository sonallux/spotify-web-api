package de.sonallux.spotify.generator.openapi.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SchemaValidationError {
    private String level;
    private String domain;
    private String keyword;
    private String message;
    private Schema schema;
    private Instance instance;
    private List<String> required;
    private List<String> missing;

    public SchemaValidationError() {
    }

    public SchemaValidationError(JsonNode node) {
        JsonNode prop = node.get("level");
        if (prop != null) {
            level = prop.asText();
        }

        prop = node.get("domain");
        if (prop != null) {
            domain = prop.asText();
        }

        prop = node.get("keyword");
        if (prop != null) {
            keyword = prop.asText();
        }

        prop = node.get("message");
        if (prop != null) {
            message = prop.asText();
        }

        prop = node.get("schema");
        if (prop != null) {
            schema = new Schema();
            JsonNode s = prop;
            prop = s.get("loadingURI");
            if (prop != null) {
                schema.setLoadingURI(prop.asText());
            }
            prop = s.get("pointer");
            if (prop != null) {
                schema.setPointer(prop.asText());
            }
        }

        prop = node.get("instance");
        if (prop != null) {
            instance = new Instance();
            JsonNode s = prop;
            prop = s.get("pointer");
            if (prop != null) {
                instance.setPointer(prop.asText());
            }
        }

        prop = node.get("required");
        if (prop != null && prop.isArray()) {
            ArrayNode an = (ArrayNode) prop;
        }
    }
}
