package de.sonallux.spotify.api.authentication;

/**
 * <a href="https://developer.spotify.com/documentation/general/guides/scopes">Authorization Scopes</a>
 */
public enum Scope {
    /**
     * Remote control playback of Spotify. This scope is currently available to Spotify iOS and Android SDKs.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#app-remote-control">app-remote-control</a>
     */
    APP_REMOTE_CONTROL("app-remote-control"),
    /**
     * Write access to a user's private playlists.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#playlist-modify-private">playlist-modify-private</a>
     */
    PLAYLIST_MODIFY_PRIVATE("playlist-modify-private"),
    /**
     * Write access to a user's public playlists.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#playlist-modify-public">playlist-modify-public</a>
     */
    PLAYLIST_MODIFY_PUBLIC("playlist-modify-public"),
    /**
     * Include collaborative playlists when requesting a user's playlists.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#playlist-read-collaborative">playlist-read-collaborative</a>
     */
    PLAYLIST_READ_COLLABORATIVE("playlist-read-collaborative"),
    /**
     * Read access to user's private playlists.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#playlist-read-private">playlist-read-private</a>
     */
    PLAYLIST_READ_PRIVATE("playlist-read-private"),
    /**
     * Control playback of a Spotify track. This scope is currently available to the Web Playback SDK. The user must have a Spotify Premium account.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#streaming">streaming</a>
     */
    STREAMING("streaming"),
    /**
     * Write access to user-provided images.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#ugc-image-upload">ugc-image-upload</a>
     */
    UGC_IMAGE_UPLOAD("ugc-image-upload"),
    /**
     * Write/delete access to the list of artists and other users that the user follows.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-follow-modify">user-follow-modify</a>
     */
    USER_FOLLOW_MODIFY("user-follow-modify"),
    /**
     * Read access to the list of artists and other users that the user follows.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-follow-read">user-follow-read</a>
     */
    USER_FOLLOW_READ("user-follow-read"),
    /**
     * Write/delete access to a user's "Your Music" library.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-library-modify">user-library-modify</a>
     */
    USER_LIBRARY_MODIFY("user-library-modify"),
    /**
     * Read access to a user's "Your Music" library.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-library-read">user-library-read</a>
     */
    USER_LIBRARY_READ("user-library-read"),
    /**
     * Write access to a user’s playback state
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-modify-playback-state">user-modify-playback-state</a>
     */
    USER_MODIFY_PLAYBACK_STATE("user-modify-playback-state"),
    /**
     * Read access to a user’s currently playing content.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-read-currently-playing">user-read-currently-playing</a>
     */
    USER_READ_CURRENTLY_PLAYING("user-read-currently-playing"),
    /**
     * Read access to user’s email address.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-read-email">user-read-email</a>
     */
    USER_READ_EMAIL("user-read-email"),
    /**
     * Read access to a user’s playback position in a content.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-read-playback-position">user-read-playback-position</a>
     */
    USER_READ_PLAYBACK_POSITION("user-read-playback-position"),
    /**
     * Read access to a user’s player state.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-read-playback-state">user-read-playback-state</a>
     */
    USER_READ_PLAYBACK_STATE("user-read-playback-state"),
    /**
     * Read access to user’s subscription details (type of user account).
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-read-private">user-read-private</a>
     */
    USER_READ_PRIVATE("user-read-private"),
    /**
     * Read access to a user’s recently played tracks.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-read-recently-played">user-read-recently-played</a>
     */
    USER_READ_RECENTLY_PLAYED("user-read-recently-played"),
    /**
     * Read access to a user's top artists and tracks.
     * @see <a href="https://developer.spotify.com/documentation/general/guides/scopes/#user-top-read">user-top-read</a>
     */
    USER_TOP_READ("user-top-read");

    private final String scopeName;

    Scope(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getName() {
        return scopeName;
    }
}
