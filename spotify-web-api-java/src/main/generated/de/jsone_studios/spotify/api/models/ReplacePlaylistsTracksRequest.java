package de.jsone_studios.spotify.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplacePlaylistsTracksRequest {
    private java.util.List<String> uris;
}
