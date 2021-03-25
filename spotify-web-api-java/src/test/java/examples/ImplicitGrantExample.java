package examples;

import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.SpotifyWebApi;
import de.sonallux.spotify.api.authorization.Scope;
import de.sonallux.spotify.api.authorization.implicit_grant.ImplicitGrantFlow;

public class ImplicitGrantExample {
    private final SpotifyWebApi spotifyApi;
    private final ImplicitGrantFlow implicitGrantFlow;

    public ImplicitGrantExample() {
        this.implicitGrantFlow = new ImplicitGrantFlow("<client id>", "<redirect uri>");
        this.spotifyApi = SpotifyWebApi.builder().authorization(implicitGrantFlow).build();
    }

    public static void main(String[] args) {
        var example = new ImplicitGrantExample();

        if (example.authorize()) {
            // Once authorized the access token will be automatically renewed, if is has expired
            example.useTheSpotifyWebApi();
        }
    }

    private boolean authorize() {
        // Navigate the user to this authorizationUrl, so he can grant access
        var authorizationUrl = implicitGrantFlow.createAuthorizationUrl()
            .state("<state>")
            .scopes(Scope.USER_LIBRARY_READ)
            //.showDialog(true)
            .build();

        // After successful authorization the user will be redirected
        var responseUrl = "<redirect response url>";
        var redirectResponse = implicitGrantFlow.parseAuthorizationRedirectResponse(responseUrl);

        //Validate redirectResponse.getState() with the state parameter you choose for the authorization url

        if (!redirectResponse.isSuccess()) {
            System.err.println("Authorization failed: " + redirectResponse.getError());
            return false;
        }

        implicitGrantFlow.useResponse(redirectResponse);
        return true;
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
