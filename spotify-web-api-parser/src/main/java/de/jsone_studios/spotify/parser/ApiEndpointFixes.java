package de.jsone_studios.spotify.parser;

import de.jsone_studios.spotify.parser.model.SpotifyApiCategory;
import de.jsone_studios.spotify.parser.model.SpotifyApiEndpoint;
import de.jsone_studios.spotify.parser.model.SpotifyObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static de.jsone_studios.spotify.parser.model.SpotifyApiEndpoint.ParameterLocation.*;

@Slf4j
class ApiEndpointFixes {

    static void fixApiEndpoints(List<SpotifyApiCategory> categories) {
        removeQueryParameterFromEndpointUrl(categories);
        fixResponses(categories);
        fixTopArtistsTracksEndpoint(categories);
        fixChangePlaylistsDetails(categories);
        fixGetInfoAboutUsersCurrentPlayback(categories);
        fixAddTracksToPlaylist(categories);
        fixStartAUsersPlayback(categories);
    }

    private static void fixResponses(List<SpotifyApiCategory> categories) {
        try {
            var responseTypeMapper = new ResponseTypeMapper();
            responseTypeMapper.update(categories);
            responseTypeMapper.save();

            for (var category : categories) {
                for (var endpoint : category.getEndpoints()) {
                    var endpointResponse = responseTypeMapper.getEndpointResponse(category.getId(), endpoint.getId());
                    if (endpointResponse == null || endpointResponse.getResponseTypes().isEmpty()) {
                        log.warn("Missing response type in {} for {} {} with response: \n{}\n", category.getId(),
                                endpoint.getHttpMethod(), endpoint.getId(), endpoint.getResponseDescription());
                        continue;
                    }
                    endpoint.setResponseTypes(endpointResponse.getResponseTypes());
                }
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error("Failed to load missing response types", e);
        }
    }

    private static void fixTopArtistsTracksEndpoint(List<SpotifyApiCategory> categories) {
        var paramLimit = categories.stream()
                .filter(c -> "category-personalization".equals(c.getId()))
                .flatMap(c -> c.getEndpoints().stream())
                .filter(e -> "endpoint-get-users-top-artists-and-tracks".equals(e.getId()))
                .flatMap(e -> e.getParameters().stream())
                .filter(p -> p.getLocation() == QUERY && "limit".equals(p.getName()) && "String".equals(p.getType()))
                .findFirst().orElse(null);
        if (paramLimit == null) {
            log.warn("users-top-artists-and-tracks wrong limit parameter type has been fixed");
        } else {
            paramLimit.setType("Integer");
        }
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

    private static void removeQueryParameterFromEndpointUrl(List<SpotifyApiCategory> categories) {
        categories.stream()
                .flatMap(c -> c.getEndpoints().stream())
                .forEach(e -> {
                    //Remove query parameter from url
                    var queryParamStart = e.getPath().indexOf('?');
                    if (queryParamStart != -1) {
                        e.setPath(e.getPath().substring(0, queryParamStart));
                    }
                });
    }

    private static void fixGetInfoAboutUsersCurrentPlayback(List<SpotifyApiCategory> categories) {
        var endpoint = categories.stream()
                .filter(c -> "category-player".equals(c.getId()))
                .flatMap(c -> c.getEndpoints().stream())
                .filter(e -> "endpoint-get-information-about-the-users-current-playback".equals(e.getId()))
                .findFirst().orElseThrow(() -> new RuntimeException("Can not find endpoint-get-information-about-the-users-current-playback"));
        if (endpoint.getParameters().stream().filter(p -> "Authorization".equals(p.getName())).count() == 1) {
            log.warn("Authorization header for endpoint-get-information-about-the-users-current-playback has been fixed");
            return;
        }

        endpoint.getParameters().add(new SpotifyApiEndpoint.Parameter(HEADER, "Authorization",
                "A valid access token from the Spotify Accounts service: see the Web API Authorization Guide for details. The access token must have been issued on behalf of a user. The access token must have the user-read-playback-state scope authorized in order to read information.",
                "String", true));
        endpoint.getScopes().add("user-read-playback-state");
    }

    private static void fixAddTracksToPlaylist(List<SpotifyApiCategory> categories) {
        var param = categories.stream()
                .filter(c -> "category-playlists".equals(c.getId()))
                .flatMap(c -> c.getEndpoints().stream())
                .filter(e -> "endpoint-add-tracks-to-playlist".equals(e.getId()))
                .flatMap(e -> e.getParameters().stream())
                .filter(p -> p.getLocation() == QUERY && "uri".equals(p.getName()))
                .findFirst().orElse(null);
        if (param == null) {
            log.warn("endpoint-add-tracks-to-playlist query param name has been fixed");
        } else {
            param.setName("uris");
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
                .filter(p -> "offset".equals(p.getName()) && "Object".equals(p.getDescription()))
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
}
