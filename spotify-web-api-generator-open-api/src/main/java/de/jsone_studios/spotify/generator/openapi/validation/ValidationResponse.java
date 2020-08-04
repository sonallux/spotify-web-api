package de.jsone_studios.spotify.generator.openapi.validation;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationResponse {
    private List<String> messages = new ArrayList<>();
    private List<SchemaValidationError> schemaValidationMessages = new ArrayList<>();

    public void addValidationMessage(SchemaValidationError schemaValidationError) {
        this.schemaValidationMessages.add(schemaValidationError);
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
