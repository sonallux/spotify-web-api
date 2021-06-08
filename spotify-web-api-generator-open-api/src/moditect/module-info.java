module spotify.web.api.generator.open.api {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.google.common;
    requires info.picocli;
    requires io.swagger.v3.core;
    requires json.schema.core;
    requires json.schema.validator;
    requires org.slf4j;
    requires swagger.parser.core;
    requires swagger.parser.v3;
    requires io.swagger.v3.oas.models;
    requires de.sonallux.spotify.core;
    requires static lombok;

    exports de.sonallux.spotify.generator.openapi;
    exports de.sonallux.spotify.generator.openapi.validation;

}
