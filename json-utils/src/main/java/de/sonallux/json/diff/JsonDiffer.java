package de.sonallux.json.diff;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.sonallux.json.diff.changes.JsonChange;
import de.sonallux.json.diff.changes.ValueAdded;
import de.sonallux.json.diff.changes.ValueChanged;
import de.sonallux.json.diff.changes.ValueRemoved;
import de.sonallux.json.diff.lists.*;
import de.sonallux.json.patching.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Function;

public class JsonDiffer {
    private final ListDifference<JsonNode> listDifference = new LevenshteinListDifference<>();

    private final Function<ObjectNode, ObjectNode> transformerLeft;
    private final Function<ObjectNode, ObjectNode> transformerRight;

    private final List<String> ignoredProperties = List.of("x-spotify-docs-display-name", "x-spotify-docs-category",
        "x-spotify-docs-endpoint-name", "x-spotify-docs-console-url", "example", "x-spotify-docs-type", "description");

    @Getter
    private List<JsonChange> changes;

    @Getter
    private List<PatchOperation> patches;

    public JsonDiffer(Function<ObjectNode, ObjectNode> transformerLeft, Function<ObjectNode, ObjectNode> transformerRight) {
        this.transformerLeft = transformerLeft;
        this.transformerRight = transformerRight;
    }

    public JsonDiffer() {
        this(Function.identity(), Function.identity());
    }

    // TODO: use json patches from spotify-web-api-parser instead of custom JsonChange format
    public List<JsonChange> jsonDiff(JsonNode left, JsonNode right) {
        patches = new ArrayList<>();
        changes = new ArrayList<>();
        diffJsonNode("$", left, right);
        return changes;
    }

    private void diffJsonNode(String jsonPath, JsonNode left, JsonNode right) {
        var leftType = left.getNodeType();
        var rightType = right.getNodeType();
        if (leftType != rightType) {
            changes.add(new ValueChanged(jsonPath, left, right));
            patches.add(new SetOperation(jsonPath, right));
        } else if (leftType == JsonNodeType.ARRAY) {
            diffJsonArray(jsonPath, (ArrayNode) left, (ArrayNode) right);
        } else if (leftType == JsonNodeType.OBJECT) {
            diffJsonObject(jsonPath, (ObjectNode) left, (ObjectNode) right);
        } else if (!left.equals(right)) {
            changes.add(new ValueChanged(jsonPath, left, right));
            patches.add(new SetOperation(jsonPath, right));
        }
    }

    private void diffJsonArray(String jsonPath, ArrayNode left, ArrayNode right) {
        var leftItems = iteratorToList(left.elements());
        var rightItems = iteratorToList(right.elements());
        var listChanges = listDifference.listDifferences(leftItems, rightItems);

        for (var change : listChanges) {
            if (change instanceof ItemAdded) {
                var itemAdded = (ItemAdded<JsonNode>) change;
                changes.add(new ValueAdded(addPathSegment(jsonPath, itemAdded.getIndex()), itemAdded.getItem()));
                patches.add(new AddOperation(jsonPath, itemAdded.getItem()));
            } else if (change instanceof ItemRemoved) {
                var itemRemoved = (ItemRemoved<JsonNode>) change;
                changes.add(new ValueRemoved(addPathSegment(jsonPath, itemRemoved.getIndex()), itemRemoved.getItem()));
                patches.add(new DeleteOperation(addPathSegment(jsonPath, itemRemoved.getIndex())));
            } else if (change instanceof ItemChanged) {
                var itemChanged = (ItemChanged<JsonNode>) change;
                diffJsonNode(addPathSegment(jsonPath, itemChanged.getIndex()), itemChanged.getLeft(), itemChanged.getRight());
            }
        }
    }

    private void diffJsonObject(String jsonPath, ObjectNode left, ObjectNode right) {
        left = transformerLeft.apply(left);
        right = transformerRight.apply(right);

        var allKeys = new LinkedHashSet<String>();
        left.fieldNames().forEachRemaining(allKeys::add);
        right.fieldNames().forEachRemaining(allKeys::add);

        for (var key : allKeys) {
            if (ignoredProperties.contains(key)) {
                continue;
            }

            var leftProperty = left.get(key);
            var rightProperty = right.get(key);

            if (leftProperty == null) {
                changes.add(new ValueAdded(addPathSegment(jsonPath, key), rightProperty));
                patches.add(new PutOperation(jsonPath, key, rightProperty));
                continue;
            }

            if (rightProperty == null) {
                changes.add(new ValueRemoved(addPathSegment(jsonPath, key), leftProperty));
                patches.add(new DeleteOperation(addPathSegment(jsonPath, key)));
                continue;
            }

            diffJsonNode(addPathSegment(jsonPath, key), leftProperty, rightProperty);
        }
    }

    private String addPathSegment(String jsonPath, String key) {
        return jsonPath + "." + key;
    }

    private String addPathSegment(String jsonPath, int index) {
        return jsonPath + "[" + index + "]";
    }

    private static <T> List<T> iteratorToList(Iterator<T> iterator) {
        var list = new ArrayList<T>();
        iterator.forEachRemaining(list::add);
        return list;
    }
}
