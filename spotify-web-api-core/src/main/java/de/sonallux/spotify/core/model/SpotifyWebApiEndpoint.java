package de.sonallux.spotify.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXISTING_PROPERTY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SpotifyWebApiEndpoint {
    @NonNull
    @ToString.Include
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private String link;
    private String description;
    private String httpMethod;
    private String path;
    private List<Parameter> parameters;
    private RequestBody requestBody;
    private String responseDescription;
    private List<String> scopes;
    private String notes;
    private List<ResponseType> responseTypes;

    public SpotifyWebApiEndpoint(@NonNull String id, String name, String link, String description, String httpMethod, String path, List<Parameter> parameters, RequestBody requestBody,  String responseDescription, List<String> scopes, String notes) {
        this(id, name, link, description, httpMethod, path, parameters, requestBody, responseDescription, scopes, notes, new ArrayList<>());
    }

    public SpotifyWebApiEndpoint addScope(String scope) {
        this.scopes.add(scope);
        return this;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString(onlyExplicitlyIncluded = true)
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Parameter {
        @NonNull
        @ToString.Include
        @EqualsAndHashCode.Include
        private ParameterLocation location;
        @NonNull
        @ToString.Include
        @EqualsAndHashCode.Include
        private String name;
        private String description;
        private String type;
        private boolean required;
    }

    public enum ParameterLocation {
        PATH,
        HEADER,
        QUERY,
        BODY
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "contentType", include = EXISTING_PROPERTY)
    @JsonSubTypes({
        @JsonSubTypes.Type(name = "application/json", value = JsonRequestBody.class),
        @JsonSubTypes.Type(name = "image/jpeg", value = Base64ImageRequestBody.class)
    })
    @Getter
    @Setter
    @AllArgsConstructor
    public abstract static class RequestBody {
        private String contentType;
        private String description;
    }

    @Getter
    @Setter
    public static class JsonRequestBody extends RequestBody {
        private List<Parameter> parameters;

        @JsonCreator
        public JsonRequestBody(@JsonProperty("description") String description, @JsonProperty("parameters") List<Parameter> parameters) {
            super("application/json", description);
            this.parameters = parameters;
        }
    }

    @Getter
    @Setter
    public static class Base64ImageRequestBody extends RequestBody {
        @JsonCreator
        public Base64ImageRequestBody(@JsonProperty("description") String description) {
            super("image/jpeg", description);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseType {
        @NonNull
        private String type;
        private int status;
    }
}
