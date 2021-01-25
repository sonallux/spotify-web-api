package de.sonallux.spotify.api.authorization;

import okhttp3.HttpUrl;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationRedirectResponseTest {

    private final Function<HttpUrl, String> contentExtractor = httpUrl -> {
        var code = httpUrl.queryParameter("code");
            if (code != null && code.length() > 0) {
                return code;
        }
        return null;
    };

    @Test
    void parseSuccess() {
        var url = "https://example.com/callback?code=NApCCgBkWtQ";
        var response = AuthorizationRedirectResponse.parse(url, contentExtractor);
        assertTrue(response.isSuccess());
        assertEquals("NApCCgBkWtQ", response.getBody());
        assertNull(response.getState());
        assertNull(response.getError());
    }

    @Test
    void parseSuccessWithState() {
        var url = "https://example.com/callback?code=NApCCgBkWtQ&state=state-123";
        var response = AuthorizationRedirectResponse.parse(url, contentExtractor);
        assertTrue(response.isSuccess());
        assertEquals("NApCCgBkWtQ", response.getBody());
        assertEquals("state-123", response.getState());
        assertNull(response.getError());
    }

    @Test
    void parseInvalidUri() {
        var url = "invalid-uri";
        assertThrows(IllegalArgumentException.class, () -> AuthorizationRedirectResponse.parse(url, contentExtractor));
    }

    @Test
    void parseValidUri() {
        var url = "https://example.com/callback";
        var response = AuthorizationRedirectResponse.parse(url, contentExtractor);
        assertFalse(response.isSuccess());
        assertEquals("Invalid authorization redirect response uri", response.getError());
        assertNull(response.getState());
        assertNull(response.getBody());
    }

    @Test
    void parseError() {
        var url = "https://example.com/callback?error=access_denied";
        var response = AuthorizationRedirectResponse.parse(url, contentExtractor);
        assertFalse(response.isSuccess());
        assertEquals("access_denied", response.getError());
        assertNull(response.getState());
        assertNull(response.getBody());
    }

    @Test
    void parseErrorWithState() {
        var url = "https://example.com/callback?error=access_denied&state=state-42";
        var response = AuthorizationRedirectResponse.parse(url, contentExtractor);
        assertFalse(response.isSuccess());
        assertEquals("access_denied", response.getError());
        assertEquals("state-42", response.getState());
        assertNull(response.getBody());
    }
}
