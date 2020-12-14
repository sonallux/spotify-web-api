package de.sonallux.spotify.parser;

import de.sonallux.spotify.core.model.SpotifyObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

@Slf4j
class ApiObjectFixes {

    static void fixApiObjects(SortedMap<String, SpotifyObject> objects) {
        fixLinkedTrackObjectReferenceInTrackObject(objects);
        fixTracksTypeInPlaylistObject(objects);
        fixTracksTypeInAlbumObject(objects);
        fixEpisodesTypeInShowObject(objects);
        fixMissingDescriptionInPlaylistObject(objects);
    }

    private static void fixLinkedTrackObjectReferenceInTrackObject(SortedMap<String, SpotifyObject> objects) {
        var linkedFromProperty = objects.get("TrackObject")
                .getProperties().stream()
                .filter(p -> "linked_from".equals(p.getName()) && "".equals(p.getType()))
                .findFirst().orElse(null);
        if (linkedFromProperty == null) {
            log.warn("TrackObject: wrong type of property linked_from has been fixed");
        } else {
            linkedFromProperty.setType("LinkedTrackObject");
        }
    }

    private static void fixTracksTypeInPlaylistObject(SortedMap<String, SpotifyObject> objects) {
        var tracksProperty = objects.get("PlaylistObject")
            .getProperties().stream()
            .filter(p -> "tracks".equals(p.getName()) && "Array[PlaylistTrackObject]".equals(p.getType()))
            .findFirst().orElse(null);
        if (tracksProperty == null) {
            log.warn("PlaylistObject: wrong type of property tracks has been fixed");
        } else {
            tracksProperty.setType("PagingObject[PlaylistTrackObject]");
        }
    }

    private static void fixTracksTypeInAlbumObject(SortedMap<String, SpotifyObject> objects) {
        var tracksProperty = objects.get("AlbumObject")
            .getProperties().stream()
            .filter(p -> "tracks".equals(p.getName()) && "Array[SimplifiedTrackObject]".equals(p.getType()))
            .findFirst().orElse(null);
        if (tracksProperty == null) {
            log.warn("AlbumObject: wrong type of property tracks has been fixed");
        } else {
            tracksProperty.setType("PagingObject[SimplifiedTrackObject]");
        }
    }

    private static void fixEpisodesTypeInShowObject(SortedMap<String, SpotifyObject> objects) {
        var tracksProperty = objects.get("ShowObject")
            .getProperties().stream()
            .filter(p -> "episodes".equals(p.getName()) && "Array[SimplifiedEpisodeObject]".equals(p.getType()))
            .findFirst().orElse(null);
        if (tracksProperty == null) {
            log.warn("ShowObject: wrong type of property episodes has been fixed");
        } else {
            tracksProperty.setType("PagingObject[SimplifiedEpisodeObject]");
        }
    }

    private static void fixMissingDescriptionInPlaylistObject(SortedMap<String, SpotifyObject> objects) {
        var playlistObject = objects.get("PlaylistObject");
        var descriptionProperty = playlistObject.getProperties().stream()
            .filter(p -> "description".equals(p.getName()))
            .findFirst();
        if (descriptionProperty.isPresent()) {
            log.warn("PlaylistObject: missing description property has been added");
        } else {
            playlistObject.addProperty(new SpotifyObject.Property("description", "String"));
        }
    }

    static List<SpotifyObject> getMissingObjects() {
        var objects = new ArrayList<SpotifyObject>();

        //AlbumsObject
        objects.add(new SpotifyObject("AlbumsObject")
            .addProperty(new SpotifyObject.Property("albums", "Array[AlbumObject]")));

        //ArtistsObject
        objects.add(new SpotifyObject("ArtistsObject")
            .addProperty(new SpotifyObject.Property("artists", "Array[ArtistObject]")));

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
        objects.add(new SpotifyObject("AudioFeaturesArrayObject")
            .addProperty(new SpotifyObject.Property("audio_features", "Array[AudioFeaturesObject]")));

        //CategoriesObject
        objects.add(new SpotifyObject("CategoriesObject")
            .addProperty(new SpotifyObject.Property("categories", "PagingObject[CategoryObject]")));

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
        objects.add(new SpotifyObject("EpisodesObject")
            .addProperty(new SpotifyObject.Property("episodes", "Array[EpisodeObject]")));

        //ErrorDetailsObject
        objects.add(new SpotifyObject("ErrorDetailsObject")
            .addProperty(new SpotifyObject.Property("status", "Integer", "The HTTP status code that is also returned in the response header."))
            .addProperty(new SpotifyObject.Property("message", "String", "A short description of the cause of the error."))
        );

        //ErrorResponseObject
        objects.add(new SpotifyObject("ErrorResponseObject")
            .addProperty(new SpotifyObject.Property("error", "ErrorDetailsObject")));

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
        objects.add(new SpotifyObject("FollowingArtistsObject")
            .addProperty(new SpotifyObject.Property("artists", "CursorPagingObject[ArtistObject]")));

        //GenreSeedsObject
        objects.add(new SpotifyObject("GenreSeedsObject")
            .addProperty(new SpotifyObject.Property("genres", "Array[String]")));

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
        objects.add(new SpotifyObject("PlaylistPagingObject")
            .addProperty(new SpotifyObject.Property("playlists", "PagingObject[SimplifiedPlaylistObject]")));

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
        objects.add(new SpotifyObject("ShowsObject")
            .addProperty(new SpotifyObject.Property("shows", "Array[SimplifiedShowObject]")));

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
        objects.add(new SpotifyObject("SnapshotIdObject")
            .addProperty(new SpotifyObject.Property("snapshot_id", "String", "The snapshot_id can be used to identify your playlist version in future requests.")));

        //TracksObject
        objects.add(new SpotifyObject("TracksObject")
            .addProperty(new SpotifyObject.Property("tracks", "Array[TrackObject]")));

        return objects;
    }
}
