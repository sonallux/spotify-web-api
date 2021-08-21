package de.sonallux.spotify.parser;

import de.sonallux.spotify.core.model.SpotifyWebApiObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.SortedMap;

@Slf4j
class ApiObjectFixes {

    static void fixApiObjects(SortedMap<String, SpotifyWebApiObject> objects) {
        fixObjectNames(objects);

        fixLinkedTrackObjectReferenceInTrackObject(objects);
        fixTracksTypeInPlaylistObject(objects);
        fixMissingTracksPropertyInAlbumObject(objects);
        fixEpisodesTypeInShowObject(objects);
        fixTracksPropertyInRecommendationsObject(objects);
    }

    private static void fixObjectNames(SortedMap<String, SpotifyWebApiObject> objects) {
        List.of("AlbumBase", "EpisodeBase", "ShowBase").forEach(oldName -> {
            var newName = oldName.replace("Base", "Object");
            var object = objects.remove(oldName);
            object.setId(null);//TODO: check if ok
            object.setName(newName);
            objects.put(newName, object);
        });

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
            .filter(p -> "tracks".equals(p.getName()) && "Object".equals(p.getType()))
            .findFirst().orElse(null);
        if (tracksProperty == null) {
            log.warn("PlaylistObject: wrong type of property tracks has been fixed");
        } else {
            tracksProperty.setType("PagingObject[PlaylistTrackObject]");
        }
    }

    private static void fixMissingTracksPropertyInAlbumObject(SortedMap<String, SpotifyWebApiObject> objects) {
        var tracksProperty = objects.get("AlbumObject")
            .getProperties().stream()
            .filter(p -> "tracks".equals(p.getName()) && "Object".equals(p.getType()))
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

    private static void fixTracksPropertyInRecommendationsObject(SortedMap<String, SpotifyWebApiObject> objects) {
        var tracksProperty = objects.get("RecommendationsObject")
            .getProperties().stream()
            .filter(p -> "tracks".equals(p.getName()) && "Array[SimplifiedTrackObject]".equals(p.getType()))
            .findFirst().orElse(null);
        if (tracksProperty == null){
            log.warn("RecommendationsObject: wrong type of tracks property has been fixed");
        } else {
            tracksProperty.setType("Array[TrackObject]");
            var newDescription = tracksProperty.getDescription()
                .replace(" (simplified)", "")
                .replace("object-simplifiedtrackobject", "object-trackobject");
            tracksProperty.setDescription(newDescription);
        }
    }
}
