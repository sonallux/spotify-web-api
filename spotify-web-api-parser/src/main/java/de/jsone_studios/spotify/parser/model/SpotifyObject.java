package de.jsone_studios.spotify.parser.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SpotifyObject {
    @NonNull
    @ToString.Include
    @EqualsAndHashCode.Include
    private String name;
    private String id;
    private String link;
    private List<Property> properties;

    public SpotifyObject(String name, String id, String link) {
        this(name, id, link, new ArrayList<>());
    }

    public SpotifyObject(String name) {
        this(name, null, null, new ArrayList<>());
    }

    public SpotifyObject(String name, String link) {
        this(name, null, link, new ArrayList<>());
    }

    public SpotifyObject(String name, List<Property> properties) {
        this(name, null, null, properties);
    }

    public SpotifyObject addProperty(Property property) {
        properties.add(property);
        return this;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString(onlyExplicitlyIncluded = true)
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Property {
        @NonNull
        @ToString.Include
        @EqualsAndHashCode.Include
        private String name;
        private String type;
        private String description;
        private Boolean nonNull;

        public Property(String name, String type) {
            this(name, type, "", null);
        }

        public Property(String name, String type, String description) {
            this(name, type, description, null);
        }
    }
}
