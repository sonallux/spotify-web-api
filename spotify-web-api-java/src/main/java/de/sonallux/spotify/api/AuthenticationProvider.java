package de.sonallux.spotify.api;

import de.sonallux.spotify.api.authentication.AuthTokens;

public interface AuthenticationProvider {
    String getAccessToken();

    String getRefreshToken();

    String getClientId();

    String getClientSecret();

    void onNewAuthTokens(AuthTokens authTokens);

    void onErrorGettingAuthTokens(SpotifyApiException exception);
}
