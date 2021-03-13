package de.sonallux.spotify.parser;

import de.sonallux.spotify.core.model.SpotifyWebApiObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

@Slf4j
class ApiObjectFixes {

    static void fixApiObjects(SortedMap<String, SpotifyWebApiObject> objects) {
        fixLinkedTrackObjectReferenceInTrackObject(objects);
        fixTracksTypeInPlaylistObject(objects);
        fixTracksTypeInAlbumObject(objects);
        fixEpisodesTypeInShowObject(objects);
    }

    private static void fixLinkedTrackObjectReferenceInTrackObject(SortedMap<String, SpotifyWebApiObject> objects) {
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

    private static void fixTracksTypeInPlaylistObject(SortedMap<String, SpotifyWebApiObject> objects) {
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

    private static void fixTracksTypeInAlbumObject(SortedMap<String, SpotifyWebApiObject> objects) {
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

    private static void fixEpisodesTypeInShowObject(SortedMap<String, SpotifyWebApiObject> objects) {
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

    static List<SpotifyWebApiObject> getMissingObjects() {
        var objects = new ArrayList<SpotifyWebApiObject>();

        //AlbumsObject
        objects.add(new SpotifyWebApiObject("AlbumsObject")
            .addProperty(new SpotifyWebApiObject.Property("albums", "Array[AlbumObject]")));

        //ArtistsObject
        objects.add(new SpotifyWebApiObject("ArtistsObject")
            .addProperty(new SpotifyWebApiObject.Property("artists", "Array[ArtistObject]")));

        //AudioAnalysisObject
        objects.add(new SpotifyWebApiObject("SectionObject", "https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#section-object")
            .addProperty(new SpotifyWebApiObject.Property("start", "Float", "The starting point (in seconds) of the section."))
            .addProperty(new SpotifyWebApiObject.Property("duration", "Float", "The duration (in seconds) of the section."))
            .addProperty(new SpotifyWebApiObject.Property("confidence", "Float", "The confidence, from 0.0 to 1.0, of the reliability of the section’s \"designation\"."))
            .addProperty(new SpotifyWebApiObject.Property("loudness", "Float", "The overall loudness of the section in decibels (dB). Loudness values are useful for comparing relative loudness of sections within tracks."))
            .addProperty(new SpotifyWebApiObject.Property("tempo", "Float", "The overall estimated tempo of the section in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration."))
            .addProperty(new SpotifyWebApiObject.Property("tempo_confidence", "Float"))
            .addProperty(new SpotifyWebApiObject.Property("key", "Integer"))
            .addProperty(new SpotifyWebApiObject.Property("key_confidence", "Float"))
            .addProperty(new SpotifyWebApiObject.Property("mode", "Integer"))
            .addProperty(new SpotifyWebApiObject.Property("mode_confidence", "Float"))
            .addProperty(new SpotifyWebApiObject.Property("time_signature", "Integer"))
            .addProperty(new SpotifyWebApiObject.Property("time_signature_confidence", "Float"))
        );
        objects.add(new SpotifyWebApiObject("SegmentObject", "https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#segment-object")
            .addProperty(new SpotifyWebApiObject.Property("start", "Float"))
            .addProperty(new SpotifyWebApiObject.Property("duration", "Float"))
            .addProperty(new SpotifyWebApiObject.Property("confidence", "Float"))
            .addProperty(new SpotifyWebApiObject.Property("loudness_start", "Float"))
            .addProperty(new SpotifyWebApiObject.Property("loudness_max", "Float"))
            .addProperty(new SpotifyWebApiObject.Property("loudness_max_time", "Float"))
            .addProperty(new SpotifyWebApiObject.Property("loudness_end", "Float"))
            .addProperty(new SpotifyWebApiObject.Property("pitches", "Array[Float]"))
            .addProperty(new SpotifyWebApiObject.Property("timbre", "Array[Float]"))
        );
        objects.add(new SpotifyWebApiObject("TimeIntervalObject", "https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#time-interval-object")
            .addProperty(new SpotifyWebApiObject.Property("start", "Float", "The starting point (in seconds) of the time interval."))
            .addProperty(new SpotifyWebApiObject.Property("duration", "Float", "The duration (in seconds) of the time interval."))
            .addProperty(new SpotifyWebApiObject.Property("confidence", "Float", "The confidence, from 0.0 to 1.0, of the reliability of the interval."))
        );
        objects.add(new SpotifyWebApiObject("AudioAnalysisObject", "https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#audio-analysis-object")
            .addProperty(new SpotifyWebApiObject.Property("bars", "Array[TimeIntervalObject]", "The time intervals of the bars throughout the track. A bar (or measure) is a segment of time defined as a given number of beats. Bar offsets also indicate downbeats, the first beat of the measure."))
            .addProperty(new SpotifyWebApiObject.Property("beats", "Array[TimeIntervalObject]", "The time intervals of beats throughout the track. A beat is the basic time unit of a piece of music; for example, each tick of a metronome. Beats are typically multiples of tatums."))
            .addProperty(new SpotifyWebApiObject.Property("sections", "Array[SectionObject]", "Sections are defined by large variations in rhythm or timbre, e.g. chorus, verse, bridge, guitar solo, etc. Each section contains its own descriptions of tempo, key, mode, time_signature, and loudness."))
            .addProperty(new SpotifyWebApiObject.Property("segments", "Array[SegmentObject]", "Audio segments attempts to subdivide a song into many segments, with each segment containing a roughly consistent sound throughout its duration."))
            .addProperty(new SpotifyWebApiObject.Property("tatums", "Array[TimeIntervalObject]", "A tatum represents the lowest regular pulse train that a listener intuitively infers from the timing of perceived musical events (segments)."))
        );

        //AudioFeaturesArrayObject
        objects.add(new SpotifyWebApiObject("AudioFeaturesArrayObject")
            .addProperty(new SpotifyWebApiObject.Property("audio_features", "Array[AudioFeaturesObject]")));

        //CategoriesObject
        objects.add(new SpotifyWebApiObject("CategoriesObject")
            .addProperty(new SpotifyWebApiObject.Property("categories", "PagingObject[CategoryObject]")));

        //EpisodesObject
        objects.add(new SpotifyWebApiObject("EpisodesObject")
            .addProperty(new SpotifyWebApiObject.Property("episodes", "Array[EpisodeObject]")));

        //ErrorResponseObject
        objects.add(new SpotifyWebApiObject("ErrorResponseObject")
            .addProperty(new SpotifyWebApiObject.Property("error", "ErrorObject")));

        //FeaturedPlaylistObject
        objects.add(new SpotifyWebApiObject("FeaturedPlaylistObject")
            .addProperty(new SpotifyWebApiObject.Property("message", "String"))
            .addProperty(new SpotifyWebApiObject.Property("playlists", "PagingObject[SimplifiedPlaylistObject]"))
        );

        //FollowingArtistsObject
        objects.add(new SpotifyWebApiObject("FollowingArtistsObject")
            .addProperty(new SpotifyWebApiObject.Property("artists", "CursorPagingObject[ArtistObject]")));

        //GenreSeedsObject
        objects.add(new SpotifyWebApiObject("GenreSeedsObject")
            .addProperty(new SpotifyWebApiObject.Property("genres", "Array[String]")));

        //MarketsObject
        objects.add(new SpotifyWebApiObject("MarketsObject")
            .addProperty(new SpotifyWebApiObject.Property("markets", "Array[String]")));

        //NewReleasesObject
        objects.add(new SpotifyWebApiObject("NewReleasesObject")
            //.addProperty(new SpotifyWebApiObject.Property("message", "String"))//Note: property is specified, but it is not returned
            .addProperty(new SpotifyWebApiObject.Property("albums", "PagingObject[SimplifiedAlbumObject]"))
        );

        //PlaylistPagingObject
        objects.add(new SpotifyWebApiObject("PlaylistPagingObject")
            .addProperty(new SpotifyWebApiObject.Property("playlists", "PagingObject[SimplifiedPlaylistObject]")));

        //SearchResponseObject
        objects.add(new SpotifyWebApiObject("SearchResponseObject")
            .addProperty(new SpotifyWebApiObject.Property("album", "PagingObject[SimplifiedAlbumObject]"))
            .addProperty(new SpotifyWebApiObject.Property("artist", "PagingObject[ArtistObject]"))
            .addProperty(new SpotifyWebApiObject.Property("playlist", "PagingObject[SimplifiedPlaylistObject]"))
            .addProperty(new SpotifyWebApiObject.Property("track", "PagingObject[TrackObject]"))
            .addProperty(new SpotifyWebApiObject.Property("show", "PagingObject[SimplifiedShowObject]"))
            .addProperty(new SpotifyWebApiObject.Property("episode", "PagingObject[SimplifiedEpisodeObject]"))
        );

        //ShowsObject
        objects.add(new SpotifyWebApiObject("ShowsObject")
            .addProperty(new SpotifyWebApiObject.Property("shows", "Array[SimplifiedShowObject]")));

        //SnapshotIdObject
        objects.add(new SpotifyWebApiObject("SnapshotIdObject")
            .addProperty(new SpotifyWebApiObject.Property("snapshot_id", "String", "The snapshot_id can be used to identify your playlist version in future requests.")));

        //TracksObject
        objects.add(new SpotifyWebApiObject("TracksObject")
            .addProperty(new SpotifyWebApiObject.Property("tracks", "Array[TrackObject]")));

        return objects;
    }
}
