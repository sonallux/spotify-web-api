package de.sonallux.json.patching;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.PathNotFoundException;
import lombok.Getter;

public class TestOperation extends PatchOperation {

    @Getter
    @JsonProperty
    protected final JsonNode value;

    @JsonCreator
    public TestOperation(@JsonProperty("path") String path, @JsonProperty("value") JsonNode value) {
        super("test", path);
        this.value = value;
    }

    @Override
    public JsonNode apply(JsonNode node) throws PatchException {
        try {
            var jsonPath = JsonPath.compile(path);
            JsonNode testNode = jsonPath.read(node, JSON_PATH_CONFIG);
            if (!value.equals(testNode)) {
                throw new PatchException("Value differs from expected value");
            }

            return node.deepCopy();
        } catch (PathNotFoundException e) {
            if (value.isNull()) {
                // Null is used to test non-existence
                return node.deepCopy();
            }
            throw new PatchException("Path did not exist", e);
        }
        catch (JsonPathException e) {
            throw wrapException(e);
        }
    }
}
