package de.sonallux.json.patching;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Patch {
    private final String description;
    private final List<PatchOperation> operations;

    @JsonCreator
    public Patch(@JsonProperty("description") String description, @JsonProperty("operations") List<PatchOperation> operations) {
        this.description = description;
        this.operations = operations;
    }

    public JsonNode apply(JsonNode node) throws PatchException {
        Objects.requireNonNull(node);
        JsonNode ret = node;
        for (var operation : operations) {
            ret = operation.apply(ret);
        }

        return ret;
    }
}
