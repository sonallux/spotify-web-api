package de.sonallux.spotify.api.authorization;

import de.sonallux.spotify.api.util.TextUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenStoreApiAuthorizationProvider implements ApiAuthorizationProvider{
    protected final TokenStore tokenStore;

    @Override
    public String getAuthorizationHeaderValue() {
        var tokens = tokenStore.loadTokens();
        if (tokens == null || !TextUtil.hasText(tokens.getAccessToken()) || !TextUtil.hasText(tokens.getTokenType())) {
            return null;
        }

        return tokens.getTokenType() + " " + tokens.getAccessToken();
    }

    @Override
    public boolean refreshAccessToken() {
        return false;
    }
}
