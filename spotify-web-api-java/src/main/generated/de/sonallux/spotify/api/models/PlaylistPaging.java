package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PlaylistPaging {
    public Paging<SimplifiedPlaylist> playlists;
}
