package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FollowingArtists {
    public CursorPaging<Artist> artists;
}
