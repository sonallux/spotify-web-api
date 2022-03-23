package de.sonallux.json.patching;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import lombok.Getter;

public class PutOperation extends PatchOperation {
    @Getter
    @JsonProperty
    protected final String key;

    @Getter
    @JsonProperty
    protected final JsonNode value;

    @JsonCreator
    public PutOperation(@JsonProperty("path") String path, @JsonProperty("key") String key, @JsonProperty("value") JsonNode value) {
        super("put", path);
        this.key = key;
        this.value = value;
    }

    @Override
    public JsonNode apply(JsonNode node) throws PatchException {
        try {
            var jsonPath = JsonPath.compile(path);
            return jsonPath.put(node.deepCopy(), key, value, JSON_PATH_CONFIG);
        } catch (JsonPathException e) {
            throw wrapException(e);
        }
    }
}
