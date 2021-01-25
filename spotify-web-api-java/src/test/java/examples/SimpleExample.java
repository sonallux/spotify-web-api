package examples;

import de.sonallux.spotify.api.SpotifyApi;
import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.authorization.SimpleApiAuthorizationProvider;

public class SimpleExample {
    public static void main(String[] args) {
        var authProvider = new SimpleApiAuthorizationProvider("<your access token>");
        var spotifyApi = new SpotifyApi(authProvider);

        var getArtistCall = spotifyApi.getArtistsApi().getArtist("<artist id>");

        try {
            var artist = spotifyApi.callApiAndReturnBody(getArtistCall);
            System.out.println(artist.getName());
        } catch (SpotifyApiException e) {
            e.printStackTrace();
        }

    }
}
