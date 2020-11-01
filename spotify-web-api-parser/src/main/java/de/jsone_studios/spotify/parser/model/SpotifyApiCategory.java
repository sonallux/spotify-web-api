package de.jsone_studios.spotify.parser.model;

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
public class SpotifyApiCategory {
    @NonNull
    @ToString.Include
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private String link;
    private SortedMap<String, SpotifyApiEndpoint> endpoints;

    @JsonIgnore
    public List<SpotifyApiEndpoint> getEndpointList() {
        return new ArrayList<>(endpoints.values());
    }

    @JsonIgnore
    public Optional<SpotifyApiEndpoint> getEndpoint(String id) {
        return Optional.ofNullable(endpoints.get(id));
    }
}
