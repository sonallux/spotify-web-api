package de.jsone_studios.spotify.parser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SpotifyApiEndpoint {
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
    private String responseDescription;
    private List<String> scopes;
    private List<ResponseType> responseTypes;

    public SpotifyApiEndpoint(@NonNull String id, String name, String link, String description, String httpMethod, String path, List<Parameter> parameters, String responseDescription, List<String> scopes) {
        this(id, name, link, description, httpMethod, path, parameters, responseDescription, scopes, new ArrayList<>());
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
        @JsonIgnore
        private Element descriptionElement;
        private String type;
        private boolean required;

        public Parameter(ParameterLocation location, String name, Element descriptionElement, String type, boolean required) {
            this(location, name, descriptionElement.text(), descriptionElement, type, required);
        }

        public Parameter(ParameterLocation location, String name, String description, String type, boolean required) {
            this(location, name, description, null, type, required);
        }
    }

    public enum ParameterLocation {
        PATH,
        HEADER,
        QUERY,
        BODY
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseType {
        @NonNull
        private String type;
        private int status;
        private String description;
    }
}
