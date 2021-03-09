package de.sonallux.spotify.api;

import de.sonallux.spotify.api.authorization.ApiAuthorizationProvider;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpotifyApiTest {
    @Mock
    ApiAuthorizationProvider apiAuthorizationProvider;

    private MockWebServer webServer;
    private SpotifyApi api;

    @BeforeEach
    void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        var baseUrl = webServer.url("/");
        api = new SpotifyApi(apiAuthorizationProvider, baseUrl);
    }

    @AfterEach
    void teardown() throws IOException{
        webServer.shutdown();
    }

    @Test
    void addNoAuthorizationHeaderToRequest() throws Exception {
        when(apiAuthorizationProvider.getAuthorizationHeaderValue()).thenReturn(null);
        webServer.enqueue(mockResponseArtist);

        var artist = api.callApiAndReturnBody(api.getArtistsApi().getArtist("foo"));
        assertNotNull(artist);
        assertEquals("0OdUWJ0sBjDrqHygGUXeCF", artist.getId());
        assertEquals("https://open.spotify.com/artist/0OdUWJ0sBjDrqHygGUXeCF", artist.getExternalUrls().getSpotify());

        assertEquals(webServer.getRequestCount(), 1);
        var request = webServer.takeRequest();
        assertEquals(request.getRequestLine(), "GET /artists/foo HTTP/1.1");
        assertNull(request.getHeader("Authorization"));
    }

    @Test
    void addAuthorizationHeaderToRequest() throws Exception {
        when(apiAuthorizationProvider.getAuthorizationHeaderValue()).thenReturn("Bearer some-access-token");
        webServer.enqueue(mockResponseArtist);

        var artist = api.callApiAndReturnBody(api.getArtistsApi().getArtist("foo"));
        assertNotNull(artist);
        assertEquals("0OdUWJ0sBjDrqHygGUXeCF", artist.getId());
        assertEquals("https://open.spotify.com/artist/0OdUWJ0sBjDrqHygGUXeCF", artist.getExternalUrls().getSpotify());

        assertEquals(webServer.getRequestCount(), 1);
        var request = webServer.takeRequest();
        assertEquals(request.getRequestLine(), "GET /artists/foo HTTP/1.1");
        assertEquals(request.getHeader("Authorization"), "Bearer some-access-token");
    }

    @Test
    void handlesUnauthorizedResponseWithRetry() throws Exception {
        when(apiAuthorizationProvider.getAuthorizationHeaderValue()).thenReturn(null);
        when(apiAuthorizationProvider.refreshAccessToken()).thenReturn(true);
        webServer.enqueue(mockResponseUnauthorizedInvalidToken);
        webServer.enqueue(mockResponseArtist);

        var artist = api.callApiAndReturnBody(api.getArtistsApi().getArtist("foo"));
        assertNotNull(artist);
        assertEquals("0OdUWJ0sBjDrqHygGUXeCF", artist.getId());

        verify(apiAuthorizationProvider, times(1)).refreshAccessToken();

        assertEquals(webServer.getRequestCount(), 2);
        var request1 = webServer.takeRequest();
        assertEquals(request1.getRequestLine(), "GET /artists/foo HTTP/1.1");
        var request2 = webServer.takeRequest();
        assertEquals(request2.getRequestLine(), "GET /artists/foo HTTP/1.1");
    }

    @Test
    void handlesUnauthorizedResponseWithFailure() throws Exception {
        when(apiAuthorizationProvider.getAuthorizationHeaderValue()).thenReturn(null);
        when(apiAuthorizationProvider.refreshAccessToken()).thenReturn(false);
        webServer.enqueue(mockResponseUnauthorizedInvalidToken);

        assertThrows(SpotifyApiException.class, () -> api.callApiAndReturnBody(api.getArtistsApi().getArtist("foo")));

        verify(apiAuthorizationProvider, times(1)).refreshAccessToken();

        assertEquals(webServer.getRequestCount(), 1);
        var request1 = webServer.takeRequest();
        assertEquals(request1.getRequestLine(), "GET /artists/foo HTTP/1.1");
    }

    private final MockResponse mockResponseUnauthorizedInvalidToken = new MockResponse()
        .setStatus("HTTP/1.1 401 Unauthorized")
        .setBody("{\"error\": {\"status\": 401,\"message\": \"Invalid access token\"}}");

    private final MockResponse mockResponseArtist = new MockResponse()
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
