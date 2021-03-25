package de.sonallux.spotify.api.http;

import okhttp3.HttpUrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestTest {

    @Mock
    ApiClient apiClient;

    @Test
    void testBodyParametersAndFormFieldThrowsException() {
        var request = new Request("PUT", "/")
            .addBodyParameter("body", "parameter")
            .addFormUrlEncodedField("form", "parameter");

        assertThrows(IllegalArgumentException.class, () -> request.toOkHttpRequest(apiClient));
    }

    private static Stream<Arguments> provideUrls() {
        return Stream.of(
            Arguments.of("https://test-api.de/v1", "/", "https://test-api.de/v1"),
            Arguments.of("https://test-api.de/v1", "", "https://test-api.de/v1"),
            Arguments.of("https://test-api.de/v1/", "/", "https://test-api.de/v1/"),
            Arguments.of("https://test-api.de/v1/", "", "https://test-api.de/v1/"),
            Arguments.of("https://test-api.de/v1", "/test", "https://test-api.de/v1/test"),
            Arguments.of("https://test-api.de/v1", "test", "https://test-api.de/v1/test"),
            Arguments.of("https://test-api.de/v1/", "/test", "https://test-api.de/v1/test"),
            Arguments.of("https://test-api.de/v1/", "test", "https://test-api.de/v1/test"),
            Arguments.of("https://test-api.de/v1", "/test/foo", "https://test-api.de/v1/test/foo"),
            Arguments.of("https://test-api.de/v1", "test/foo", "https://test-api.de/v1/test/foo"),
            Arguments.of("https://test-api.de/v1", "/test/foo/", "https://test-api.de/v1/test/foo/"),
            Arguments.of("https://test-api.de/v1", "test/foo/", "https://test-api.de/v1/test/foo/")
        );
    }

    @ParameterizedTest
    @MethodSource("provideUrls")
    void testCreateCorrectUrl(String baseUrl, String path, String expectedUrl) throws IOException {
        when(apiClient.getBaseUrl()).thenReturn(HttpUrl.get(baseUrl));
        var request = new Request("GET", path)
            .toOkHttpRequest(apiClient).build();
        assertEquals(expectedUrl, request.url().toString());
    }
}
