package de.sonallux.spotify.api;

import de.sonallux.spotify.api.authentication.AuthTokens;
import de.sonallux.spotify.api.models.Artist;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticatedSpotifyApiTest {

    private MockWebServer webServer;
    private AuthenticatedSpotifyApi api;

    @Mock
    AuthenticationProvider authProvider;

    @BeforeEach
    void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        HttpUrl baseUrl = webServer.url("/");
        when(authProvider.getClientId()).thenReturn("clientId");
        when(authProvider.getClientSecret()).thenReturn("clientSecret");
        api = new AuthenticatedSpotifyApi(authProvider, baseUrl, baseUrl);
    }

    @Test
    void testCallApiAndReturnBodyError() {
        when(authProvider.getAccessToken()).thenReturn("wrongToken");
        webServer.enqueue(mockResponseUnauthorizedInvalidToken);
        SpotifyApiException exception = assertThrows(SpotifyApiException.class, () -> api.callApiAndReturnBody(api.getTracksApi().getTrack("foo")));
        assertNotNull(exception.getError());
        assertEquals(401, exception.getError().getStatus());
    }

    @Test
    void testCallApiAndReturnBody() throws SpotifyApiException {
        when(authProvider.getAccessToken()).thenReturn("token");
        webServer.enqueue(mockResponseArtist);
        Artist artist = api.callApiAndReturnBody(api.getArtistsApi().getArtist("foo"));
        assertNotNull(artist);
        assertEquals("0OdUWJ0sBjDrqHygGUXeCF", artist.getId());
    }

    @Test
    void testCallApiRefreshToken() throws SpotifyApiException {
        when(authProvider.getAccessToken()).thenReturn("accessToken");
        when(authProvider.getRefreshToken()).thenReturn("refreshToken");
        webServer.enqueue(mockResponseUnauthorizedExpiredToken);
        webServer.enqueue(mockResponseAuthTokens);
        webServer.enqueue(mockResponseArtist);

        Artist artist = api.callApiAndReturnBody(api.getArtistsApi().getArtist("foo"));
        assertNotNull(artist);
        assertEquals("0OdUWJ0sBjDrqHygGUXeCF", artist.getId());

        AuthTokens authTokens = new AuthTokens();
        authTokens.access_token = "NgA6ZcYIixn8bU";
        authTokens.token_type = "Bearer";
        authTokens.expires_in = 3600;
        authTokens.scope = "user-read-private user-read-email";

        verify(authProvider, Mockito.times(1)).onNewAuthTokens(refEq(authTokens));
    }

    @Test
    void testGetAuthTokensFromAuthCode() {
        webServer.enqueue(mockResponseAuthTokens);

        assertTrue(api.getAuthTokensFromAuthCode("code", "http://localhost/"));

        AuthTokens authTokens = new AuthTokens();
        authTokens.access_token = "NgA6ZcYIixn8bU";
        authTokens.token_type = "Bearer";
        authTokens.expires_in = 3600;
        authTokens.scope = "user-read-private user-read-email";

        verify(authProvider, Mockito.times(1)).onNewAuthTokens(refEq(authTokens));
    }

    @Test
    void testGetAuthTokensFromAuthCodeError() {
        webServer.enqueue(mockResponseBadRequestAuthTokens);

        assertFalse(api.getAuthTokensFromAuthCode("code", "http://localhost/"));

        verify(authProvider, Mockito.times(1)).onErrorGettingAuthTokens(any());
    }

    @AfterEach
    void teardown() throws IOException{
        webServer.shutdown();
    }

    private MockResponse mockResponseUnauthorizedNoToken = new MockResponse()
            .setStatus("HTTP/1.1 401 Unauthorized")
            .setBody("{\"error\": {\"status\": 401,\"message\": \"No token provided\"}}");

    private MockResponse mockResponseUnauthorizedExpiredToken = new MockResponse()
            .setStatus("HTTP/1.1 401 Unauthorized")
            .setBody("{\"error\": {\"status\": 401,\"message\": \"The access token expired\"}}");

    private MockResponse mockResponseUnauthorizedInvalidToken = new MockResponse()
            .setStatus("HTTP/1.1 401 Unauthorized")
            .setBody("{\"error\": {\"status\": 401,\"message\": \"Invalid access token\"}}");

    private MockResponse mockResponseBadRequestAuthTokens = new MockResponse()
            .setStatus("HTTP/1.1 400 Bad Request")
            .setBody("{\"error\":\"invalid_client\",\"error_description\":\"Invalid client\"}");

    private MockResponse mockResponseAuthTokens = new MockResponse()
            .setStatus("HTTP/1.1 200 OK")
            .setBody("{\n" +
                    "   \"access_token\": \"NgA6ZcYIixn8bU\",\n" +
                    "   \"token_type\": \"Bearer\",\n" +
                    "   \"scope\": \"user-read-private user-read-email\",\n" +
                    "   \"expires_in\": 3600\n" +
                    "}");

    private MockResponse mockResponseArtist = new MockResponse()
            .setStatus("HTTP/1.1 200 OK")
            .setBody("{\n" +
                    "  \"external_urls\" : {\n" +
                    "    \"spotify\" : \"https://open.spotify.com/artist/0OdUWJ0sBjDrqHygGUXeCF\"\n" +
                    "  },\n" +
                    "  \"followers\" : {\n" +
                    "    \"href\" : null,\n" +
                    "    \"total\" : 306565\n" +
                    "  },\n" +
                    "  \"genres\" : [ \"indie folk\", \"indie pop\" ],\n" +
                    "  \"href\" : \"https://api.spotify.com/v1/artists/0OdUWJ0sBjDrqHygGUXeCF\",\n" +
                    "  \"id\" : \"0OdUWJ0sBjDrqHygGUXeCF\",\n" +
                    "  \"images\" : [ {\n" +
                    "    \"height\" : 816,\n" +
                    "    \"url\" : \"https://i.scdn.co/image/eb266625dab075341e8c4378a177a27370f91903\",\n" +
                    "    \"width\" : 1000\n" +
                    "  }, {\n" +
                    "    \"height\" : 52,\n" +
                    "    \"url\" : \"https://i.scdn.co/image/4f25297750dfa4051195c36809a9049f6b841a23\",\n" +
                    "    \"width\" : 64\n" +
                    "  } ],\n" +
                    "  \"name\" : \"Band of Horses\",\n" +
                    "  \"popularity\" : 59,\n" +
                    "  \"type\" : \"artist\",\n" +
                    "  \"uri\" : \"spotify:artist:0OdUWJ0sBjDrqHygGUXeCF\"\n" +
                    "}");
}
