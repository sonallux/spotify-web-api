package de.sonallux.spotify.api.authentication;

import okhttp3.HttpUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SpotifyAuthApiTest
{
    private SpotifyAuthApi authApi;

    @BeforeEach
    void setup()
    {
        this.authApi = new SpotifyAuthApi("1a2b3c4d5e6f7", "bar456");
    }

    @Test
    void testGetAuthorizeUrl()
    {
        HttpUrl expectedUrl = HttpUrl.parse("https://accounts.spotify.com/authorize?client_id=1a2b3c4d5e6f7&response_type=code&redirect_uri=https://example.com/callback&scope=user-read-private user-read-email&state=34fFs29kd10");
        HttpUrl realUrl = authApi.getAuthorizeUrl("code", "https://example.com/callback",
                "34fFs29kd10", Arrays.asList(Scope.USER_READ_PRIVATE, Scope.USER_READ_EMAIL));

        assertEquals(expectedUrl, realUrl);
    }

    @Test
    void testParseRedirectResponseSuccess()
    {
        SpotifyAuthApi.RedirectResponse redirectResponse = authApi.parseRedirectResponse("https://example.com/callback?code=NApCCgBkWtQ&state=profile%2Factivity");
        assertNotNull(redirectResponse);
        assertNull(redirectResponse.getError());
        assertEquals("profile/activity", redirectResponse.getState());
        assertEquals("NApCCgBkWtQ", redirectResponse.getAuthCode());
    }

    @Test
    void testParseRedirectResponseFail()
    {
        SpotifyAuthApi.RedirectResponse redirectResponse = authApi.parseRedirectResponse("https://example.com/callback?error=access_denied&state=STATE");
        assertNotNull(redirectResponse);
        assertNull(redirectResponse.getAuthCode());
        assertEquals("STATE", redirectResponse.getState());
        assertEquals("access_denied", redirectResponse.getError());
    }
}
