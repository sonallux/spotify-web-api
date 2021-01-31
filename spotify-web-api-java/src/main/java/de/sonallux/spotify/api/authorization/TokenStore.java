package de.sonallux.spotify.api.authorization;

public interface TokenStore {
    void storeTokens(AuthTokens authTokens);
    AuthTokens loadTokens();
}
