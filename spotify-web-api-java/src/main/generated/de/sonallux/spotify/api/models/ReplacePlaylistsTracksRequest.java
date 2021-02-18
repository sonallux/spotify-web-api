package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * Request body for endpoint <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-reorder-or-replace-playlists-tracks">Replace items in a playlist</a>
 */
@Getter
@Setter
@AllArgsConstructor
public class ReplacePlaylistsTracksRequest {
    @NonNull
    private final java.util.List<String> uris;
}
