package de.sonallux.spotify.parser.patching;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

abstract class PatchOperationTest {
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static <T extends PatchOperation> OperationTestData<T> loadTestData(String operation, TypeReference<OperationTestData<T>> type) throws IOException {
        var resource = "/patching/" + operation + ".json";
        try (var inStream = PatchOperationTest.class.getResourceAsStream(resource)) {
            return OBJECT_MAPPER.readValue(inStream, type);
        }
    }

    void assertPatchThrowsError(OperationTestData.FailureTestCase<? extends PatchOperation> testCase) {
        var thrownException = assertThrows(PatchException.class, () -> testCase.op.apply(testCase.node));
        assertEquals(testCase.message, thrownException.getMessage());
    }

    void assertPatchIsApplied(OperationTestData.SuccessTestCase<? extends PatchOperation> testCase) throws PatchException {
        var actual = testCase.op.apply(testCase.node);

        assertEquals(testCase.expected, actual);

        if (testCase.node.isContainerNode()) {
            assertNotSame(testCase.node, actual, "Operation didn't make a copy of the input node");
        }
    }
}

