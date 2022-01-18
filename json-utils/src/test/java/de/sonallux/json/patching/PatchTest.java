package de.sonallux.json.patching;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatchTest {
    private static final JsonNodeFactory FACTORY = JsonNodeFactory.instance;

    @Mock
    private PatchOperation op1;
    @Mock
    private PatchOperation op2;

    @Test
    public void cannotPatchNull() {
        var patch = new Patch(null, List.of(op1, op2));
        assertThrows(NullPointerException.class, () -> patch.apply(null));
    }

    @Test
    public void operationsAreCalledInOrder() throws PatchException {
        var node1 = FACTORY.textNode("hello");
        var node2 = FACTORY.textNode("world");

        when(op1.apply(node1)).thenReturn(node2);

        var patch = new Patch("", List.of(op1, op2));
        patch.apply(node1);

        verify(op1, only()).apply(eq(node1));
        verify(op2, only()).apply(eq(node2));
    }

    @Test
    public void whenOneOperationFailsNextOperationIsNotCalled() throws PatchException {
        when(op1.apply(any(JsonNode.class))).thenThrow(new PatchException("test exception"));

        var patch = new Patch(null, List.of(op1, op2));

        assertThrows(PatchException.class, () -> patch.apply(FACTORY.nullNode()));

        verifyNoInteractions(op2);
    }

    @Test
    void testDeserializationWithoutDescription() throws IOException {
        var objectMapper = new ObjectMapper();
        var patchAsJson = "{\"operations\":[{\"op\": \"add\", \"path\":\"$.a\", \"value\": [1,2]},{\"op\": \"delete\", \"path\":\"$.b\"}]}";

        var patch = objectMapper.readValue(patchAsJson, Patch.class);
        assertNull(patch.getDescription());
        assertEquals(2, patch.getOperations().size());

        assertEquals(AddOperation.class, patch.getOperations().get(0).getClass());
        var addOp = (AddOperation) patch.getOperations().get(0);
        assertEquals("add", addOp.getOp());
        assertEquals("$.a", addOp.getPath());
        assertTrue(addOp.getValue().isArray());
        assertEquals(1, addOp.getValue().get(0).numberValue());
        assertEquals(2, addOp.getValue().get(1).numberValue());

        assertEquals(DeleteOperation.class, patch.getOperations().get(1).getClass());
        var delOp = (DeleteOperation) patch.getOperations().get(1);
        assertEquals("delete", delOp.getOp());
        assertEquals("$.b", delOp.getPath());
    }

    @Test
    void testDeserializationWithDescription() throws IOException {
        var objectMapper = new ObjectMapper();
        var patchAsJson = "{\"description\":\"Test\",\"operations\":[{\"op\": \"add\", \"path\":\"$.a\", \"value\": [1,2]},{\"op\": \"delete\", \"path\":\"$.b\"}]}";

        var patch = objectMapper.readValue(patchAsJson, Patch.class);
        assertEquals("Test", patch.getDescription());
        assertEquals(2, patch.getOperations().size());

        assertEquals(AddOperation.class, patch.getOperations().get(0).getClass());
        var addOp = (AddOperation) patch.getOperations().get(0);
        assertEquals("add", addOp.getOp());
        assertEquals("$.a", addOp.getPath());
        assertTrue(addOp.getValue().isArray());
        assertEquals(1, addOp.getValue().get(0).numberValue());
        assertEquals(2, addOp.getValue().get(1).numberValue());

        assertEquals(DeleteOperation.class, patch.getOperations().get(1).getClass());
        var delOp = (DeleteOperation) patch.getOperations().get(1);
        assertEquals("delete", delOp.getOp());
        assertEquals("$.b", delOp.getPath());
    }

    @Test
    void testSerialization() throws IOException {
        var objectMapper = new ObjectMapper();
        var patch = new Patch("Test", List.of(new AddOperation("$.a", objectMapper.createObjectNode()), new DeleteOperation("$.b")));

        var patchAsJson = objectMapper.writeValueAsString(patch);

        assertEquals("{\"description\":\"Test\",\"operations\":[{\"op\":\"add\",\"path\":\"$.a\",\"value\":{}},{\"op\":\"delete\",\"path\":\"$.b\"}]}", patchAsJson);
    }
}
