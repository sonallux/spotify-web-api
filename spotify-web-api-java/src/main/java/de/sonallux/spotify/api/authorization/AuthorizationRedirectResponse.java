package de.sonallux.spotify.api.authorization;

import de.sonallux.spotify.api.util.TextUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import okhttp3.HttpUrl;

import java.util.function.Function;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationRedirectResponse<T> {
    private final String state;
    private final T body;
    private final String error;

    public boolean isSuccess() {
        return body != null;
    }

    public static <T> AuthorizationRedirectResponse<T> success(T body, String state) {
        return new AuthorizationRedirectResponse<>(state, body, null);
    }

    public static <T> AuthorizationRedirectResponse<T> success(T body) {
        return new AuthorizationRedirectResponse<>(null, body, null);
    }

    public static <T> AuthorizationRedirectResponse<T> error(String error, String state) {
        return new AuthorizationRedirectResponse<>(state, null, error);
    }

    public static <T> AuthorizationRedirectResponse<T> error(String error) {
        return new AuthorizationRedirectResponse<>(null, null, error);
    }

    /**
     * Parses the authorization response from the callback url
     * @param url the callback url
     * @return the authorization response
     * @throws IllegalArgumentException If {@code uri} is not a well-formed URI.
     */
    public static <T> AuthorizationRedirectResponse<T> parse(String url, Function<HttpUrl, T> contentExtractor) {
        return parse(HttpUrl.get(url), contentExtractor);
    }

    /**
     * Parses the authorization response from the callback url
     * @param httpUrl the callback url
     * @return the authorization response
     * @throws IllegalArgumentException If {@code uri} is not a well-formed URI.
     */
    public static <T> AuthorizationRedirectResponse<T> parse(HttpUrl httpUrl, Function<HttpUrl, T> contentExtractor) {
        var state = httpUrl.queryParameter("state");
        var error = httpUrl.queryParameter("error");

        var content = contentExtractor.apply(httpUrl);
        if (content != null) {
            return new AuthorizationRedirectResponse<>(state, content, null);
        }
        else if (TextUtil.hasText(error)) {
            return new AuthorizationRedirectResponse<>(state, null, error);
        }
        else {
            return new AuthorizationRedirectResponse<>(state, null, "Invalid authorization redirect response uri");
        }
    }
}
