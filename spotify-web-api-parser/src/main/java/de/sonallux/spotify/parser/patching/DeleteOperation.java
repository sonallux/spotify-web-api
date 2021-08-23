package de.sonallux.spotify.parser.patching;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;

public class DeleteOperation extends PatchOperation {
    @JsonCreator
    public DeleteOperation(@JsonProperty("path") String path) {
        super("delete", path);
    }

    @Override
    public JsonNode apply(JsonNode node) throws PatchException {
        try {
            var jsonPath = JsonPath.compile(path);
            return jsonPath.delete(node.deepCopy(), JSON_PATH_CONFIG);
        } catch (JsonPathException e) {
            throw new PatchException(e.getMessage(), e);
        }
    }
}
