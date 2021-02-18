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
public class SpotifyWebApiObject {
    @NonNull
    @ToString.Include
    @EqualsAndHashCode.Include
    private String name;
    private String id;
    private String link;
    private List<Property> properties;

    public SpotifyWebApiObject(String name, String id, String link) {
        this(name, id, link, new ArrayList<>());
    }

    public SpotifyWebApiObject(String name) {
        this(name, null, null, new ArrayList<>());
    }

    public SpotifyWebApiObject(String name, String link) {
        this(name, null, link, new ArrayList<>());
    }

    public SpotifyWebApiObject(String name, List<Property> properties) {
        this(name, null, null, properties);
    }

    public SpotifyWebApiObject addProperty(Property property) {
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

        public Property(String name, String type) {
            this(name, type, "");
        }
    }
}
