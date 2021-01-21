package de.sonallux.spotify.api.authorization;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class InMemoryTokenStore implements TokenStore {
    private AuthTokens authTokens;

    @Override
    public void storeTokens(AuthTokens authTokens) {
        this.authTokens = authTokens;
    }

    @Override
    public AuthTokens loadTokens() {
        if (this.authTokens == null) {
            return new AuthTokens();
        }
        return this.authTokens;
    }
}
