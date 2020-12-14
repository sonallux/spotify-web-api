package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SearchResponse {
    private Paging<SimplifiedAlbum> album;
    private Paging<Artist> artist;
    private Paging<SimplifiedEpisode> episode;
    private Paging<SimplifiedPlaylist> playlist;
    private Paging<SimplifiedShow> show;
    private Paging<Track> track;
}
