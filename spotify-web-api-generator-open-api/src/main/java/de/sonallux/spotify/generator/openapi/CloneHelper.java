package de.sonallux.spotify.generator.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;

import java.util.HashMap;
import java.util.Map;

class CloneHelper {
    private final ObjectMapper mapper;
    private final Map<Object, String> objectCache;

    public CloneHelper() {
        this.mapper = Json.mapper();
        this.objectCache = new HashMap<>();
    }

    Schema cloneSchema(Schema schema) {
        try {
            String schemaJson;
            if (objectCache.containsKey(schema)) {
                schemaJson = objectCache.get(schema);
            } else {
                schemaJson = mapper.writeValueAsString(schema);
                objectCache.put(schema, schemaJson);
            }

            return mapper.readValue(schemaJson, Schema.class);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to clone Schema", e);
        }
    }
}
