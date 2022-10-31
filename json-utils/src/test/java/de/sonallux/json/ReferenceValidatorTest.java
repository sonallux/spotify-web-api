package de.sonallux.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReferenceValidatorTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void validateReference() {
        var innerNodeBaz = mapper.createObjectNode()
            .put("$ref", "#/foo/bar")
            .put("$ref", "#/foo/bat");

        var innerNodeFoo = mapper.createObjectNode()
            .put("bar", "asdf");

        var node = mapper.createObjectNode()
            .put("$ref", "#/baz");

        node.set("baz", innerNodeBaz);
        node.set("foo", innerNodeFoo);

        var messages = new ReferenceValidator().validateReference(node);

        assertEquals(1, messages.size());
        assertIterableEquals(List.of("Reference not found: #/foo/bat"), messages);
    }
}
