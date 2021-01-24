package de.sonallux.spotify.api.authorization.authorization_code;

import de.sonallux.spotify.api.authorization.Scope;
import okhttp3.HttpUrl;

import java.util.Arrays;

public class AuthorizationCodeUriBuilder {
    final HttpUrl.Builder builder;

    AuthorizationCodeUriBuilder(String clientId, String redirectUri) {
        builder = new HttpUrl.Builder()
            .scheme("https")
            .host("accounts.spotify.com")
            .addPathSegment("authorize")
            .addQueryParameter("client_id", clientId)
            .addQueryParameter("response_type", "code")
            .addQueryParameter("redirect_uri", redirectUri);
    }

    public AuthorizationCodeUriBuilder state(String state) {
        builder.addQueryParameter("state", state);
        return this;
    }

    public AuthorizationCodeUriBuilder scopes(Scope... scopes) {
        if (scopes.length == 0) {
            return this;
        }
        var scopeString = Arrays.stream(scopes)
            .map(Scope::getName)
            .reduce((scope1, scope2) -> scope1 + " " + scope2)
            .orElse("");

        builder.addQueryParameter("scope", scopeString);
        return this;
    }

    public AuthorizationCodeUriBuilder showDialog(boolean showDialog) {
        builder.addQueryParameter("show_dialog", String.valueOf(showDialog));
        return this;
    }

    public HttpUrl build() {
        return builder.build();
    }
}
