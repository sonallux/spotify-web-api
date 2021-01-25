package de.sonallux.spotify.api.authorization.authorization_code;

import de.sonallux.spotify.api.authorization.*;
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
class AuthorizationCodePKCEFlowTest {
    private final static String REDIRECT_URI = "http://example.com/callback";

    private MockWebServer webServer;
    private AuthorizationCodePKCEFlow authCodePKCEFlow;

    @Mock
    TokenStore tokenStore;

    @BeforeEach
    void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        var baseUrl = webServer.url("/");
        authCodePKCEFlow = new AuthorizationCodePKCEFlow("1a2b3c4d5e6f7", REDIRECT_URI, tokenStore, baseUrl);
    }

    @AfterEach
    void teardown() throws IOException{
        webServer.shutdown();
    }

    @Test
    void createAuthorizationUriTest() {
        var codeChallenge = "code42challenge";
        var url = authCodePKCEFlow.createAuthorizationUrl(codeChallenge).build();
        assertEquals("https://accounts.spotify.com/authorize?" +
            "client_id=1a2b3c4d5e6f7&" +
            "response_type=code&" +
            "redirect_uri=http%3A%2F%2Fexample.com%2Fcallback&" +
            "code_challenge_method=S256&" +
            "code_challenge=code42challenge", url.toString());

        var urlWithStateAndScope = authCodePKCEFlow.createAuthorizationUrl(codeChallenge)
            .state("34fFs29kd10")
            .scopes(Scope.USER_LIBRARY_READ, Scope.USER_LIBRARY_MODIFY)
            .build();
        assertEquals("https://accounts.spotify.com/authorize?" +
            "client_id=1a2b3c4d5e6f7&" +
            "response_type=code&" +
            "redirect_uri=http%3A%2F%2Fexample.com%2Fcallback&" +
            "code_challenge_method=S256&" +
            "code_challenge=code42challenge&" +
            "state=34fFs29kd10&" +
            "scope=user-library-read%20user-library-modify", urlWithStateAndScope.toString());

        var urlWithDialog = authCodePKCEFlow.createAuthorizationUrl(codeChallenge)
            .showDialog(true)
            .build();
        assertEquals("https://accounts.spotify.com/authorize?" +
            "client_id=1a2b3c4d5e6f7&" +
            "response_type=code&" +
            "redirect_uri=http%3A%2F%2Fexample.com%2Fcallback&" +
            "code_challenge_method=S256&" +
            "code_challenge=code42challenge&" +
            "show_dialog=true", urlWithDialog.toString());
    }

    @Test
    void exchangeAuthorizationCodeWithSuccessfulTokenRequestTest() throws Exception {
        webServer.enqueue(mockResponseAuthTokens);

        authCodePKCEFlow.exchangeAuthorizationCode(AuthorizationRedirectResponse.success("NApCCgBkWtQ"), "code42verifier");

        assertEquals(1, webServer.getRequestCount());
        assertAuthTokensRequest(webServer.takeRequest(),
            "client_id=1a2b3c4d5e6f7&" +
                "grant_type=authorization_code&" +
                "code=NApCCgBkWtQ&" +
                "redirect_uri=http%3A%2F%2Fexample.com%2Fcallback&" +
                "code_verifier=code42verifier");

        assertStoreAuthTokensFromResponse();
    }

    @Test
    void exchangeAuthorizationCodeWithFailingTokenRequestTest() throws InterruptedException {
        webServer.enqueue(mockResponseBadRequestAuthTokens);

        assertThrows(SpotifyAuthorizationException.class, () ->
            authCodePKCEFlow.exchangeAuthorizationCode(AuthorizationRedirectResponse.success("NApCCgBkWtQ"), "code42verifier"));

        assertEquals(1, webServer.getRequestCount());
        assertAuthTokensRequest(webServer.takeRequest(),
            "client_id=1a2b3c4d5e6f7&" +
                "grant_type=authorization_code&" +
                "code=NApCCgBkWtQ&" +
                "redirect_uri=http%3A%2F%2Fexample.com%2Fcallback&" +
                "code_verifier=code42verifier");

        verify(tokenStore, times(0)).storeTokens(any());
    }

    @Test
    void exchangeAuthorizationCodeFailureTest() {
        assertThrows(SpotifyAuthorizationException.class, () ->
            authCodePKCEFlow.exchangeAuthorizationCode(AuthorizationRedirectResponse.error("access_denied"), "code42verifier"));
        assertEquals(0, webServer.getRequestCount());
    }

    @Test
    void refreshAccessTokenWithSuccessfulRequestTest() throws InterruptedException {
        when(tokenStore.loadTokens())
            .thenReturn(AuthTokens.builder().refreshToken("RgA6ZcjIi6L8bq").build());

        webServer.enqueue(mockResponseAuthTokens);

        assertTrue(authCodePKCEFlow.refreshAccessToken());
        assertEquals(1, webServer.getRequestCount());
        assertAuthTokensRequest(webServer.takeRequest(),
            "client_id=1a2b3c4d5e6f7&" +
                "grant_type=refresh_token&" +
                "refresh_token=RgA6ZcjIi6L8bq");
        assertStoreAuthTokensFromResponse();
    }

    @Test
    void refreshAccessTokenWithFailingRequestTest() throws InterruptedException {
        when(tokenStore.loadTokens())
            .thenReturn(AuthTokens.builder().refreshToken("RgA6ZcjIi6L8bq").build());

        webServer.enqueue(mockResponseBadRequestAuthTokens);

        assertFalse(authCodePKCEFlow.refreshAccessToken());
        assertEquals(1, webServer.getRequestCount());
        assertAuthTokensRequest(webServer.takeRequest(),
            "client_id=1a2b3c4d5e6f7&" +
                "grant_type=refresh_token&" +
                "refresh_token=RgA6ZcjIi6L8bq");
        verify(tokenStore, times(0)).storeTokens(any());
    }

    @Test
    void refreshAccessTokenWithoutTokenTest() {
        when(tokenStore.loadTokens()).thenReturn(null);
        assertFalse(authCodePKCEFlow.refreshAccessToken());
        assertEquals(0, webServer.getRequestCount());

        when(tokenStore.loadTokens()).thenReturn(new AuthTokens());
        assertFalse(authCodePKCEFlow.refreshAccessToken());
        assertEquals(0, webServer.getRequestCount());
    }

    private void assertAuthTokensRequest(RecordedRequest request, String body) {
        assertEquals("POST", request.getMethod());
        assertEquals("/api/token", request.getPath());
        assertEquals("application/x-www-form-urlencoded", request.getHeader("Content-Type"));
        assertEquals(body, request.getBody().readUtf8());
    }

    private void assertStoreAuthTokensFromResponse() {
        verify(tokenStore, times(1)).storeTokens(argThat(authTokens -> {
            assertEquals("NgA6ZcYIixn8bU", authTokens.getAccessToken());
            assertEquals("Bearer", authTokens.getTokenType());
            assertEquals("user-read-private user-read-email", authTokens.getScope());
            assertEquals(3600, authTokens.getExpiresIn());
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
