package de.jsone_studios.spotify.parser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpotifyScopes {
    private String url;
    private List<SpotifyScope> scopes;
}
