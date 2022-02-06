package de.sonallux.json.diff.changes;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Value;

@Value
public class ValueRemoved implements JsonChange {
    String type;
    String path;
    JsonNode item;

    private ValueRemoved(String type, String path, JsonNode item) {
        this.type = type;
        this.path = path;
        this.item = item;
    }

    public ValueRemoved(String path, JsonNode item) {
        this("ValueRemoved", path, item);
    }
}
