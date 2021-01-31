package de.sonallux.spotify.api.authorization.implicit_grant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RedirectResponseBody {
    private final String accessToken;
    private final String tokenType;
    private final int expiresIn;
}
