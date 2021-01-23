package de.sonallux.spotify.api.authorization.authorization_code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import okhttp3.HttpUrl;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationResponse {
    private final String state;
    private final String code;
    private final String error;

    public boolean isSuccess() {
        return code != null && code.length() > 0;
    }

    public static AuthorizationResponse success(String code, String state) {
        return new AuthorizationResponse(state, code, null);
    }

    public static AuthorizationResponse success(String code) {
        return new AuthorizationResponse(null, code, null);
    }

    public static AuthorizationResponse error(String error, String state) {
        return new AuthorizationResponse(state, null, error);
    }

    public static AuthorizationResponse error(String error) {
        return new AuthorizationResponse(null, null, error);
    }

    /**
     * Parses the authorization response from the callback url
     * @param uri the uri
     * @return the authorization response
     * @throws IllegalArgumentException If {@code uri} is not a well-formed URI.
     */
    public static AuthorizationResponse parse(String uri) {
        var httpUrl = HttpUrl.get(uri);

        var code = httpUrl.queryParameter("code");
        var state = httpUrl.queryParameter("state");
        var error = httpUrl.queryParameter("error");

        if (code != null && code.length() > 0) {
            return new AuthorizationResponse(state, code, null);
        }
        else if (error != null && error.length() > 0) {
            return new AuthorizationResponse(state, null, error);
        }
        else {
            return new AuthorizationResponse(state, null, "Missing error or code on authorization response uri");
        }
    }
}
