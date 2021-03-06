package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FeaturedPlaylist {
    public String message;
    public Paging<SimplifiedPlaylist> playlists;
}
