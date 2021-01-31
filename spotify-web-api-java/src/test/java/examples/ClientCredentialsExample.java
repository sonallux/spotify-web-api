package examples;

import de.sonallux.spotify.api.SpotifyApi;
import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.authorization.SpotifyAuthorizationException;
import de.sonallux.spotify.api.authorization.client_credentials.ClientCredentialsFlow;

public class ClientCredentialsExample {
    public static void main(String[] args) {
        var clientCredentialsFlow = new ClientCredentialsFlow("<client id>", "<client secret>");
        var spotifyApi = new SpotifyApi(clientCredentialsFlow);

        try {
            clientCredentialsFlow.authorize();
        } catch (SpotifyAuthorizationException e) {
            e.printStackTrace();
            return;
        }

        var getArtistCall = spotifyApi.getArtistsApi().getArtist("<artist id>");
        try {
            var artist = spotifyApi.callApiAndReturnBody(getArtistCall);
            System.out.println(artist.getName());
        } catch (SpotifyApiException e) {
            e.printStackTrace();
        }
    }
}
