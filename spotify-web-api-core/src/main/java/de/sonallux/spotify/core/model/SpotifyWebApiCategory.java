package de.sonallux.spotify.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SpotifyWebApiCategory {
    @NonNull
    @ToString.Include
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private String link;
    private SortedMap<String, SpotifyWebApiEndpoint> endpoints;

    @JsonIgnore
    public List<SpotifyWebApiEndpoint> getEndpointList() {
        return new ArrayList<>(endpoints.values());
    }

    @JsonIgnore
    public Optional<SpotifyWebApiEndpoint> getEndpoint(String id) {
        return Optional.ofNullable(endpoints.get(id));
    }
}
