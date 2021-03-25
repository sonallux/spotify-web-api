package examples;

import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.SpotifyWebApi;
import de.sonallux.spotify.api.authorization.SpotifyAuthorizationException;
import de.sonallux.spotify.api.authorization.client_credentials.ClientCredentialsFlow;

public class ClientCredentialsExample {
    public static void main(String[] args) {
        var clientCredentialsFlow = new ClientCredentialsFlow("<client id>", "<client secret>");
        var spotifyApi = SpotifyWebApi.builder().authorization(clientCredentialsFlow).build();

        try {
            clientCredentialsFlow.authorize();
        } catch (SpotifyAuthorizationException e) {
            e.printStackTrace();
            return;
        }

        try {
            var artist = spotifyApi.getArtistsApi().getArtist("<artist id>").build().execute();
            System.out.println(artist.getName());
        } catch (SpotifyApiException e) {
            e.printStackTrace();
        }
    }
}
