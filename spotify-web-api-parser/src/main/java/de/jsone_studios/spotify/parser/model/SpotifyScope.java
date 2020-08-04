package de.jsone_studios.spotify.parser.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SpotifyScope {
    @NonNull
    @ToString.Include
    @EqualsAndHashCode.Include
    private String id;
    private String link;
    private String description;
    private String userDescription;
    private List<EndpointLink> endpoints;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EndpointLink {
        @NonNull
        private String url;
        @NonNull
        private String api;
        private String endpoint;

        public EndpointLink(String url, String api) {
            this.url = url;
            this.api = api;
        }
    }
}
