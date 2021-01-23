package de.sonallux.spotify.api.authorization.authorization_code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationResponseTest {
    @Test
    void parseSuccess() {
        var response = AuthorizationResponse.parse("https://example.com/callback?code=NApCCgBkWtQ");
        assertTrue(response.isSuccess());
        assertEquals("NApCCgBkWtQ", response.getCode());
        assertNull(response.getState());
        assertNull(response.getError());
    }

    @Test
    void parseSuccessWithState() {
        var response = AuthorizationResponse.parse("https://example.com/callback?code=NApCCgBkWtQ&state=state-123");
        assertTrue(response.isSuccess());
        assertEquals("NApCCgBkWtQ", response.getCode());
        assertEquals("state-123", response.getState());
        assertNull(response.getError());
    }

    @Test
    void parseInvalidUri() {
        assertThrows(IllegalArgumentException.class, () -> AuthorizationResponse.parse("invalid-uri"));
    }

    @Test
    void parseValidUri() {
        var response = AuthorizationResponse.parse("https://example.com/callback");
        assertFalse(response.isSuccess());
        assertEquals("Missing error or code on authorization response uri", response.getError());
        assertNull(response.getState());
        assertNull(response.getCode());
    }

    @Test
    void parseError() {
        var response = AuthorizationResponse.parse("https://example.com/callback?error=access_denied");
        assertFalse(response.isSuccess());
        assertEquals("access_denied", response.getError());
        assertNull(response.getState());
        assertNull(response.getCode());
    }

    @Test
    void parseErrorWithState() {
        var response = AuthorizationResponse.parse("https://example.com/callback?error=access_denied&state=state-42");
        assertFalse(response.isSuccess());
        assertEquals("access_denied", response.getError());
        assertEquals("state-42", response.getState());
        assertNull(response.getCode());
    }
}
