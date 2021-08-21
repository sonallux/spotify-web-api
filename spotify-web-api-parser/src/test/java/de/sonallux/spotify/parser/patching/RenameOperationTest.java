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

class RenameOperationTest extends PatchOperationTest {

    private static OperationTestData<RenameOperation> testData;

    @BeforeAll
    static void setup() throws IOException {
        testData = loadTestData("rename", new TypeReference<>() {});
    }

    static Stream<Arguments> testOperationIsApplied() {
        return testData.successCases.stream().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource
    void testOperationIsApplied(OperationTestData.SuccessTestCase<RenameOperation> testCase) throws Exception {
        assertPatchIsApplied(testCase);
    }

    static Stream<Arguments> testOperationThrowsError() {
        return testData.failureCases.stream().map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource
    void testOperationThrowsError(OperationTestData.FailureTestCase<RenameOperation> testCase) throws Exception {
        assertPatchThrowsError(testCase);
    }

    @Test
    void testDeserialization() throws IOException {
        var opAsJson = "{\"op\": \"rename\", \"path\":\"$\", \"oldKey\":\"a\", \"newKey\": \"b\"}";

        var op = OBJECT_MAPPER.readValue(opAsJson, RenameOperation.class);

        assertEquals("rename", op.getOp());
        assertEquals("$", op.getPath());
        assertEquals("a", op.getOldKey());
        assertEquals("b", op.getNewKey());
    }

    @Test
    void testSerialization() throws IOException {
        var op = new RenameOperation("$", "a", "b");

        var opAsJson = OBJECT_MAPPER.writeValueAsString(op);

        assertEquals("{\"op\":\"rename\",\"path\":\"$\",\"oldKey\":\"a\",\"newKey\":\"b\"}", opAsJson);
    }
}
