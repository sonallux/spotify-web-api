package de.sonallux.spotify.api.apis;

import de.sonallux.spotify.api.http.ApiClient;
import de.sonallux.spotify.api.apis.artists.*;
import lombok.RequiredArgsConstructor;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#category-artists">Artists API</a>
 */
@RequiredArgsConstructor
public class ArtistsApi {
    private final ApiClient apiClient;

    /**
     * <h3>Get an Artist</h3>
     * <p>Get Spotify catalog information for a single artist identified by their unique Spotify ID.</p>
     * @param id <p>The Spotify ID of the artist.</p>
     * @return a {@link GetArtistRequest} object to build and execute the request
     */
    public GetArtistRequest getArtist(String id) {
        return new GetArtistRequest(apiClient, id);
    }

    /**
     * <h3>Get an Artist's Albums</h3>
     * <p>Get Spotify catalog information about an artist's albums.</p>
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist.</p>
     * @return a {@link GetArtistsAlbumsRequest} object to build and execute the request
     */
    public GetArtistsAlbumsRequest getArtistsAlbums(String id) {
        return new GetArtistsAlbumsRequest(apiClient, id);
    }

    /**
     * <h3>Get an Artist's Related Artists</h3>
     * <p>Get Spotify catalog information about artists similar to a given artist. Similarity is based on analysis of the Spotify community's <a href="http://news.spotify.com/se/2010/02/03/related-artists/">listening history</a>.</p>
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist</p>
     * @return a {@link GetArtistsRelatedArtistsRequest} object to build and execute the request
     */
    public GetArtistsRelatedArtistsRequest getArtistsRelatedArtists(String id) {
        return new GetArtistsRelatedArtistsRequest(apiClient, id);
    }

    /**
     * <h3>Get an Artist's Top Tracks</h3>
     * <p>Get Spotify catalog information about an artist's top tracks by country.</p>
     * @param id <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the artist</p>
     * @param market <p>An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country code</a> or the string <code>from_token</code>. Synonym for <code>country</code>.</p>
     * @return a {@link GetArtistsTopTracksRequest} object to build and execute the request
     */
    public GetArtistsTopTracksRequest getArtistsTopTracks(String id, String market) {
        return new GetArtistsTopTracksRequest(apiClient, id, market);
    }

    /**
     * <h3>Get Multiple Artists</h3>
     * <p>Get Spotify catalog information for several artists based on their Spotify IDs.</p>
     * @param ids <p>A comma-separated list of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a> for the artists. Maximum: 50 IDs.</p>
     * @return a {@link GetMultipleArtistsRequest} object to build and execute the request
     */
    public GetMultipleArtistsRequest getMultipleArtists(String ids) {
        return new GetMultipleArtistsRequest(apiClient, ids);
    }
}
