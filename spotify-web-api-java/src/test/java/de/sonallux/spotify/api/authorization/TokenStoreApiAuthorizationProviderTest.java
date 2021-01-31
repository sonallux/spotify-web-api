package de.sonallux.spotify.api.authorization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenStoreApiAuthorizationProviderTest {

    @Mock
    TokenStore tokenStore;

    private TokenStoreApiAuthorizationProvider authProvider;

    @BeforeEach
    void setup() {
        authProvider = new TokenStoreApiAuthorizationProvider(tokenStore);
    }

    @Test
    void getAuthorizationHeaderValueSuccessTest() {
        when(tokenStore.loadTokens())
            .thenReturn(AuthTokens.builder().tokenType("Bearer").accessToken("PgA6ZceIixL8bU").build());

        assertEquals("Bearer PgA6ZceIixL8bU", authProvider.getAuthorizationHeaderValue());
    }

    @Test
    void getAuthorizationHeaderValueFailureTest() {
        when(tokenStore.loadTokens()).thenReturn(null);
        assertNull(authProvider.getAuthorizationHeaderValue());

        when(tokenStore.loadTokens()).thenReturn(new AuthTokens());
        assertNull(authProvider.getAuthorizationHeaderValue());
    }
}
