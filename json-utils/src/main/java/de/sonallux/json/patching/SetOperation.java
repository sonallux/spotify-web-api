package de.sonallux.json.patching;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import lombok.Getter;

public class SetOperation extends PatchOperation {

    @Getter
    @JsonProperty
    protected final JsonNode value;

    @JsonCreator
    public SetOperation(@JsonProperty("path") String path, @JsonProperty("value") JsonNode value) {
        super("set", path);
        this.value = value;
    }

    @Override
    public JsonNode apply(JsonNode node) throws PatchException {
        try {
            var jsonPath = JsonPath.compile(path);
            return jsonPath.set(node.deepCopy(), value, JSON_PATH_CONFIG);
        } catch (JsonPathException e) {
            throw new PatchException(e.getMessage(), e);
        }
    }
}
