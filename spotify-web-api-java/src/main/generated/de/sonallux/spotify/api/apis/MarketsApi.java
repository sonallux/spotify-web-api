package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-markets">Markets API</a>
 */
public interface MarketsApi {

    /**
     * <h3>Get Available Markets</h3>
     * <p>Get the list of markets where Spotify is available.</p>
     *
     * @return <p>On success, the HTTP status code in the response header is 200 OK and the response body contains a list of the countries in which Spotify is available, identified by their <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> with additional country codes for special territories. On error, the header status code is an error code and the response body contains an error object.</p>
     * @see <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-get-available-markets">Get Available Markets</a>
     */
    @GET("/markets")
    Call<> getAvailableMarkets();
}
