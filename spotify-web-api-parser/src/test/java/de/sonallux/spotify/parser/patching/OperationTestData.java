package de.sonallux.spotify.parser.patching;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class OperationTestData<T extends PatchOperation> {
    public List<FailureTestCase<T>> failureCases;
    public List<SuccessTestCase<T>> successCases;

    public static class FailureTestCase<T extends PatchOperation> {
        public T op;
        public JsonNode node;
        public String message;
    }

    public static class SuccessTestCase<T extends PatchOperation> {
        public T op;
        public JsonNode node;
        public JsonNode expected;
    }
}
