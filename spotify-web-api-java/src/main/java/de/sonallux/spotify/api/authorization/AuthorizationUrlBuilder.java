package de.sonallux.spotify.api.authorization;

import okhttp3.HttpUrl;

import java.util.Arrays;

public class AuthorizationUrlBuilder {
    protected final HttpUrl.Builder builder;

    public AuthorizationUrlBuilder(String clientId, String redirectUri, String responseType) {
        builder = new HttpUrl.Builder()
            .scheme("https")
            .host("accounts.spotify.com")
            .addPathSegment("authorize")
            .addQueryParameter("client_id", clientId)
            .addQueryParameter("response_type", responseType)
            .addQueryParameter("redirect_uri", redirectUri);
    }

    public AuthorizationUrlBuilder state(String state) {
        builder.addQueryParameter("state", state);
        return this;
    }

    public AuthorizationUrlBuilder scopes(Scope... scopes) {
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

    public AuthorizationUrlBuilder showDialog(boolean showDialog) {
        builder.addQueryParameter("show_dialog", String.valueOf(showDialog));
        return this;
    }

    public HttpUrl build() {
        return builder.build();
    }
}
