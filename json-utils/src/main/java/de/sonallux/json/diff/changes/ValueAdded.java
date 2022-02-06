package de.sonallux.json.diff.changes;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Value;

@Value
public class ValueAdded implements JsonChange {
    String type;
    String path;
    JsonNode item;

    private ValueAdded(String type, String path, JsonNode item) {
        this.type = type;
        this.path = path;
        this.item = item;
    }

    public ValueAdded(String path, JsonNode item) {
        this("ValueAdded", path, item);
    }
}
