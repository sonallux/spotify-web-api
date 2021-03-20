package de.sonallux.spotify.core;

import de.sonallux.spotify.core.model.SpotifyWebApiObject;

import java.util.List;

public class SpotifyWebApiObjectUtils {
    private static final List<String> BASE_OBJECT_PROPERTY_NAMES = List.of("id", "type", "href", "uri");
    public static final List<String> BASE_OBJECT_NAMES = List.of("AlbumObject", "ArtistObject", "EpisodeObject", "PlaylistObject", "ShowObject", "TrackObject", "UserObject");
    public static final String BASE_OBJECT_NAME = "BaseObject";
    public static final SpotifyWebApiObject SPOTIFY_BASE_OBJECT = new SpotifyWebApiObject(BASE_OBJECT_NAME)
        .addProperty(new SpotifyWebApiObject.Property("id", "String", "The [Spotify ID](https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids) for the object."))
        .addProperty(new SpotifyWebApiObject.Property("type", "String", "The object type."))
        .addProperty(new SpotifyWebApiObject.Property("href", "String", "A link to the Web API endpoint providing full details of the object."))
        .addProperty(new SpotifyWebApiObject.Property("uri", "String", "The [Spotify URI](https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids) for the object."));

    public static boolean isBaseObject(SpotifyWebApiObject object) {
        return object.getProperties().stream()
            .filter(p -> BASE_OBJECT_PROPERTY_NAMES.contains(p.getName()))
            .count() == BASE_OBJECT_PROPERTY_NAMES.size();
    }

    public static boolean removeBaseProperties(SpotifyWebApiObject object) {
        if (BASE_OBJECT_NAME.equals(object.getName()) || !isBaseObject(object)) {
            return false;
        }
        object.getProperties().removeIf(p -> BASE_OBJECT_PROPERTY_NAMES.contains(p.getName()));
        return true;
    }
}
