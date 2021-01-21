package de.sonallux.spotify.api.authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A simple {@link ApiAuthorizationProvider} that can only hold an access token.
 * It does not support any authorization specific logic such as refreshing a token.
 * <br>
 * This class is useful if you already have an access token.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleApiAuthorizationProvider implements ApiAuthorizationProvider {
    private static final String TOKEN_TYPE = "Bearer";
    private String accessToken;

    @Override
    public String getAuthorizationHeaderValue() {
        if (accessToken == null) {
            return null;
        }
        return TOKEN_TYPE + " " + accessToken;
    }

    @Override
    public boolean refreshAccessToken() {
        return false;
    }
}
