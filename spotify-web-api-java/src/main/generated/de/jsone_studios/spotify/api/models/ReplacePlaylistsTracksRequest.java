package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ReplacePlaylistsTracksRequest {
    /**
     * A JSON array of the Spotify URIs to set, can be track or episode URIs. For example: {"uris": ["spotify:track:4iV5W9uYEdYUVa79Axb7Rh", "spotify:track:1301WleyT98MSxVHPZCA6M", "spotify:episode:512ojhOuo1ktJprKbVcKyQ"]} Currently, a maximum of 100 items can be set. Note: if the uris parameter is present in the query string, any URIs listed here in the body will be ignored.
     */
    private java.util.List<String> uris;
}