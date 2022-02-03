module spotify.web.api.openapi {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires info.picocli;
    requires io.swagger.v3.core;
    requires com.networknt.schema;
    requires org.slf4j;
    requires de.sonallux.json;
    requires static lombok;

    exports de.sonallux.spotify.openapi;
}
