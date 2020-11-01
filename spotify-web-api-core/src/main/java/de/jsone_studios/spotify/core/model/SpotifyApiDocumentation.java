package de.jsone_studios.spotify.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.SortedMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpotifyApiDocumentation {

    private String apiDocumentationUrl;
    private String endpointUrl;
    private SortedMap<String, SpotifyObject> objects;
    private SortedMap<String, SpotifyApiCategory> categories;
    private SpotifyScopes scopes;

    @JsonIgnore
    public Collection<SpotifyObject> getObjectList() {
        return Collections.unmodifiableCollection(objects.values());
    }

    @JsonIgnore
    public Optional<SpotifyObject> getObject(String name) {
        return Optional.ofNullable(objects.get(name));
    }

    @JsonIgnore
    public Collection<SpotifyApiCategory> getCategoryList() {
        return Collections.unmodifiableCollection(categories.values());
    }

    @JsonIgnore
    public Optional<SpotifyApiCategory> getCategory(String id) {
        return Optional.ofNullable(categories.get(id));
    }
}
