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
public class SpotifyAuthorizationScopes {
    private String url;
    private SortedMap<String, SpotifyScope> scopes;

    @JsonIgnore
    public Collection<SpotifyScope> getScopeList() {
        return Collections.unmodifiableCollection(scopes.values());
    }

    @JsonIgnore
    public Optional<SpotifyScope> getScope(String name) {
        return Optional.ofNullable(scopes.get(name));
    }
}
