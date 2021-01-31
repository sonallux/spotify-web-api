package de.sonallux.spotify.api.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpotifyUri {
    private final Type type;
    private final String id;

    public boolean isArtist() {
        return type == Type.ARTIST;
    }

    public String getArtistId() throws SpotifyUriException {
        if (isArtist()) {
            return id;
        }
        throw new SpotifyUriException("SpotifyUri does not contain an artist id");
    }

    public boolean isAlbum() {
        return type == Type.ALBUM;
    }

    public String getAlbumId() throws SpotifyUriException {
        if (isAlbum()) {
            return id;
        }
        throw new SpotifyUriException("SpotifyUri does not contain an album id");
    }

    public boolean isEpisode() {
        return type == Type.EPISODE;
    }

    public String getEpisodeId() throws SpotifyUriException {
        if (isEpisode()) {
            return id;
        }
        throw new SpotifyUriException("SpotifyUri does not contain an episode id");
    }

    public boolean isPlaylist() {
        return type == Type.PLAYLIST;
    }

    public String getPlaylistId() throws SpotifyUriException {
        if (isPlaylist()) {
            return id;
        }
        throw new SpotifyUriException("SpotifyUri does not contain a playlist id");
    }

    public boolean isShow() {
        return type == Type.SHOW;
    }

    public String getShowId() throws SpotifyUriException {
        if (isShow()) {
            return id;
        }
        throw new SpotifyUriException("SpotifyUri does not contain a show id");
    }

    public boolean isTrack() {
        return type == Type.TRACK;
    }

    public String getTrackId() throws SpotifyUriException {
        if (isTrack()) {
            return id;
        }
        throw new SpotifyUriException("SpotifyUri does not contain a track id");
    }

    public boolean isUser() {
        return type == Type.USER;
    }

    public String getUserId() throws SpotifyUriException {
        if (isUser()) {
            return id;
        }
        throw new SpotifyUriException("SpotifyUri does not contain a user id");
    }

    public String toSpotifyUri() {
        return "spotify:" + type.name().toLowerCase() + ":" + id;
    }

    @Override
    public String toString() {
        return toSpotifyUri();
    }

    public static SpotifyUri parseUri(String string) throws SpotifyUriException {
        if (string == null || string.length() == 0) {
            throw new SpotifyUriException("Can not parse empty spotifyUri");
        }

        String[] parts = string.split(":");
        if (parts.length != 3) {
            throw new SpotifyUriException("SpotifyUri has wrong format: " + string);
        }
        if (!"spotify".equals(parts[0])) {
            throw new SpotifyUriException("SpotifyUri must start with 'spotify'");
        }
        try {
            var type = Type.get(parts[1]);
            return new SpotifyUri(type, parts[2]);
        } catch (IllegalArgumentException e) {
            throw new SpotifyUriException("Spotify URI has unknown type: " + parts[1]);
        }
    }

    @Getter
    public enum Type {
        ARTIST,
        ALBUM,
        EPISODE,
        PLAYLIST,
        SHOW,
        TRACK,
        USER;

        public static Type get(String name) {
            return Type.valueOf(name.toUpperCase());
        }
    }
}
