package de.sonallux.spotify.api.authorization.implicit_grant;

import de.sonallux.spotify.api.authorization.AuthorizationRedirectResponse;
import de.sonallux.spotify.api.authorization.Scope;
import de.sonallux.spotify.api.authorization.TokenStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplicitGrantFlowTest {
    private final static String REDIRECT_URI = "http://example.com/callback";

    private ImplicitGrantFlow implicitGrantFlow;

    @Mock
    TokenStore tokenStore;

    @BeforeEach
    void setup() {
        implicitGrantFlow = new ImplicitGrantFlow("1a2b3c4d5e6f7", REDIRECT_URI, tokenStore);
    }

    @Test
    void createAuthorizationUriTest() {
        var url = implicitGrantFlow.createAuthorizationUrl().build();
        assertEquals("https://accounts.spotify.com/authorize?" +
            "client_id=1a2b3c4d5e6f7&" +
            "response_type=token&" +
            "redirect_uri=http%3A%2F%2Fexample.com%2Fcallback", url.toString());

        var urlWithStateAndScope = implicitGrantFlow.createAuthorizationUrl()
            .state("34fFs29kd10")
            .scopes(Scope.USER_LIBRARY_READ, Scope.USER_LIBRARY_MODIFY)
            .build();
        assertEquals("https://accounts.spotify.com/authorize?" +
            "client_id=1a2b3c4d5e6f7&" +
            "response_type=token&" +
            "redirect_uri=http%3A%2F%2Fexample.com%2Fcallback&" +
            "state=34fFs29kd10&" +
            "scope=user-library-read%20user-library-modify", urlWithStateAndScope.toString());

        var urlWithDialog = implicitGrantFlow.createAuthorizationUrl()
            .showDialog(true)
            .build();
        assertEquals("https://accounts.spotify.com/authorize?" +
            "client_id=1a2b3c4d5e6f7&" +
            "response_type=token&" +
            "redirect_uri=http%3A%2F%2Fexample.com%2Fcallback&" +
            "show_dialog=true", urlWithDialog.toString());
    }

    @Test
    void parseAuthorizationRedirectResponseSuccessTest() {
        var url = "https://example.com/callback#access_token=NwAExzBV3O2Tk&token_type=Bearer&expires_in=3600&state=abc123";

        var response = implicitGrantFlow.parseAuthorizationRedirectResponse(url);
        assertTrue(response.isSuccess());
        assertEquals("NwAExzBV3O2Tk", response.getBody().getAccessToken());
        assertEquals("Bearer", response.getBody().getTokenType());
        assertEquals(3600, response.getBody().getExpiresIn());
        assertEquals("abc123", response.getState());
        assertNull(response.getError());
    }

    @Test
    void parseAuthorizationRedirectResponseFailureTest() {
        var url = "https://example.com/callback?error=access_denied&state=abc123";

        var response = implicitGrantFlow.parseAuthorizationRedirectResponse(url);
        assertFalse(response.isSuccess());
        assertEquals("access_denied", response.getError());
        assertEquals("abc123", response.getState());
        assertNull(response.getBody());
    }

    @Test
    void useResponseSuccessTest() {
        implicitGrantFlow.useResponse(AuthorizationRedirectResponse.success(new RedirectResponseBody("NAE4xzBV372Tk", "Bearer", 3600)));

        verify(tokenStore, times(1)).storeTokens(argThat(tokens -> {
            assertEquals("NAE4xzBV372Tk", tokens.getAccessToken());
            assertEquals("Bearer", tokens.getTokenType());
            assertEquals(3600, tokens.getExpiresIn());
            assertNull(tokens.getScope());
            assertNull(tokens.getRefreshToken());
            return true;
        }));
    }

    @Test
    void useResponseFailureTest() {
        implicitGrantFlow.useResponse(AuthorizationRedirectResponse.error("access_denied"));

        verify(tokenStore, times(0)).storeTokens(any());
    }
}
