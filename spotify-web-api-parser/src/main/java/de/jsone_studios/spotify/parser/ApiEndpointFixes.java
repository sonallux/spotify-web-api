package de.jsone_studios.spotify.parser;

import de.jsone_studios.spotify.parser.model.SpotifyApiCategory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static de.jsone_studios.spotify.parser.model.SpotifyApiEndpoint.ParameterLocation.PATH;

@Slf4j
class ApiEndpointFixes {

    static void fixApiEndpoints(List<SpotifyApiCategory> categories) {
        fixChangePlaylistsDetails(categories);
        fixGetInfoAboutUsersCurrentPlayback(categories);
        fixStartAUsersPlayback(categories);
        fixGetUsersSavedShowsScope(categories);
        fixCheckUsersSavedShowsScope(categories);
    }

    private static void fixChangePlaylistsDetails(List<SpotifyApiCategory> categories) {
        var paramPath = categories.stream()
                .filter(c -> "category-playlists".equals(c.getId()))
                .flatMap(c -> c.getEndpoints().stream())
                .filter(e -> "endpoint-change-playlist-details".equals(e.getId()))
                .flatMap(e -> e.getParameters().stream())
                .filter(p -> p.getLocation() == PATH && "playlist_id".equals(p.getName()))
                .findFirst().orElse(null);
        if (paramPath == null) {
            log.warn("change-playlist-details wrong playlist_id parameter type has been fixed");
        } else {
            paramPath.setType("String");
        }
    }

    private static void fixGetInfoAboutUsersCurrentPlayback(List<SpotifyApiCategory> categories) {
        var endpoint = categories.stream()
                .filter(c -> "category-player".equals(c.getId()))
                .flatMap(c -> c.getEndpoints().stream())
                .filter(e -> "endpoint-get-information-about-the-users-current-playback".equals(e.getId()))
                .findFirst().orElseThrow(() -> new RuntimeException("Can not find endpoint-get-information-about-the-users-current-playback"));
        if (endpoint.getScopes().size() != 0) {
            log.warn("endpoint-get-information-about-the-users-current-playback missing scope has been fixed");
        } else {
            endpoint.getScopes().add("user-read-playback-state");
        }
    }

    private static void fixStartAUsersPlayback(List<SpotifyApiCategory> categories) {
        var endpoint = categories.stream()
                .filter(c -> "category-player".equals(c.getId()))
                .flatMap(c -> c.getEndpoints().stream())
                .filter(e -> "endpoint-start-a-users-playback".equals(e.getId()))
                .findFirst().orElse(null);
        if (endpoint == null) {
            log.warn("endpoint-start-a-users-playback is not present.");
            return;
        }

        var contextUriParam = endpoint.getParameters().stream()
                .filter(p -> "context_uri".equals(p.getName()) && "string".equals(p.getDescription()))
                .findFirst().orElse(null);
        if (contextUriParam == null){
            log.warn("endpoint-start-a-users-playback context_uri param has been fixed");
        } else {
            contextUriParam.setDescription("Spotify URI of the context to play. Valid contexts are albums, artists, playlists. Example: {\"context_uri\": \"spotify:album:1Je1IMUlBXcx1Fz0WE7oPT\"}");
        }

        var urisParam = endpoint.getParameters().stream()
                .filter(p -> "uris".equals(p.getName()) && "Array of URIs".equals(p.getDescription()))
                .findFirst().orElse(null);
        if (urisParam == null){
            log.warn("endpoint-start-a-users-playback uris param has been fixed");
        } else {
            urisParam.setDescription("A JSON array of the Spotify track URIs to play. For example: {\"uris\": [\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\", \"spotify:track:1301WleyT98MSxVHPZCA6M\"]}");
        }

        var offsetParam = endpoint.getParameters().stream()
                .filter(p -> "offset".equals(p.getName()) && "object".equals(p.getDescription()))
                .findFirst().orElse(null);
        if (offsetParam == null){
            log.warn("endpoint-start-a-users-playback offset param has been fixed");
        } else {
            offsetParam.setDescription("Indicates from where in the context playback should start. Only available when context_uri corresponds to an album or playlist object, or when the uris parameter is used. " +
                    "“position” is zero based and can’t be negative. Example: \"offset\": {\"position\": 5} " +
                    "“uri” is a string representing the uri of the item to start at. Example: \"offset\": {\"uri\": \"spotify:track:1301WleyT98MSxVHPZCA6M\"}");
        }

        var positionMsParam = endpoint.getParameters().stream()
                .filter(p -> "position_ms".equals(p.getName()) && "integer".equals(p.getDescription()))
                .findFirst().orElse(null);
        if (positionMsParam == null){
            log.warn("endpoint-start-a-users-playback position_ms param has been fixed");
        } else {
            positionMsParam.setDescription("Indicates from what position to start playback. Must be a positive number. Passing in a position that is greater than the length of the track will cause the player to start playing the next song.");
        }
    }

    private static void fixGetUsersSavedShowsScope(List<SpotifyApiCategory> categories) {
        var endpoint = categories.stream()
                .filter(c -> "category-library".equals(c.getId()))
                .flatMap(c -> c.getEndpoints().stream())
                .filter(e -> "endpoint-get-users-saved-shows".equals(e.getId()))
                .filter(e -> e.getScopes().contains("user-libary-read"))
                .findFirst().orElse(null);
        if (endpoint == null) {
            log.warn("endpoint-get-users-saved-shows scope user-libary-read has been fixed");
        } else {
            endpoint.setScopes(List.of("user-library-read"));
        }
    }

    private static void fixCheckUsersSavedShowsScope(List<SpotifyApiCategory> categories) {
        var endpoint = categories.stream()
                .filter(c -> "category-library".equals(c.getId()))
                .flatMap(c -> c.getEndpoints().stream())
                .filter(e -> "endpoint-check-users-saved-shows".equals(e.getId()))
                .filter(e -> e.getScopes().contains("user-libary-read"))
                .findFirst().orElse(null);
        if (endpoint == null) {
            log.warn("endpoint-check-users-saved-shows scope user-libary-read has been fixed");
        } else {
            endpoint.setScopes(List.of("user-library-read"));
        }
    }
}
