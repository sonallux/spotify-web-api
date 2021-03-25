package examples;

import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.SpotifyWebApi;
import de.sonallux.spotify.api.authorization.SimpleApiAuthorizationProvider;

public class SimpleExample {
    public static void main(String[] args) {
        var authProvider = new SimpleApiAuthorizationProvider("<your access token>");
        var spotifyApi = SpotifyWebApi.builder().authorization(authProvider).build();

        try {
            var artist = spotifyApi.getArtistsApi().getArtist("<artist id>").build().execute();
            System.out.println(artist.getName());
        } catch (SpotifyApiException e) {
            e.printStackTrace();
        }
    }
}
