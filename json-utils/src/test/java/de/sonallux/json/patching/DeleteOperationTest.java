package de.sonallux.json.patching;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteOperationTest extends PatchOperationTest {

    private static OperationTestData<DeleteOperation> testData;

    @BeforeAll
    static void setup() throws IOException {
        testData = loadTestData("delete", new TypeReference<>() {});
    }

    static Stream<Arguments> testOperationIsApplied() {
        return testData.successCases.stream().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource
    void testOperationIsApplied(OperationTestData.SuccessTestCase<DeleteOperation> testCase) throws Exception {
        assertPatchIsApplied(testCase);
    }

    static Stream<Arguments> testOperationThrowsError() {
        return testData.failureCases.stream().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource
    void testOperationThrowsError(OperationTestData.FailureTestCase<DeleteOperation> testCase) {
        assertPatchThrowsError(testCase);
    }

    @Test
    void testDeserialization() throws IOException {
        var opAsJson = "{\"op\": \"delete\", \"path\":\"$.a\"}";

        var op = OBJECT_MAPPER.readValue(opAsJson, DeleteOperation.class);

        assertEquals("delete", op.getOp());
        assertEquals("$.a", op.getPath());
    }

    @Test
    void testSerialization() throws IOException {
        var op = new DeleteOperation("$.a");

        var opAsJson = OBJECT_MAPPER.writeValueAsString(op);

        assertEquals("{\"op\":\"delete\",\"path\":\"$.a\"}", opAsJson);
    }
}
