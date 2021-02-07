package de.sonallux.spotify.core.model;

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
public class SpotifyWebApi {

    private String apiDocumentationUrl;
    private String endpointUrl;
    private SortedMap<String, SpotifyWebApiObject> objects;
    private SortedMap<String, SpotifyWebApiCategory> categories;
    private SpotifyAuthorizationScopes scopes;

    @JsonIgnore
    public Collection<SpotifyWebApiObject> getObjectList() {
        return Collections.unmodifiableCollection(objects.values());
    }

    @JsonIgnore
    public Optional<SpotifyWebApiObject> getObject(String name) {
        return Optional.ofNullable(objects.get(name));
    }

    @JsonIgnore
    public Collection<SpotifyWebApiCategory> getCategoryList() {
        return Collections.unmodifiableCollection(categories.values());
    }

    @JsonIgnore
    public Optional<SpotifyWebApiCategory> getCategory(String id) {
        return Optional.ofNullable(categories.get(id));
    }
}
