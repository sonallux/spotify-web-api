package de.sonallux.json.diff.changes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class JsonChangeDeserializer extends StdDeserializer<JsonChange> {

    public JsonChangeDeserializer() {
        this(null);
    }

    public JsonChangeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public JsonChange deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        var type = node.get("type").asText();
        switch (type) {
            case "ValueAdded": {
                var item = node.get("item");
                if (item == null) {
                    throw new JsonParseException(p, "Expecting item to be present");
                }
                return new ValueAdded(extractPath(p, node), item);
            }
            case "ValueRemoved": {
                var item = node.get("item");
                if (item == null) {
                    throw new JsonParseException(p, "Expecting item to be present");
                }
                return new ValueRemoved(extractPath(p, node), item);
            }
            case "ValueChanged": {
                var left = node.get("left");
                if (left == null) {
                    throw new JsonParseException(p, "Expecting left to be present");
                }
                var right = node.get("right");
                if (right == null) {
                    throw new JsonParseException(p, "Expecting right to be present");
                }
                return new ValueChanged(extractPath(p, node), left, right);
            }
        }

        return null;
    }

    private String extractPath(JsonParser p, JsonNode node) throws JsonProcessingException {
        var pathNode = node.get("path");
        if (pathNode == null || !pathNode.isTextual()) {
            throw new JsonParseException(p, "Expecting path to be an string");
        }

        return pathNode.textValue();
    }
}
