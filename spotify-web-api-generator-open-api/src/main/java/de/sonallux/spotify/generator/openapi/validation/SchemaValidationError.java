package de.sonallux.spotify.generator.openapi.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchemaValidationError {
    private String level;
    private String domain;
    private String keyword;
    private String message;
    private Schema schema;
    private Instance instance;
}
