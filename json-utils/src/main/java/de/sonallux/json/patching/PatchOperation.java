package de.sonallux.json.patching;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "op", include = PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(name = "add", value = AddOperation.class),
    @JsonSubTypes.Type(name = "set", value = SetOperation.class),
    @JsonSubTypes.Type(name = "delete", value = DeleteOperation.class),
    @JsonSubTypes.Type(name = "put", value = PutOperation.class),
    @JsonSubTypes.Type(name = "rename", value = RenameOperation.class),
    @JsonSubTypes.Type(name = "test", value = TestOperation.class)
})
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PatchOperation {

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @JsonIgnore
    protected static final Configuration JSON_PATH_CONFIG = Configuration.builder()
        .jsonProvider(new JacksonJsonNodeJsonProvider(OBJECT_MAPPER))
        .mappingProvider(new JacksonMappingProvider(OBJECT_MAPPER))
        .build();

    @Getter
    @JsonTypeId
    protected final String op;

    @Getter
    @JsonProperty
    protected final String path;

    public abstract JsonNode apply(JsonNode node) throws PatchException;

    protected PatchException wrapException(JsonPathException e) {
        var message = e.getMessage();
        if (message == null) {
            message = e.getClass().getSimpleName();
        }
        return new PatchException(message, e);
    }
}
