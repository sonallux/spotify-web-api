package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SearchResponse {
    private Paging<SimplifiedAlbum> album;
    private Paging<Artist> artist;
    private Paging<SimplifiedPlaylist> playlist;
    private Paging<Track> track;
    private Paging<SimplifiedShow> show;
    private Paging<SimplifiedEpisode> episode;
}