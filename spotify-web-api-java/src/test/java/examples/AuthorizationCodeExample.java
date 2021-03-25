package examples;

import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.SpotifyWebApi;
import de.sonallux.spotify.api.authorization.InMemoryTokenStore;
import de.sonallux.spotify.api.authorization.Scope;
import de.sonallux.spotify.api.authorization.SpotifyAuthorizationException;
import de.sonallux.spotify.api.authorization.authorization_code.AuthorizationCodeFlow;

public class AuthorizationCodeExample {
    private final SpotifyWebApi spotifyApi;
    private final AuthorizationCodeFlow authCodeFlow;

    public AuthorizationCodeExample() {
        var tokenStore = new InMemoryTokenStore();
        this.authCodeFlow = new AuthorizationCodeFlow("<client id>", "<client secret>", "<redirect uri>", tokenStore);
        this.spotifyApi = SpotifyWebApi.builder().authorization(authCodeFlow).build();
    }

    public static void main(String[] args) {
        var example = new AuthorizationCodeExample();

        if (example.authorize()) {
            // Once authorized the access token will be automatically renewed, if is has expired
            example.useTheSpotifyWebApi();
        }
    }

    private boolean authorize() {
        // Navigate the user to this authorizationUrl, so he can grant access
        var authorizationUrl = authCodeFlow.createAuthorizationUrl()
            .state("<state>")
            .scopes(Scope.USER_LIBRARY_READ)
            //.showDialog(true)
            .build();

        // After successful authorization the user will be redirected
        var responseUrl = "<redirect response url>";
        var redirectResponse = authCodeFlow.parseAuthorizationRedirectResponse(responseUrl);

        //Validate redirectResponse.getState() with the state parameter you choose for the authorization url

        if (!redirectResponse.isSuccess()) {
            System.err.println("Authorization failed: " + redirectResponse.getError());
            return false;
        }

        try {
            authCodeFlow.exchangeAuthorizationCode(redirectResponse);
            return true;
        } catch (SpotifyAuthorizationException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void useTheSpotifyWebApi() {
        try {
            var usersSavedTracks = spotifyApi.getLibraryApi().getUsersSavedTracks().build().execute();
            usersSavedTracks.getItems().forEach(System.out::println);
        } catch (SpotifyApiException e) {
            e.printStackTrace();
        }
    }
}
