package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class NewReleases {
    private Paging<SimplifiedAlbum> albums;
}
