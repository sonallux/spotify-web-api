package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class NewReleases {
    private Paging<SimplifiedAlbum> albums;
}