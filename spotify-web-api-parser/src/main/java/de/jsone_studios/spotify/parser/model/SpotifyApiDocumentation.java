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
public class SpotifyApiDocumentation {

    private String apiDocumentationUrl;
    private String endpointUrl;
    private List<SpotifyObject> objects;
    private List<SpotifyApiCategory> categories;
    private SpotifyScopes scopes;
}
