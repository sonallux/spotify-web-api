package de.sonallux.json;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Validate that nodes referenced by <code>$ref</code> fields do exist. This currently only supports internal
 * references (e.g. <code>#/field1/field2</code>).
 * <br>
 * json-schema-validator cannot provide this because it is not part of the json-schema spec.
 * <br>
 * swagger-parser only reports wrong references for schemas, but not responses, parameters, headers and others.
 * See <a href="https://github.com/swagger-api/swagger-parser/issues/1396">this issue on GitHub</a>
 */
public class ReferenceValidator {
    public Set<String> validateReference(JsonNode node) {
        var messages = new HashSet<String>();
        validateInternal(node, node, messages);
        return messages;
    }

    private void validateInternal(JsonNode currentNode, JsonNode rootNode, Set<String> messages) {
        getReference(currentNode)
            .filter(ref -> !referencedNodeExists(ref, rootNode))
            .ifPresent(ref -> messages.add("Reference not found: " + ref));

        currentNode.elements().forEachRemaining(node -> validateInternal(node, rootNode, messages));
    }

    private static boolean referencedNodeExists(String reference, JsonNode root) {
        var parts = reference.substring(2).split("/");

        var currentNode = root;
        for (String field : parts) {
            currentNode = currentNode.get(field);
            if (currentNode == null) {
                return false;
            }
        }

        return true;
    }

    private static Optional<String> getReference(JsonNode node) {
        return Optional.ofNullable(node.get("$ref"))
            .filter(JsonNode::isTextual)
            .map(JsonNode::textValue);
    }
}
