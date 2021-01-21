package de.sonallux.spotify.api.authorization.flows;

import de.sonallux.spotify.api.authorization.AuthTokens;
import de.sonallux.spotify.api.authorization.Scope;
import de.sonallux.spotify.api.authorization.TokenStore;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
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
class AuthorizationCodeFlowTest {
    private final static String REDIRECT_URI = "http://example.com/callback";

    private MockWebServer webServer;
    private AuthorizationCodeFlow authCodeFlow;

    @Mock
    TokenStore tokenStore;

    @BeforeEach
    void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        var baseUrl = webServer.url("/");
        authCodeFlow = new AuthorizationCodeFlow("1a2b3c4d5e6f7", "bar456", REDIRECT_URI, tokenStore, baseUrl);
    }

    @AfterEach
    void teardown() throws IOException{
        webServer.shutdown();
    }

    @Test
    void createAuthorizationUriTest() {
        var url = authCodeFlow.createAuthorizationUri().build();
        assertEquals(url.toString(),
            "https://accounts.spotify.com/authorize?client_id=1a2b3c4d5e6f7&response_type=code&redirect_uri=http%3A%2F%2Fexample.com%2Fcallback");

        var urlWithStateAndScope = authCodeFlow.createAuthorizationUri()
            .state("34fFs29kd10")
            .scopes(Scope.USER_LIBRARY_READ, Scope.USER_LIBRARY_MODIFY)
            .build();
        assertEquals(urlWithStateAndScope.toString(),
            "https://accounts.spotify.com/authorize?client_id=1a2b3c4d5e6f7&response_type=code&redirect_uri=http%3A%2F%2Fexample.com%2Fcallback&state=34fFs29kd10&scope=user-library-read%20user-library-modify");

        var urlWithDialog = authCodeFlow.createAuthorizationUri()
            .showDialog(true)
            .build();
        assertEquals(urlWithDialog.toString(),
            "https://accounts.spotify.com/authorize?client_id=1a2b3c4d5e6f7&response_type=code&redirect_uri=http%3A%2F%2Fexample.com%2Fcallback&show_dialog=true");
    }

    @Test
    void onAuthorizationResponseWithSuccessfulTokenRequestTest() throws InterruptedException {
        webServer.enqueue(mockResponseAuthTokens);

        var url = "https://example.com/callback?code=NApCCgBkWtQ&state=example-state-123";
        assertTrue(authCodeFlow.onAuthorizationResponse(url));

        assertEquals(webServer.getRequestCount(), 1);
        assertAuthTokensRequest(webServer.takeRequest(), "grant_type=authorization_code&code=NApCCgBkWtQ&redirect_uri=http%3A%2F%2Fexample.com%2Fcallback");

        assertStoreAuthTokensFromResponse();
    }

    @Test
    void onAuthorizationResponseWithFailingTokenRequestTest() throws InterruptedException {
        webServer.enqueue(mockResponseBadRequestAuthTokens);

        var url = "https://example.com/callback?code=NApCCgBkWtQ&state=example-state-123";
        assertFalse(authCodeFlow.onAuthorizationResponse(url));

        assertEquals(webServer.getRequestCount(), 1);
        assertAuthTokensRequest(webServer.takeRequest(), "grant_type=authorization_code&code=NApCCgBkWtQ&redirect_uri=http%3A%2F%2Fexample.com%2Fcallback");

        verify(tokenStore, times(0)).storeTokens(any());
    }

    @Test
    void onAuthorizationResponseFailureTest() {
        var invalidUrl = "invalid-url";
        assertFalse(authCodeFlow.onAuthorizationResponse(invalidUrl));
        assertEquals(webServer.getRequestCount(), 0);

        var url = "https://example.com/callback?error=access_denied&state=example-state-123";
        assertFalse(authCodeFlow.onAuthorizationResponse(url));
        assertEquals(webServer.getRequestCount(), 0);
    }

    @Test
    void getAuthorizationHeaderValueSuccessTest() {
        when(tokenStore.loadTokens())
            .thenReturn(AuthTokens.builder().tokenType("Bearer").accessToken("PgA6ZceIixL8bU").build());

        assertEquals(authCodeFlow.getAuthorizationHeaderValue(), "Bearer PgA6ZceIixL8bU");
    }

    @Test
    void getAuthorizationHeaderValueFailureTest() {
        when(tokenStore.loadTokens()).thenReturn(null);
        assertNull(authCodeFlow.getAuthorizationHeaderValue());

        when(tokenStore.loadTokens()).thenReturn(new AuthTokens());
        assertNull(authCodeFlow.getAuthorizationHeaderValue());
    }

    @Test
    void refreshAccessTokenWithSuccessfulRequestTest() throws InterruptedException {
        when(tokenStore.loadTokens())
            .thenReturn(AuthTokens.builder().refreshToken("RgA6ZcjIi6L8bq").build());

        webServer.enqueue(mockResponseAuthTokens);

        assertTrue(authCodeFlow.refreshAccessToken());
        assertEquals(webServer.getRequestCount(), 1);
        assertAuthTokensRequest(webServer.takeRequest(), "grant_type=refresh_token&refresh_token=RgA6ZcjIi6L8bq");
        assertStoreAuthTokensFromResponse();
    }

    @Test
    void refreshAccessTokenWithFailingRequestTest() throws InterruptedException {
        when(tokenStore.loadTokens())
            .thenReturn(AuthTokens.builder().refreshToken("RgA6ZcjIi6L8bq").build());

        webServer.enqueue(mockResponseBadRequestAuthTokens);

        assertFalse(authCodeFlow.refreshAccessToken());
        assertEquals(webServer.getRequestCount(), 1);
        assertAuthTokensRequest(webServer.takeRequest(), "grant_type=refresh_token&refresh_token=RgA6ZcjIi6L8bq");
        verify(tokenStore, times(0)).storeTokens(any());
    }

    @Test
    void refreshAccessTokenWithoutTokenTest() {
        when(tokenStore.loadTokens()).thenReturn(null);
        assertFalse(authCodeFlow.refreshAccessToken());
        assertEquals(webServer.getRequestCount(), 0);

        when(tokenStore.loadTokens()).thenReturn(new AuthTokens());
        assertFalse(authCodeFlow.refreshAccessToken());
        assertEquals(webServer.getRequestCount(), 0);
    }

    private void assertAuthTokensRequest(RecordedRequest request, String requestBody) {
        assertEquals(request.getMethod(), "POST");
        assertEquals(request.getPath(), "/api/token");
        assertEquals(request.getHeader("Authorization"), "Basic MWEyYjNjNGQ1ZTZmNzpiYXI0NTY=");
        assertEquals(request.getHeader("Content-Type"), "application/x-www-form-urlencoded");
        assertEquals(request.getBody().readUtf8(), requestBody);
    }

    private void assertStoreAuthTokensFromResponse() {
        verify(tokenStore, times(1)).storeTokens(argThat(authTokens -> {
            assertEquals(authTokens.getAccessToken(), "NgA6ZcYIixn8bU");
            assertEquals(authTokens.getTokenType(), "Bearer");
            assertEquals(authTokens.getScope(), "user-read-private user-read-email");
            assertEquals(authTokens.getExpiresIn(), 3600);
            assertNull(authTokens.getRefreshToken());
            return true;
        }));
    }

    private final MockResponse mockResponseAuthTokens = new MockResponse()
        .setStatus("HTTP/1.1 200 OK")
        .setBody("{\n" +
            "   \"access_token\": \"NgA6ZcYIixn8bU\",\n" +
            "   \"token_type\": \"Bearer\",\n" +
            "   \"scope\": \"user-read-private user-read-email\",\n" +
            "   \"expires_in\": 3600\n" +
            "}");

    private final MockResponse mockResponseBadRequestAuthTokens = new MockResponse()
        .setStatus("HTTP/1.1 400 Bad Request")
        .setBody("{\"error\":\"invalid_client\",\"error_description\":\"Invalid client\"}");
}
