package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-player">Player API</a>
 */
public interface PlayerApi {

    /**
     * <h3>Add an item to queue</h3>
     * <p>Add an item to the end of the user's current playback queue.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param uri <p>The uri of the item to add to the queue. Must be a track or an episode uri.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-add-to-queue">Add an item to queue</a>
     */
    @POST("/me/player/queue")
    Call<Void> addToQueue(@Query("uri") String uri);

    /**
     * <h3>Add an item to queue</h3>
     * <p>Add an item to the end of the user's current playback queue.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param uri <p>The uri of the item to add to the queue. Must be a track or an episode uri.</p>
     * @param device_id <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-add-to-queue">Add an item to queue</a>
     */
    @POST("/me/player/queue")
    Call<Void> addToQueue(@Query("uri") String uri, @Query("device_id") String device_id);

    /**
     * <h3>Get a User's Available Devices</h3>
     * <p>Get information about a user's available devices.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-playback-state</code>
     *
     * @return <p>A successful request will return a <code>200 OK</code> response code with a json payload that contains the device objects (see below).
     *         When no available devices are found, the request will return a 200 OK response with an empty devices list.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-a-users-available-devices">Get a User's Available Devices</a>
     */
    @GET("/me/player/devices")
    Call<Devices> getUsersAvailableDevices();

    /**
     * <h3>Get Information About The User's Current Playback</h3>
     * <p>Get information about the user's current playback state, including track or episode, progress, and active device.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-playback-state</code>
     *
     * @return <p>A successful request will return a <code>200 OK</code> response code with a json payload that contains information about the current playback. The information returned is for the last known state, which means an inactive device could be returned if it was the last one to execute playback.
     *         When no available devices are found, the request will return a <code>200 OK</code> response but with no data populated.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a>
     */
    @GET("/me/player")
    Call<CurrentlyPlayingContext> getInformationAboutUsersCurrentPlayback();

    /**
     * <h3>Get Information About The User's Current Playback</h3>
     * <p>Get information about the user's current playback state, including track or episode, progress, and active device.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-playback-state</code>
     *
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>A successful request will return a <code>200 OK</code> response code with a json payload that contains information about the current playback. The information returned is for the last known state, which means an inactive device could be returned if it was the last one to execute playback.
     *         When no available devices are found, the request will return a <code>200 OK</code> response but with no data populated.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a>
     */
    @GET("/me/player")
    Call<CurrentlyPlayingContext> getInformationAboutUsersCurrentPlayback(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get Current User's Recently Played Tracks</h3>
     * <p>Get tracks from the current user's recently played tracks. <em>Note: Currently doesn't support podcast episodes.</em></p>
     *
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="#play-history-object">play history objects</a> (wrapped in a <a href="#cursor-based-paging-object">cursor-based paging object</a>) in JSON format. The play history items each contain the context the track was played from (e.g. playlist, album), the date and time the track was played, and a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedtrackobject">track object (simplified)</a>. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     *         <p>If private session is enabled the response will be a <code>204 NO CONTENT</code> with an empty payload.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-recently-played">Get Current User's Recently Played Tracks</a>
     */
    @GET("/me/player/recently-played")
    Call<CursorPaging<PlayHistory>> getRecentlyPlayed();

    /**
     * <h3>Get Current User's Recently Played Tracks</h3>
     * <p>Get tracks from the current user's recently played tracks. <em>Note: Currently doesn't support podcast episodes.</em></p>
     *
     * @param queryParameters <p>A map of optional query parameters</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an array of <a href="#play-history-object">play history objects</a> (wrapped in a <a href="#cursor-based-paging-object">cursor-based paging object</a>) in JSON format. The play history items each contain the context the track was played from (e.g. playlist, album), the date and time the track was played, and a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedtrackobject">track object (simplified)</a>. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     *         <p>If private session is enabled the response will be a <code>204 NO CONTENT</code> with an empty payload.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-recently-played">Get Current User's Recently Played Tracks</a>
     */
    @GET("/me/player/recently-played")
    Call<CursorPaging<PlayHistory>> getRecentlyPlayed(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get the User's Currently Playing Track</h3>
     * <p>Get the object currently being played on the user's Spotify account.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-currently-playing, user-read-playback-state</code>
     *
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>.</p>
     * @return <p>A successful request will return a <code>200 OK</code> response code with a json payload that contains information about the currently playing track or episode and its context (see below). The information returned is for the last known state, which means an inactive device could be returned if it was the last one to execute playback.</p>
     *         <p>When no available devices are found, the request will return a <code>200 OK</code> response but with no data populated.</p>
     *         <p>When no track is currently playing, the request will return a <code>204 NO CONTENT</code> response with no payload.</p>
     *         <p>If private session is enabled the response will be a <code>204 NO CONTENT</code> with an empty payload.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-the-users-currently-playing-track">Get the User's Currently Playing Track</a>
     */
    @GET("/me/player/currently-playing")
    Call<CurrentlyPlaying> getUsersCurrentlyPlayingTrack(@Query("market") String market);

    /**
     * <h3>Get the User's Currently Playing Track</h3>
     * <p>Get the object currently being played on the user's Spotify account.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-currently-playing, user-read-playback-state</code>
     *
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>.</p>
     * @param additional_types <p>A comma-separated list of item types that your client supports besides the default <code>track</code> type. Valid types are: <code>track</code> and <code>episode</code>. An unsupported type in the response is expected to be represented as <code>null</code> value in the <code>item</code> field. <strong>Note</strong> : This parameter was introduced to allow existing clients to maintain their current behaviour and might be deprecated in the future. In addition to providing this parameter, make sure that your client properly handles cases of new types in the future by checking against the <code>currently_playing_type</code> field.</p>
     * @return <p>A successful request will return a <code>200 OK</code> response code with a json payload that contains information about the currently playing track or episode and its context (see below). The information returned is for the last known state, which means an inactive device could be returned if it was the last one to execute playback.</p>
     *         <p>When no available devices are found, the request will return a <code>200 OK</code> response but with no data populated.</p>
     *         <p>When no track is currently playing, the request will return a <code>204 NO CONTENT</code> response with no payload.</p>
     *         <p>If private session is enabled the response will be a <code>204 NO CONTENT</code> with an empty payload.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-the-users-currently-playing-track">Get the User's Currently Playing Track</a>
     */
    @GET("/me/player/currently-playing")
    Call<CurrentlyPlaying> getUsersCurrentlyPlayingTrack(@Query("market") String market, @Query("additional_types") String additional_types);

    /**
     * <h3>Pause a User's Playback</h3>
     * <p>Pause playback on the user's account.</p>
     *
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-pause-a-users-playback">Pause a User's Playback</a>
     */
    @PUT("/me/player/pause")
    Call<Void> pauseUsersPlayback();

    /**
     * <h3>Pause a User's Playback</h3>
     * <p>Pause playback on the user's account.</p>
     *
     * @param device_id <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-pause-a-users-playback">Pause a User's Playback</a>
     */
    @PUT("/me/player/pause")
    Call<Void> pauseUsersPlayback(@Query("device_id") String device_id);

    /**
     * <h3>Seek To Position In Currently Playing Track</h3>
     * <p>Seeks to the given position in the user's currently playing track.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param position_ms <p>The position in milliseconds to seek to. Must be a positive number. Passing in a position that is greater than the length of the track will cause the player to start playing the next song.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-seek-to-position-in-currently-playing-track">Seek To Position In Currently Playing Track</a>
     */
    @PUT("/me/player/seek")
    Call<Void> seekToPositionInCurrentlyPlayingTrack(@Query("position_ms") Integer position_ms);

    /**
     * <h3>Seek To Position In Currently Playing Track</h3>
     * <p>Seeks to the given position in the user's currently playing track.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param position_ms <p>The position in milliseconds to seek to. Must be a positive number. Passing in a position that is greater than the length of the track will cause the player to start playing the next song.</p>
     * @param device_id <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-seek-to-position-in-currently-playing-track">Seek To Position In Currently Playing Track</a>
     */
    @PUT("/me/player/seek")
    Call<Void> seekToPositionInCurrentlyPlayingTrack(@Query("position_ms") Integer position_ms, @Query("device_id") String device_id);

    /**
     * <h3>Set Repeat Mode On User’s Playback</h3>
     * <p>Set the repeat mode for the user's playback. Options are repeat-track, repeat-context, and off.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param state <p><strong>track</strong> , <strong>context</strong> or <strong>off</strong> .<br><strong>track</strong> will repeat the current track.<br><strong>context</strong> will repeat the current context.<br><strong>off</strong> will turn repeat off.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-set-repeat-mode-on-users-playback">Set Repeat Mode On User’s Playback</a>
     */
    @PUT("/me/player/repeat")
    Call<Void> setRepeatModeOnUsersPlayback(@Query("state") String state);

    /**
     * <h3>Set Repeat Mode On User’s Playback</h3>
     * <p>Set the repeat mode for the user's playback. Options are repeat-track, repeat-context, and off.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param state <p><strong>track</strong> , <strong>context</strong> or <strong>off</strong> .<br><strong>track</strong> will repeat the current track.<br><strong>context</strong> will repeat the current context.<br><strong>off</strong> will turn repeat off.</p>
     * @param device_id <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-set-repeat-mode-on-users-playback">Set Repeat Mode On User’s Playback</a>
     */
    @PUT("/me/player/repeat")
    Call<Void> setRepeatModeOnUsersPlayback(@Query("state") String state, @Query("device_id") String device_id);

    /**
     * <h3>Set Volume For User's Playback</h3>
     * <p>Set the volume for the user's current playback device.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param volume_percent <p>The volume to set. Must be a value from 0 to 100 inclusive.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-set-volume-for-users-playback">Set Volume For User's Playback</a>
     */
    @PUT("/me/player/volume")
    Call<Void> setVolumeForUsersPlayback(@Query("volume_percent") Integer volume_percent);

    /**
     * <h3>Set Volume For User's Playback</h3>
     * <p>Set the volume for the user's current playback device.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param volume_percent <p>The volume to set. Must be a value from 0 to 100 inclusive.</p>
     * @param device_id <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-set-volume-for-users-playback">Set Volume For User's Playback</a>
     */
    @PUT("/me/player/volume")
    Call<Void> setVolumeForUsersPlayback(@Query("volume_percent") Integer volume_percent, @Query("device_id") String device_id);

    /**
     * <h3>Skip User’s Playback To Next Track</h3>
     * <p>Skips to next track in the user's queue.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-skip-users-playback-to-next-track">Skip User’s Playback To Next Track</a>
     */
    @POST("/me/player/next")
    Call<Void> skipUsersPlaybackToNextTrack();

    /**
     * <h3>Skip User’s Playback To Next Track</h3>
     * <p>Skips to next track in the user's queue.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param device_id <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-skip-users-playback-to-next-track">Skip User’s Playback To Next Track</a>
     */
    @POST("/me/player/next")
    Call<Void> skipUsersPlaybackToNextTrack(@Query("device_id") String device_id);

    /**
     * <h3>Skip User’s Playback To Previous Track</h3>
     * <p>Skips to previous track in the user's queue.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-skip-users-playback-to-previous-track">Skip User’s Playback To Previous Track</a>
     */
    @POST("/me/player/previous")
    Call<Void> skipUsersPlaybackToPreviousTrack();

    /**
     * <h3>Skip User’s Playback To Previous Track</h3>
     * <p>Skips to previous track in the user's queue.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param device_id <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-skip-users-playback-to-previous-track">Skip User’s Playback To Previous Track</a>
     */
    @POST("/me/player/previous")
    Call<Void> skipUsersPlaybackToPreviousTrack(@Query("device_id") String device_id);

    /**
     * <h3>Start/Resume a User's Playback</h3>
     * <p>Start a new context or resume current playback on the user's active device.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-start-a-users-playback">Start/Resume a User's Playback</a>
     */
    @PUT("/me/player/play")
    Call<Void> startUsersPlayback();

    /**
     * <h3>Start/Resume a User's Playback</h3>
     * <p>Start a new context or resume current playback on the user's active device.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param requestBody <p>The request body</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-start-a-users-playback">Start/Resume a User's Playback</a>
     */
    @PUT("/me/player/play")
    Call<Void> startUsersPlayback(@Body StartUsersPlaybackRequest requestBody);

    /**
     * <h3>Start/Resume a User's Playback</h3>
     * <p>Start a new context or resume current playback on the user's active device.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param device_id <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-start-a-users-playback">Start/Resume a User's Playback</a>
     */
    @PUT("/me/player/play")
    Call<Void> startUsersPlayback(@Query("device_id") String device_id);

    /**
     * <h3>Start/Resume a User's Playback</h3>
     * <p>Start a new context or resume current playback on the user's active device.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param device_id <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     * @param requestBody <p>The request body</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-start-a-users-playback">Start/Resume a User's Playback</a>
     */
    @PUT("/me/player/play")
    Call<Void> startUsersPlayback(@Query("device_id") String device_id, @Body StartUsersPlaybackRequest requestBody);

    /**
     * <h3>Toggle Shuffle For User’s Playback</h3>
     * <p>Toggle shuffle on or off for user's playback.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param state <p><strong>true</strong> : Shuffle user's playback.<br><strong>false</strong> : Do not shuffle user's playback.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-toggle-shuffle-for-users-playback">Toggle Shuffle For User’s Playback</a>
     */
    @PUT("/me/player/shuffle")
    Call<Void> toggleShuffleForUsersPlayback(@Query("state") Boolean state);

    /**
     * <h3>Toggle Shuffle For User’s Playback</h3>
     * <p>Toggle shuffle on or off for user's playback.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param state <p><strong>true</strong> : Shuffle user's playback.<br><strong>false</strong> : Do not shuffle user's playback.</p>
     * @param device_id <p>The id of the device this command is targeting. If not supplied, the user's currently active device is the target.</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-toggle-shuffle-for-users-playback">Toggle Shuffle For User’s Playback</a>
     */
    @PUT("/me/player/shuffle")
    Call<Void> toggleShuffleForUsersPlayback(@Query("state") Boolean state, @Query("device_id") String device_id);

    /**
     * <h3>Transfer a User's Playback</h3>
     * <p>Transfer playback to a new device and determine if it should start playing.</p>
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     *
     * @param requestBody <p>the request body</p>
     * @return <p>A completed request will return a <code>204 NO CONTENT</code> response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a> endpoint to check that your issued command was handled correctly by the player.</p>
     *         <p>If the device is not found, the request will return <code>404 NOT FOUND</code> response code.</p>
     *         <p>If the user making the request is non-premium, a <code>403 FORBIDDEN</code> response code will be returned.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-transfer-a-users-playback">Transfer a User's Playback</a>
     */
    @PUT("/me/player")
    Call<Void> transferUsersPlayback(@Body TransferUsersPlaybackRequest requestBody);
}
