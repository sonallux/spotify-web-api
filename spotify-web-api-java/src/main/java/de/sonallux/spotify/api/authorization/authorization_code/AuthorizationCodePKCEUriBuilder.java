package de.sonallux.spotify.api.authorization.authorization_code;

class AuthorizationCodePKCEUriBuilder extends AuthorizationCodeUriBuilder {
    AuthorizationCodePKCEUriBuilder(String clientId, String redirectUri, String codeChallenge) {
        super(clientId, redirectUri);
        builder
            .addQueryParameter("code_challenge_method", "S256")
            .addQueryParameter("code_challenge", codeChallenge);
    }
}
