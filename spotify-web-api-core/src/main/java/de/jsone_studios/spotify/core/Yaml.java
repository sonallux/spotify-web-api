package de.jsone_studios.spotify.core;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public final class Yaml {
    private static ObjectMapper mapper;

    private Yaml() {}

    public static ObjectMapper create() {
        if (mapper == null) {
            mapper = createYaml();
        }

        return mapper;
    }

    private static ObjectMapper createYaml() {
        return new YAMLMapper()
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
