package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SearchResponse {
    public Paging<SimplifiedAlbum> album;
    public Paging<Artist> artist;
    public Paging<SimplifiedEpisode> episode;
    public Paging<SimplifiedPlaylist> playlist;
    public Paging<SimplifiedShow> show;
    public Paging<Track> track;
}
