package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FeaturedPlaylist {
    private String message;
    private Paging<SimplifiedPlaylist> playlists;
}