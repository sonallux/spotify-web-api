package de.sonallux.spotify.core.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private String responseDescription;
    private List<String> scopes;
    private String notes;
    private List<ResponseType> responseTypes;

    public SpotifyWebApiEndpoint(@NonNull String id, String name, String link, String description, String httpMethod, String path, List<Parameter> parameters, String responseDescription, List<String> scopes, String notes) {
        this(id, name, link, description, httpMethod, path, parameters, responseDescription, scopes, notes, new ArrayList<>());
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
