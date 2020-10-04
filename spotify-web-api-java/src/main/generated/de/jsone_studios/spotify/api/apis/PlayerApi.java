package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-player">Player API</a>
 */
public interface PlayerApi {

    /**
     * <h3>Add an item to queue</h3>
     * Add an item to the end of the user’s current playback queue.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param uri The uri of the item to add to the queue. Must be a track or an episode uri.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-add-to-queue">Add an item to queue</a>
     */
    @POST("/me/player/queue")
    Call<Void> addToQueue(@Query("uri") String uri);

    /**
     * <h3>Add an item to queue</h3>
     * Add an item to the end of the user’s current playback queue.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param uri The uri of the item to add to the queue. Must be a track or an episode uri.
     * @param device_id The id of the device this command is targeting. If not supplied, the user’s currently active device is the target.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-add-to-queue">Add an item to queue</a>
     */
    @POST("/me/player/queue")
    Call<Void> addToQueue(@Query("uri") String uri, @Query("device_id") String device_id);

    /**
     * <h3>Get a User's Available Devices</h3>
     * Get information about a user’s available devices.
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-playback-state</code>
     * 
     * @return A successful request will return a 200 OK response code with a json payload that contains the device objects (see below). When no available devices are found, the request will return a 200 OK response with an empty devices list.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-a-users-available-devices">Get a User's Available Devices</a>
     */
    @GET("/me/player/devices")
    Call<Devices> getUsersAvailableDevices();

    /**
     * <h3>Get Information About The User's Current Playback</h3>
     * Get information about the user’s current playback state, including track or episode, progress, and active device.
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-playback-state</code>
     * 
     * @return A successful request will return a 200 OK response code with a json payload that contains information about the current playback. The information returned is for the last known state, which means an inactive device could be returned if it was the last one to execute playback. When no available devices are found, the request will return a 200 OK response but with no data populated.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a>
     */
    @GET("/me/player")
    Call<CurrentPlayback> getInformationAboutUsersCurrentPlayback();

    /**
     * <h3>Get Information About The User's Current Playback</h3>
     * Get information about the user’s current playback state, including track or episode, progress, and active device.
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-playback-state</code>
     * 
     * @param queryParameters A map of optional query parameters
     * @return A successful request will return a 200 OK response code with a json payload that contains information about the current playback. The information returned is for the last known state, which means an inactive device could be returned if it was the last one to execute playback. When no available devices are found, the request will return a 200 OK response but with no data populated.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-information-about-the-users-current-playback">Get Information About The User's Current Playback</a>
     */
    @GET("/me/player")
    Call<CurrentPlayback> getInformationAboutUsersCurrentPlayback(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get Current User's Recently Played Tracks</h3>
     * Get tracks from the current user’s recently played tracks. Note: Currently doesn’t support podcast episodes.
     * 
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of play history objects (wrapped in a cursor-based paging object) in JSON format. The play history items each contain the context the track was played from (e.g. playlist, album), the date and time the track was played, and a track object (simplified). On error, the header status code is an error code and the response body contains an error object. If private session is enabled the response will be a 204 NO CONTENT with an empty payload.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-recently-played">Get Current User's Recently Played Tracks</a>
     */
    @GET("/me/player/recently-played")
    Call<CursorPaging<PlayHistory>> getRecentlyPlayed();

    /**
     * <h3>Get Current User's Recently Played Tracks</h3>
     * Get tracks from the current user’s recently played tracks. Note: Currently doesn’t support podcast episodes.
     * 
     * @param queryParameters A map of optional query parameters
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an array of play history objects (wrapped in a cursor-based paging object) in JSON format. The play history items each contain the context the track was played from (e.g. playlist, album), the date and time the track was played, and a track object (simplified). On error, the header status code is an error code and the response body contains an error object. If private session is enabled the response will be a 204 NO CONTENT with an empty payload.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-recently-played">Get Current User's Recently Played Tracks</a>
     */
    @GET("/me/player/recently-played")
    Call<CursorPaging<PlayHistory>> getRecentlyPlayed(@QueryMap java.util.Map<String, Object> queryParameters);

    /**
     * <h3>Get the User's Currently Playing Track</h3>
     * Get the object currently being played on the user’s Spotify account.
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-currently-playing, user-read-playback-state</code>
     * 
     * @param market An ISO 3166-1 alpha-2 country code or the string from_token. Provide this parameter if you want to apply Track Relinking.
     * @return A successful request will return a 200 OK response code with a json payload that contains information about the currently playing track or episode and its context (see below). The information returned is for the last known state, which means an inactive device could be returned if it was the last one to execute playback. When no available devices are found, the request will return a 200 OK response but with no data populated. When no track is currently playing, the request will return a 204 NO CONTENT response with no payload. If private session is enabled the response will be a 204 NO CONTENT with an empty payload.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-the-users-currently-playing-track">Get the User's Currently Playing Track</a>
     */
    @GET("/me/player/currently-playing")
    Call<CurrentlyPlaying> getUsersCurrentlyPlayingTrack(@Query("market") String market);

    /**
     * <h3>Get the User's Currently Playing Track</h3>
     * Get the object currently being played on the user’s Spotify account.
     * <h3>Required OAuth scopes</h3>
     * <code>user-read-currently-playing, user-read-playback-state</code>
     * 
     * @param market An ISO 3166-1 alpha-2 country code or the string from_token. Provide this parameter if you want to apply Track Relinking.
     * @param additional_types A comma-separated list of item types that your client supports besides the default track type. Valid types are: track and episode. An unsupported type in the response is expected to be represented as null value in the item field. Note: This parameter was introduced to allow existing clients to maintain their current behaviour and might be deprecated in the future. In addition to providing this parameter, make sure that your client properly handles cases of new types in the future by checking against the currently_playing_type field.
     * @return A successful request will return a 200 OK response code with a json payload that contains information about the currently playing track or episode and its context (see below). The information returned is for the last known state, which means an inactive device could be returned if it was the last one to execute playback. When no available devices are found, the request will return a 200 OK response but with no data populated. When no track is currently playing, the request will return a 204 NO CONTENT response with no payload. If private session is enabled the response will be a 204 NO CONTENT with an empty payload.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-the-users-currently-playing-track">Get the User's Currently Playing Track</a>
     */
    @GET("/me/player/currently-playing")
    Call<CurrentlyPlaying> getUsersCurrentlyPlayingTrack(@Query("market") String market, @Query("additional_types") String additional_types);

    /**
     * <h3>Pause a User's Playback</h3>
     * Pause playback on the user’s account.
     * 
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-pause-a-users-playback">Pause a User's Playback</a>
     */
    @PUT("/me/player/pause")
    Call<Void> pauseUsersPlayback();

    /**
     * <h3>Pause a User's Playback</h3>
     * Pause playback on the user’s account.
     * 
     * @param device_id The id of the device this command is targeting. If not supplied, the user’s currently active device is the target.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-pause-a-users-playback">Pause a User's Playback</a>
     */
    @PUT("/me/player/pause")
    Call<Void> pauseUsersPlayback(@Query("device_id") String device_id);

    /**
     * <h3>Seek To Position In Currently Playing Track</h3>
     * Seeks to the given position in the user’s currently playing track.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param position_ms The position in milliseconds to seek to. Must be a positive number. Passing in a position that is greater than the length of the track will cause the player to start playing the next song.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-seek-to-position-in-currently-playing-track">Seek To Position In Currently Playing Track</a>
     */
    @PUT("/me/player/seek")
    Call<Void> seekToPositionInCurrentlyPlayingTrack(@Query("position_ms") Integer position_ms);

    /**
     * <h3>Seek To Position In Currently Playing Track</h3>
     * Seeks to the given position in the user’s currently playing track.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param position_ms The position in milliseconds to seek to. Must be a positive number. Passing in a position that is greater than the length of the track will cause the player to start playing the next song.
     * @param device_id The id of the device this command is targeting. If not supplied, the user’s currently active device is the target.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-seek-to-position-in-currently-playing-track">Seek To Position In Currently Playing Track</a>
     */
    @PUT("/me/player/seek")
    Call<Void> seekToPositionInCurrentlyPlayingTrack(@Query("position_ms") Integer position_ms, @Query("device_id") String device_id);

    /**
     * <h3>Set Repeat Mode On User’s Playback</h3>
     * Set the repeat mode for the user’s playback. Options are repeat-track, repeat-context, and off.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param state track, context or off. track will repeat the current track. context will repeat the current context. off will turn repeat off.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-set-repeat-mode-on-users-playback">Set Repeat Mode On User’s Playback</a>
     */
    @PUT("/me/player/repeat")
    Call<Void> setRepeatModeOnUsersPlayback(@Query("state") String state);

    /**
     * <h3>Set Repeat Mode On User’s Playback</h3>
     * Set the repeat mode for the user’s playback. Options are repeat-track, repeat-context, and off.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param state track, context or off. track will repeat the current track. context will repeat the current context. off will turn repeat off.
     * @param device_id The id of the device this command is targeting. If not supplied, the user’s currently active device is the target.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-set-repeat-mode-on-users-playback">Set Repeat Mode On User’s Playback</a>
     */
    @PUT("/me/player/repeat")
    Call<Void> setRepeatModeOnUsersPlayback(@Query("state") String state, @Query("device_id") String device_id);

    /**
     * <h3>Set Volume For User's Playback</h3>
     * Set the volume for the user’s current playback device.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param volume_percent The volume to set. Must be a value from 0 to 100 inclusive.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-set-volume-for-users-playback">Set Volume For User's Playback</a>
     */
    @PUT("/me/player/volume")
    Call<Void> setVolumeForUsersPlayback(@Query("volume_percent") Integer volume_percent);

    /**
     * <h3>Set Volume For User's Playback</h3>
     * Set the volume for the user’s current playback device.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param volume_percent The volume to set. Must be a value from 0 to 100 inclusive.
     * @param device_id The id of the device this command is targeting. If not supplied, the user’s currently active device is the target.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-set-volume-for-users-playback">Set Volume For User's Playback</a>
     */
    @PUT("/me/player/volume")
    Call<Void> setVolumeForUsersPlayback(@Query("volume_percent") Integer volume_percent, @Query("device_id") String device_id);

    /**
     * <h3>Skip User’s Playback To Next Track</h3>
     * Skips to next track in the user’s queue.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-skip-users-playback-to-next-track">Skip User’s Playback To Next Track</a>
     */
    @POST("/me/player/next")
    Call<Void> skipUsersPlaybackToNextTrack();

    /**
     * <h3>Skip User’s Playback To Next Track</h3>
     * Skips to next track in the user’s queue.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param device_id The id of the device this command is targeting. If not supplied, the user’s currently active device is the target.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-skip-users-playback-to-next-track">Skip User’s Playback To Next Track</a>
     */
    @POST("/me/player/next")
    Call<Void> skipUsersPlaybackToNextTrack(@Query("device_id") String device_id);

    /**
     * <h3>Skip User’s Playback To Previous Track</h3>
     * Skips to previous track in the user’s queue.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-skip-users-playback-to-previous-track">Skip User’s Playback To Previous Track</a>
     */
    @POST("/me/player/previous")
    Call<Void> skipUsersPlaybackToPreviousTrack();

    /**
     * <h3>Skip User’s Playback To Previous Track</h3>
     * Skips to previous track in the user’s queue.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param device_id The id of the device this command is targeting. If not supplied, the user’s currently active device is the target.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-skip-users-playback-to-previous-track">Skip User’s Playback To Previous Track</a>
     */
    @POST("/me/player/previous")
    Call<Void> skipUsersPlaybackToPreviousTrack(@Query("device_id") String device_id);

    /**
     * <h3>Start/Resume a User's Playback</h3>
     * Start a new context or resume current playback on the user’s active device.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-start-a-users-playback">Start/Resume a User's Playback</a>
     */
    @PUT("/me/player/play")
    Call<Void> startUsersPlayback();

    /**
     * <h3>Start/Resume a User's Playback</h3>
     * Start a new context or resume current playback on the user’s active device.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param requestBody The request body
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-start-a-users-playback">Start/Resume a User's Playback</a>
     */
    @PUT("/me/player/play")
    Call<Void> startUsersPlayback(@Body StartUsersPlaybackRequest requestBody);

    /**
     * <h3>Start/Resume a User's Playback</h3>
     * Start a new context or resume current playback on the user’s active device.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param device_id The id of the device this command is targeting. If not supplied, the user’s currently active device is the target.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-start-a-users-playback">Start/Resume a User's Playback</a>
     */
    @PUT("/me/player/play")
    Call<Void> startUsersPlayback(@Query("device_id") String device_id);

    /**
     * <h3>Start/Resume a User's Playback</h3>
     * Start a new context or resume current playback on the user’s active device.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param device_id The id of the device this command is targeting. If not supplied, the user’s currently active device is the target.
     * @param requestBody The request body
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-start-a-users-playback">Start/Resume a User's Playback</a>
     */
    @PUT("/me/player/play")
    Call<Void> startUsersPlayback(@Query("device_id") String device_id, @Body StartUsersPlaybackRequest requestBody);

    /**
     * <h3>Toggle Shuffle For User’s Playback</h3>
     * Toggle shuffle on or off for user’s playback.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param state true : Shuffle user’s playback. false : Do not shuffle user’s playback.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-toggle-shuffle-for-users-playback">Toggle Shuffle For User’s Playback</a>
     */
    @PUT("/me/player/shuffle")
    Call<Void> toggleShuffleForUsersPlayback(@Query("state") Boolean state);

    /**
     * <h3>Toggle Shuffle For User’s Playback</h3>
     * Toggle shuffle on or off for user’s playback.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param state true : Shuffle user’s playback. false : Do not shuffle user’s playback.
     * @param device_id The id of the device this command is targeting. If not supplied, the user’s currently active device is the target.
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-toggle-shuffle-for-users-playback">Toggle Shuffle For User’s Playback</a>
     */
    @PUT("/me/player/shuffle")
    Call<Void> toggleShuffleForUsersPlayback(@Query("state") Boolean state, @Query("device_id") String device_id);

    /**
     * <h3>Transfer a User's Playback</h3>
     * Transfer playback to a new device and determine if it should start playing.
     * <h3>Required OAuth scopes</h3>
     * <code>user-modify-playback-state</code>
     * 
     * @param requestBody the request body
     * @return A completed request will return a 204 NO CONTENT response code, and then issue the command to the player. Due to the asynchronous nature of the issuance of the command, you should use the Get Information About The User’s Current Playback endpoint to check that your issued command was handled correctly by the player. If the device is not found, the request will return 404 NOT FOUND response code. If the user making the request is non-premium, a 403 FORBIDDEN response code will be returned.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-transfer-a-users-playback">Transfer a User's Playback</a>
     */
    @PUT("/me/player")
    Call<Void> transferUsersPlayback(@Body TransferUsersPlaybackRequest requestBody);
}
