package de.sonallux.spotify.core;

import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;
import de.sonallux.spotify.core.model.SpotifyScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EndpointSplitter {

    public static void splitEndpoints(SpotifyWebApi apiDocumentation) throws IllegalArgumentException {
        splitUsersTopArtistsAndTracksEndpoint(apiDocumentation);
        splitReorderOrReplacePlaylistsTracksEndpoint(apiDocumentation);
    }

    public static void splitUsersTopArtistsAndTracksEndpoint(SpotifyWebApi apiDocumentation) throws IllegalArgumentException {
        var category = apiDocumentation.getCategory("category-personalization")
                .orElseThrow(() -> new IllegalArgumentException("Can not find category-personalization"));

        var topArtistsAndTracks = category.getEndpoint("endpoint-get-users-top-artists-and-tracks")
                .orElseThrow(() -> new IllegalArgumentException("Can not find endpoint-get-users-top-artists-and-tracks"));

        var parameters = new ArrayList<>(topArtistsAndTracks.getParameters());
        parameters.removeIf(p -> "type".equals(p.getName()));

        var responseDescriptionArtists = "On success, the HTTP status code in the response header" +
                " is `200 OK` and the response body contains a [paging object](https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object)" +
                " of [Artists](https://developer.spotify.com/documentation/web-api/reference/object-model/#artist-object-full)." +
                " On error, the header status code is an [error code](https://developer.spotify.com/documentation/web-api/#response-status-codes)" +
                " and the response body contains an [error object](https://developer.spotify.com/documentation/web-api/#response-schema).";

        var responseDescriptionTracks = "On success, the HTTP status code in the response header" +
                " is `200 OK` and the response body contains a [paging object](https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object)" +
                " of [Tracks](https://developer.spotify.com/documentation/web-api/reference/object-model/#track-object-full)." +
                " On error, the header status code is an [error code](https://developer.spotify.com/documentation/web-api/#response-status-codes)" +
                " and the response body contains an [error object](https://developer.spotify.com/documentation/web-api/#response-schema).";

        var topArtists = new SpotifyWebApiEndpoint(
                "endpoint-get-users-top-artists",
                "Get a User's Top Artists",
                topArtistsAndTracks.getLink(),
                "Get the current user’s top artists based on calculated affinity.",
                "GET",
                "/me/top/artists",
                parameters,
                null,
                responseDescriptionArtists,
                topArtistsAndTracks.getScopes(),
                topArtistsAndTracks.getNotes(),
                List.of(new SpotifyWebApiEndpoint.ResponseType("PagingObject[ArtistObject]", 200))
        );
        var topTracks = new SpotifyWebApiEndpoint(
                "endpoint-get-users-top-tracks",
                "Get a User's Top Tracks",
                topArtistsAndTracks.getLink(),
                "Get the current user’s top tracks based on calculated affinity.",
                "GET",
                "/me/top/tracks",
                parameters,
                null,
                responseDescriptionTracks,
                topArtistsAndTracks.getScopes(),
                topArtistsAndTracks.getNotes(),
                List.of(new SpotifyWebApiEndpoint.ResponseType("PagingObject[TrackObject]", 200))
        );

        category.getEndpoints().remove(topArtistsAndTracks.getId());
        category.getEndpoints().put(topArtists.getId(), topArtists);
        category.getEndpoints().put(topTracks.getId(), topTracks);

        topArtistsAndTracks.getScopes().stream()
                .map(apiDocumentation.getScopes()::getScope)
                .filter(Optional::isPresent)
                .forEach(scope -> {
                    scope.get().getEndpoints().removeIf(e -> topArtistsAndTracks.getId().equals(e.getEndpoint()));
                    scope.get().getEndpoints().add(new SpotifyScope.EndpointLink(topArtistsAndTracks.getLink(), "web-api", topArtists.getId()));
                    scope.get().getEndpoints().add(new SpotifyScope.EndpointLink(topArtistsAndTracks.getLink(), "web-api", topTracks.getId()));
                });
    }

    public static void splitReorderOrReplacePlaylistsTracksEndpoint(SpotifyWebApi apiDocumentation) {
        var category = apiDocumentation.getCategory("category-playlists")
                .orElseThrow(() -> new IllegalArgumentException("Can not find category-playlists"));

        var endpoint = category.getEndpoint("endpoint-reorder-or-replace-playlists-tracks")
                .orElseThrow(() -> new IllegalArgumentException("Can not find endpoint-reorder-or-replace-playlists-tracks"));

        var reorderParameterNames = List.of("Authorization", "Content-Type", "playlist_id", "range_start", "insert_before", "range_length", "snapshot_id");
        var replaceParameterNames = List.of("Authorization", "Content-Type", "playlist_id", "uris");

        var responseDescriptionParts = endpoint.getResponseDescription().split("\n\n");
        if (responseDescriptionParts.length != 3
            || !responseDescriptionParts[0].contains("reorder")
            || !responseDescriptionParts[1].contains("replace")
            || !responseDescriptionParts[2].contains("error")) {
            throw new IllegalArgumentException("Response description has changed, can not split endpoint-reorder-or-replace-playlists-tracks");
        }

        var reorderEndpoint = new SpotifyWebApiEndpoint(
                "endpoint-reorder-playlists-tracks",
                "Reorder items in a playlist",
                endpoint.getLink(),
                "Reorder an item or a group of items in a playlist.",
                endpoint.getHttpMethod(),
                endpoint.getPath(),
                endpoint.getParameters().stream().filter(p -> reorderParameterNames.contains(p.getName())).collect(Collectors.toList()),
                null,
                responseDescriptionParts[0] + "\n\n" + responseDescriptionParts[2],
                endpoint.getScopes(),
                endpoint.getNotes(),
                List.of(new SpotifyWebApiEndpoint.ResponseType("SnapshotIdObject", 200))
        );

        reorderEndpoint.getParameters().stream().filter(p -> "range_start".equals(p.getName())).findFirst().get().setRequired(true);
        reorderEndpoint.getParameters().stream().filter(p -> "insert_before".equals(p.getName())).findFirst().get().setRequired(true);

        var replaceEndpoint = new SpotifyWebApiEndpoint(
                "endpoint-replace-playlists-tracks",
                "Replace items in a playlist",
                endpoint.getLink(),
                "Replace all the items in a playlist, overwriting its existing items. This powerful request can be useful for replacing items, re-ordering existing items, or clearing the playlist.",
                endpoint.getHttpMethod(),
                endpoint.getPath(),
                endpoint.getParameters().stream().filter(p -> replaceParameterNames.contains(p.getName())).collect(Collectors.toList()),
                null,
            responseDescriptionParts[1] + "\n\n" + responseDescriptionParts[2],
                endpoint.getScopes(),
                endpoint.getNotes(),
                List.of(new SpotifyWebApiEndpoint.ResponseType("SnapshotIdObject", 201))
        );

        category.getEndpoints().remove(endpoint.getId());
        category.getEndpoints().put(reorderEndpoint.getId(), reorderEndpoint);
        category.getEndpoints().put(replaceEndpoint.getId(), replaceEndpoint);
    }
}
