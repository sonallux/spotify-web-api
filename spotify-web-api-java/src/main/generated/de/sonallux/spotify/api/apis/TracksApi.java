package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#category-tracks">Tracks API</a>
 */
public interface TracksApi {

    /**
     * <h3>Get Audio Analysis for a Track</h3>
     * <p>Get a detailed audio analysis for a single track identified by its unique Spotify ID.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the track.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains an audio analysis object in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-audio-analysis">Get Audio Analysis for a Track</a>
     */
    @GET("/audio-analysis/{id}")
    Call<AudioAnalysis> getAudioAnalysis(@Path("id") String id);

    /**
     * <h3>Get Audio Features for a Track</h3>
     * <p>Get audio feature information for a single track identified by its unique Spotify ID.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the track.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#audio-features-object">audio features object</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-audio-features">Get Audio Features for a Track</a>
     */
    @GET("/audio-features/{id}")
    Call<AudioFeatures> getAudioFeatures(@Path("id") String id);

    /**
     * <h3>Get Audio Features for Several Tracks</h3>
     * <p>Get audio features for multiple tracks based on their Spotify IDs.</p>
     * 
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the tracks. Maximum: 100 IDs.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200 OK</code> and the response body contains an object whose key is <code>&quot;audio_features&quot;</code> and whose value is an array of audio features objects in JSON format.</p> <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-several-audio-features">Get Audio Features for Several Tracks</a>
     */
    @GET("/audio-features")
    Call<AudioFeaturesArray> getSeveralAudioFeatures(@Query("ids") String ids);

    /**
     * <h3>Get Several Tracks</h3>
     * <p>Get Spotify catalog information for multiple tracks based on their Spotify IDs.</p>
     * 
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the tracks. Maximum: 50 IDs.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>tracks</code> and whose value is an array of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#track-object-full">track objects</a> in JSON format.</p> <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-several-tracks">Get Several Tracks</a>
     */
    @GET("/tracks")
    Call<Tracks> getSeveralTracks(@Query("ids") String ids);

    /**
     * <h3>Get Several Tracks</h3>
     * <p>Get Spotify catalog information for multiple tracks based on their Spotify IDs.</p>
     * 
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the tracks. Maximum: 50 IDs.</p>
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains an object whose key is <code>tracks</code> and whose value is an array of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#track-object-full">track objects</a> in JSON format.</p> <p>Objects are returned in the order requested. If an object is not found, a <code>null</code> value is returned in the appropriate position. Duplicate <code>ids</code> in the query will result in duplicate objects in the response. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-several-tracks">Get Several Tracks</a>
     */
    @GET("/tracks")
    Call<Tracks> getSeveralTracks(@Query("ids") String ids, @Query("market") String market);

    /**
     * <h3>Get a Track</h3>
     * <p>Get Spotify catalog information for a single track identified by its unique Spotify ID.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the track.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#track-object-full">track object</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-track">Get a Track</a>
     */
    @GET("/tracks/{id}")
    Call<Track> getTrack(@Path("id") String id);

    /**
     * <h3>Get a Track</h3>
     * <p>Get Spotify catalog information for a single track identified by its unique Spotify ID.</p>
     * 
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the track.</p>
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Provide this parameter if you want to apply <a href="https://developer.spotify.com/documentation/general/guides/track-relinking-guide/">Track Relinking</a>.</p>
     * @return <p>On success, the HTTP status code in the response header is <code>200</code> OK and the response body contains a <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#track-object-full">track object</a> in JSON format. On error, the header status code is an <a href="https://developer.spotify.com/documentation/web-api/#response-status-codes">error code</a> and the response body contains an <a href="https://developer.spotify.com/documentation/web-api/#response-schema">error object</a>.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#endpoint-get-track">Get a Track</a>
     */
    @GET("/tracks/{id}")
    Call<Track> getTrack(@Path("id") String id, @Query("market") String market);
}
