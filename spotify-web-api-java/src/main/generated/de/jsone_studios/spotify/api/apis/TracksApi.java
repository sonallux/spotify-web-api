package de.jsone_studios.spotify.api.apis;

import de.jsone_studios.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-tracks">Tracks API</a>
 */
public interface TracksApi {

    /**
     * <h3>Get Audio Analysis for a Track</h3>
     * Get a detailed audio analysis for a single track identified by its unique Spotify ID.
     * 
     * @param id The Spotify ID for the track.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an audio analysis object in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-audio-analysis">Get Audio Analysis for a Track</a>
     */
    @GET("/audio-analysis/{id}")
    Call<AudioAnalysis> getAudioAnalysis(@Path("id") String id);

    /**
     * <h3>Get Audio Features for a Track</h3>
     * Get audio feature information for a single track identified by its unique Spotify ID.
     * 
     * @param id The Spotify ID for the track.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an audio features object in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-audio-features">Get Audio Features for a Track</a>
     */
    @GET("/audio-features/{id}")
    Call<AudioFeatures> getAudioFeatures(@Path("id") String id);

    /**
     * <h3>Get Audio Features for Several Tracks</h3>
     * Get audio features for multiple tracks based on their Spotify IDs.
     * 
     * @param ids A comma-separated list of the Spotify IDs for the tracks. Maximum: 100 IDs.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is &quot;audio_features&quot; and whose value is an array of audio features objects in JSON format. Objects are returned in the order requested. If an object is not found, a null value is returned in the appropriate position. Duplicate ids in the query will result in duplicate objects in the response. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-several-audio-features">Get Audio Features for Several Tracks</a>
     */
    @GET("/audio-features")
    Call<AudioFeaturesArray> getSeveralAudioFeatures(@Query("ids") String ids);

    /**
     * <h3>Get Several Tracks</h3>
     * Get Spotify catalog information for multiple tracks based on their Spotify IDs.
     * 
     * @param ids A comma-separated list of the Spotify IDs for the tracks. Maximum: 50 IDs.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is tracks and whose value is an array of track objects in JSON format. Objects are returned in the order requested. If an object is not found, a null value is returned in the appropriate position. Duplicate ids in the query will result in duplicate objects in the response. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-several-tracks">Get Several Tracks</a>
     */
    @GET("/tracks")
    Call<Tracks> getSeveralTracks(@Query("ids") String ids);

    /**
     * <h3>Get Several Tracks</h3>
     * Get Spotify catalog information for multiple tracks based on their Spotify IDs.
     * 
     * @param ids A comma-separated list of the Spotify IDs for the tracks. Maximum: 50 IDs.
     * @param market An ISO 3166-1 alpha-2 country code or the string from_token. Provide this parameter if you want to apply Track Relinking.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains an object whose key is tracks and whose value is an array of track objects in JSON format. Objects are returned in the order requested. If an object is not found, a null value is returned in the appropriate position. Duplicate ids in the query will result in duplicate objects in the response. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-several-tracks">Get Several Tracks</a>
     */
    @GET("/tracks")
    Call<Tracks> getSeveralTracks(@Query("ids") String ids, @Query("market") String market);

    /**
     * <h3>Get a Track</h3>
     * Get Spotify catalog information for a single track identified by its unique Spotify ID.
     * 
     * @param id The Spotify ID for the track.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains a track object in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-track">Get a Track</a>
     */
    @GET("/tracks/{id}")
    Call<Track> getTrack(@Path("id") String id);

    /**
     * <h3>Get a Track</h3>
     * Get Spotify catalog information for a single track identified by its unique Spotify ID.
     * 
     * @param id The Spotify ID for the track.
     * @param market An ISO 3166-1 alpha-2 country code or the string from_token. Provide this parameter if you want to apply Track Relinking.
     * @return On success, the HTTP status code in the response header is 200 OK and the response body contains a track object in JSON format. On error, the header status code is an error code and the response body contains an error object.
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-track">Get a Track</a>
     */
    @GET("/tracks/{id}")
    Call<Track> getTrack(@Path("id") String id, @Query("market") String market);
}