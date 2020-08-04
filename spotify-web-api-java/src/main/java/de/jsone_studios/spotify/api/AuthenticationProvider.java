package de.jsone_studios.spotify.api;

import de.jsone_studios.spotify.api.authentication.AuthTokens;

public interface AuthenticationProvider {
    String getAccessToken();

    String getRefreshToken();

    String getClientId();

    String getClientSecret();

    void onNewAuthTokens(AuthTokens authTokens);

    void onErrorGettingAuthTokens(SpotifyApiException exception);
}
