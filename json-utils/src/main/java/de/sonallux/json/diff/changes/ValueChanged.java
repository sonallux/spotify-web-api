package de.sonallux.json.diff.changes;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Value;

@Value
public class ValueChanged implements JsonChange {
    String type;
    String path;
    JsonNode left;
    JsonNode right;

    private ValueChanged(String type, String path, JsonNode left, JsonNode right) {
        this.type = type;
        this.path = path;
        this.left = left;
        this.right = right;
    }

    public ValueChanged(String path, JsonNode left, JsonNode right) {
        this("ValueChanged", path, left, right);
    }
}
