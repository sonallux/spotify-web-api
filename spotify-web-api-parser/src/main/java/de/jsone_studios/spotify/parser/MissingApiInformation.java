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
class MissingApiInformation {

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

    static List<SpotifyObject> getMissingObjects() {
        var objects = new ArrayList<SpotifyObject>();

        //AlbumsObject
        objects.add(new SpotifyObject("AlbumsObject", List.of(new SpotifyObject.Property("albums", "Array[AlbumObject]"))));

        //ArtistsObject
        objects.add(new SpotifyObject("ArtistsObject", List.of(new SpotifyObject.Property("artists", "Array[ArtistObject]"))));

        //AudioAnalysisObject
        objects.add(new SpotifyObject("SectionObject", "https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#section-object")
                .addProperty(new SpotifyObject.Property("start", "Float", "The starting point (in seconds) of the section."))
                .addProperty(new SpotifyObject.Property("duration", "Float", "The duration (in seconds) of the section."))
                .addProperty(new SpotifyObject.Property("confidence", "Float", "The confidence, from 0.0 to 1.0, of the reliability of the section’s \"designation\"."))
                .addProperty(new SpotifyObject.Property("loudness", "Float", "The overall loudness of the section in decibels (dB). Loudness values are useful for comparing relative loudness of sections within tracks."))
                .addProperty(new SpotifyObject.Property("tempo", "Float", "The overall estimated tempo of the section in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration."))
                .addProperty(new SpotifyObject.Property("tempo_confidence", "Float"))
                .addProperty(new SpotifyObject.Property("key", "Integer"))
                .addProperty(new SpotifyObject.Property("key_confidence", "Float"))
                .addProperty(new SpotifyObject.Property("mode", "Integer"))
                .addProperty(new SpotifyObject.Property("mode_confidence", "Float"))
                .addProperty(new SpotifyObject.Property("time_signature", "Integer"))
                .addProperty(new SpotifyObject.Property("time_signature_confidence", "Float"))
        );
        objects.add(new SpotifyObject("SegmentObject", "https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#segment-object")
                .addProperty(new SpotifyObject.Property("start", "Float"))
                .addProperty(new SpotifyObject.Property("duration", "Float"))
                .addProperty(new SpotifyObject.Property("confidence", "Float"))
                .addProperty(new SpotifyObject.Property("loudness_start", "Float"))
                .addProperty(new SpotifyObject.Property("loudness_max", "Float"))
                .addProperty(new SpotifyObject.Property("loudness_max_time", "Float"))
                .addProperty(new SpotifyObject.Property("loudness_end", "Float"))
                .addProperty(new SpotifyObject.Property("pitches", "Array[Float]"))
                .addProperty(new SpotifyObject.Property("timbre", "Array[Float]"))
        );
        objects.add(new SpotifyObject("TimeIntervalObject", "https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#time-interval-object")
                .addProperty(new SpotifyObject.Property("start", "Float", "The starting point (in seconds) of the time interval."))
                .addProperty(new SpotifyObject.Property("duration", "Float", "The duration (in seconds) of the time interval."))
                .addProperty(new SpotifyObject.Property("confidence", "Float", "The confidence, from 0.0 to 1.0, of the reliability of the interval."))
        );
        objects.add(new SpotifyObject("AudioAnalysisObject", "https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#audio-analysis-object")
                .addProperty(new SpotifyObject.Property("bars", "Array[TimeIntervalObject]", "The time intervals of the bars throughout the track. A bar (or measure) is a segment of time defined as a given number of beats. Bar offsets also indicate downbeats, the first beat of the measure."))
                .addProperty(new SpotifyObject.Property("beats", "Array[TimeIntervalObject]", "The time intervals of beats throughout the track. A beat is the basic time unit of a piece of music; for example, each tick of a metronome. Beats are typically multiples of tatums."))
                .addProperty(new SpotifyObject.Property("sections", "Array[SectionObject]", "Sections are defined by large variations in rhythm or timbre, e.g. chorus, verse, bridge, guitar solo, etc. Each section contains its own descriptions of tempo, key, mode, time_signature, and loudness."))
                .addProperty(new SpotifyObject.Property("segments", "Array[SegmentObject]", "Audio segments attempts to subdivide a song into many segments, with each segment containing a roughly consistent sound throughout its duration."))
                .addProperty(new SpotifyObject.Property("tatums", "Array[TimeIntervalObject]", "A tatum represents the lowest regular pulse train that a listener intuitively infers from the timing of perceived musical events (segments)."))
        );

        //AudioFeaturesArrayObject
        objects.add(new SpotifyObject("AudioFeaturesArrayObject", List.of(new SpotifyObject.Property("audio_features", "Array[AudioFeaturesObject]"))));

        //CategoriesObject
        objects.add(new SpotifyObject("CategoriesObject", List.of(new SpotifyObject.Property("categories", "PagingObject[CategoryObject]"))));

        //CopyrightObject
        objects.add(new SpotifyObject("CopyrightObject")
                .addProperty(new SpotifyObject.Property("text", "String", "The copyright text for this album."))
                .addProperty(new SpotifyObject.Property("type", "String", "The type of copyright: C = the copyright, P = the sound recording (performance) copyright."))
        );

        //CurrentPlaybackObject
        objects.add(new SpotifyObject("CurrentPlaybackObject")
                .addProperty(new SpotifyObject.Property("timestamp", "Timestamp"))
                .addProperty(new SpotifyObject.Property("device", "DeviceObject"))
                .addProperty(new SpotifyObject.Property("progress_ms", "Integer"))
                .addProperty(new SpotifyObject.Property("is_playing", "Boolean"))
                .addProperty(new SpotifyObject.Property("currently_playing_type", "String"))
                .addProperty(new SpotifyObject.Property("item", "TrackObject | EpisodeObject"))
                .addProperty(new SpotifyObject.Property("shuffle_state", "Boolean"))
                .addProperty(new SpotifyObject.Property("repeat_state", "String"))
                .addProperty(new SpotifyObject.Property("context", "ContextObject"))
        );

        //EpisodesObject
        objects.add(new SpotifyObject("EpisodesObject", List.of(new SpotifyObject.Property("episodes", "Array[EpisodeObject]"))));

        //ErrorDetailsObject
        objects.add(new SpotifyObject("ErrorDetailsObject")
                .addProperty(new SpotifyObject.Property("status", "Integer", "The HTTP status code that is also returned in the response header."))
                .addProperty(new SpotifyObject.Property("message", "String", "A short description of the cause of the error."))
        );

        //ErrorResponseObject
        objects.add(new SpotifyObject("ErrorResponseObject", List.of(new SpotifyObject.Property("error", "ErrorDetailsObject"))));

        //ExternalUrlObject
        objects.add(new SpotifyObject("ExternalUrlObject"));

        //FeaturedPlaylistObject
        objects.add(new SpotifyObject("FeaturedPlaylistObject")
                .addProperty(new SpotifyObject.Property("message", "String"))
                .addProperty(new SpotifyObject.Property("playlists", "PagingObject[SimplifiedPlaylistObject]"))
        );

        //FollowersObject
        objects.add(new SpotifyObject("FollowersObject", "https://developer.spotify.com/documentation/web-api/reference/object-model/#followers-object")
                .addProperty(new SpotifyObject.Property("href", "String", "A link to the Web API endpoint providing full details of the followers; null if not available. Please note that this will always be set to null, as the Web API does not support it at the moment."))
                .addProperty(new SpotifyObject.Property("total", "Integer", "The total number of followers."))
        );

        //FollowingArtistsObject
        objects.add(new SpotifyObject("FollowingArtistsObject", List.of(new SpotifyObject.Property("artists", "CursorPagingObject[ArtistObject]"))));

        //GenreSeedsObject
        objects.add(new SpotifyObject("GenreSeedsObject", List.of(new SpotifyObject.Property("genres", "Array[String]"))));

        //ImageObject
        objects.add(new SpotifyObject("ImageObject", "https://developer.spotify.com/documentation/web-api/reference/object-model/#image-object")
                .addProperty(new SpotifyObject.Property("height", "Integer", "The image height in pixels. If unknown: null or not returned."))
                .addProperty(new SpotifyObject.Property("url", "String", "The source URL of the image."))
                .addProperty(new SpotifyObject.Property("width", "Integer", "The image width in pixels. If unknown: null or not returned."))
        );

        //LinkedTrackObject
        objects.add(new SpotifyObject("LinkedTrackObject")
                .addProperty(new SpotifyObject.Property("external_urls", "ExternalUrlObject", "Known external URLs for this track."))
                .addProperty(new SpotifyObject.Property("href", "String", "A link to the Web API endpoint providing full details of the track."))
                .addProperty(new SpotifyObject.Property("id", "String", "The Spotify ID for the track."))
                .addProperty(new SpotifyObject.Property("type", "String", "The object type: \"track\"."))
                .addProperty(new SpotifyObject.Property("uri", "String", "The Spotify URI for the track."))
        );

        //NewReleasesObject
        objects.add(new SpotifyObject("NewReleasesObject")
                //.addProperty(new SpotifyObject.Property("message", "String"))//Note: property is specified, but it is not returned
                .addProperty(new SpotifyObject.Property("albums", "PagingObject[SimplifiedAlbumObject]"))
        );

        //PlaylistPagingObject
        objects.add(new SpotifyObject("PlaylistPagingObject", List.of(new SpotifyObject.Property("playlists", "PagingObject[SimplifiedPlaylistObject]"))));

        //PlaylistTracksInfo
        objects.add(new SpotifyObject("PlaylistTracksInfo")
                .addProperty(new SpotifyObject.Property("href", "String"))
                .addProperty(new SpotifyObject.Property("total", "Integer"))
        );

        //SearchResponseObject
        objects.add(new SpotifyObject("SearchResponseObject")
                .addProperty(new SpotifyObject.Property("album", "PagingObject[SimplifiedAlbumObject]"))
                .addProperty(new SpotifyObject.Property("artist", "PagingObject[ArtistObject]"))
                .addProperty(new SpotifyObject.Property("playlist", "PagingObject[SimplifiedPlaylistObject]"))
                .addProperty(new SpotifyObject.Property("track", "PagingObject[TrackObject]"))
                .addProperty(new SpotifyObject.Property("show", "PagingObject[SimplifiedShowObject]"))
                .addProperty(new SpotifyObject.Property("episode", "PagingObject[SimplifiedEpisodeObject]"))
        );

        //ShowsObject
        objects.add(new SpotifyObject("ShowsObject", List.of(new SpotifyObject.Property("shows", "Array[SimplifiedShowObject]"))));

        //SimplifiedArtistObject
        objects.add(new SpotifyObject("SimplifiedArtistObject")
                .addProperty(new SpotifyObject.Property("external_urls", "ExternalUrlObject", "Known external URLs for this artist."))
                .addProperty(new SpotifyObject.Property("href", "String", "A link to the Web API endpoint providing full details of the artist."))
                .addProperty(new SpotifyObject.Property("id", "String", "The Spotify ID for the artist."))
                .addProperty(new SpotifyObject.Property("name", "String", "The name of the artist."))
                .addProperty(new SpotifyObject.Property("type", "String", "The object type: \"artist\""))
                .addProperty(new SpotifyObject.Property("uri", "String", "The Spotify URI for the artist."))
        );

        //SimplifiedPlaylistObject
        objects.add(new SpotifyObject("SimplifiedPlaylistObject")
                .addProperty(new SpotifyObject.Property("collaborative", "Boolean"))
                .addProperty(new SpotifyObject.Property("description", "String"))
                .addProperty(new SpotifyObject.Property("external_urls", "ExternalUrlObject"))
                .addProperty(new SpotifyObject.Property("href", "String"))
                .addProperty(new SpotifyObject.Property("id", "String"))
                .addProperty(new SpotifyObject.Property("images", "Array[ImageObject]"))
                .addProperty(new SpotifyObject.Property("name", "String"))
                .addProperty(new SpotifyObject.Property("owner", "PublicUserObject"))
                .addProperty(new SpotifyObject.Property("public", "Boolean"))
                .addProperty(new SpotifyObject.Property("snapshot_id", "String"))
                .addProperty(new SpotifyObject.Property("tracks", "PlaylistTracksInfo"))
                .addProperty(new SpotifyObject.Property("type", "String"))
                .addProperty(new SpotifyObject.Property("uri", "String"))
        );

        //SnapshotIdObject
        objects.add(new SpotifyObject("SnapshotIdObject", List.of(new SpotifyObject.Property("snapshot_id", "String", "The snapshot_id can be used to identify your playlist version in future requests."))));

        //TrackRestrictionObject
        objects.add(new SpotifyObject("TrackRestrictionObject", List.of(new SpotifyObject.Property("reason", "String", "The reason why a track is not available."))));

        //TracksObject
        objects.add(new SpotifyObject("TracksObject", List.of(new SpotifyObject.Property("tracks", "Array[TrackObject]"))));

        return objects;
    }
}
