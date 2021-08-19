package de.sonallux.spotify.parser.patching;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddOperationTest extends PatchOperationTest {

    private static OperationTestData<AddOperation> testData;

    @BeforeAll
    static void setup() throws IOException {
        testData = loadTestData("add", new TypeReference<>() {});
    }

    static Stream<Arguments> testOperationIsApplied() {
        return testData.successCases.stream().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource
    void testOperationIsApplied(OperationTestData.SuccessTestCase<AddOperation> testCase) throws Exception {
        assertPatchIsApplied(testCase);
    }

    static Stream<Arguments> testOperationThrowsError() {
        return testData.failureCases.stream().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource
    void testOperationThrowsError(OperationTestData.FailureTestCase<AddOperation> testCase) throws Exception {
        assertPatchThrowsError(testCase);
    }

    @Test
    void testDeserialization() throws IOException {
        var opAsJson = "{\"op\": \"add\", \"path\":\"$.a\", \"value\": [1,2]}";

        var op = OBJECT_MAPPER.readValue(opAsJson, AddOperation.class);

        assertEquals("add", op.getOp());
        assertEquals("$.a", op.getPath());
        assertTrue(op.getValue().isArray());
        assertEquals(1, op.getValue().get(0).numberValue());
        assertEquals(2, op.getValue().get(1).numberValue());
    }

    @Test
    void testSerialization() throws IOException {
        var op = new AddOperation("$.a", OBJECT_MAPPER.createObjectNode());

        var opAsJson = OBJECT_MAPPER.writeValueAsString(op);

        assertEquals("{\"op\":\"add\",\"path\":\"$.a\",\"value\":{}}", opAsJson);
    }
}
