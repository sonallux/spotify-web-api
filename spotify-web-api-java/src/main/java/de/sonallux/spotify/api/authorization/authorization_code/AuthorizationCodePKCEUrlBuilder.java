package de.sonallux.spotify.api.authorization.authorization_code;

import de.sonallux.spotify.api.authorization.AuthorizationUrlBuilder;

class AuthorizationCodePKCEUrlBuilder extends AuthorizationUrlBuilder {
    AuthorizationCodePKCEUrlBuilder(String clientId, String redirectUri, String codeChallenge) {
        super(clientId, redirectUri, "code");
        builder
            .addQueryParameter("code_challenge_method", "S256")
            .addQueryParameter("code_challenge", codeChallenge);
    }
}
