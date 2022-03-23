package de.sonallux.json.patching;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import lombok.Getter;

public class RenameOperation extends PatchOperation {
    @Getter
    @JsonProperty
    protected final String oldKey;

    @Getter
    @JsonProperty
    protected final String newKey;

    @JsonCreator
    public RenameOperation(@JsonProperty("path") String path, @JsonProperty("oldKey") String oldKey, @JsonProperty("newKey") String newKey) {
        super("rename", path);
        this.oldKey = oldKey;
        this.newKey = newKey;
    }

    @Override
    public JsonNode apply(JsonNode node) throws PatchException {
        try {
            var jsonPath = JsonPath.compile(path);
            return jsonPath.renameKey(node.deepCopy(), oldKey, newKey, JSON_PATH_CONFIG);
        } catch (JsonPathException e) {
            throw wrapException(e);
        }
    }
}
