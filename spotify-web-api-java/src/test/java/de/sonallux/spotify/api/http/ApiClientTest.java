package de.sonallux.spotify.api.http;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ApiClientTest {
    private MockWebServer webServer;
    private ApiClient apiClient;

    @BeforeEach
    void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        var baseUrl = webServer.url("/");
        apiClient = ApiClient.builder()
            .baseUrl(baseUrl)
            .build();
    }

    @Test
    void getRequest() throws Exception {
        webServer.enqueue(new MockResponse()
            .setStatus("HTTP/1.1 200 OK")
            .setBody("{\"id\":123, \"foo\":\"bar\"}")
        );

        var request = new Request("GET", "/get/{id}")
            .addPathParameter("id", "123")
            .addQueryParameter("test", "asdf")
            .addQueryParameter("sort", "asc")
            .addHeaderParameter("test-header", "value");
        var response = apiClient.createApiCall(request, new TypeReference<Map<String, Object>>() {}).executeCall();
        assertTrue(response.isSuccessful());
        assertEquals(200, response.code());
        assertEquals("OK", response.message());
        assertNull(response.errorBody());
        var body = response.body();
        assertNotNull(body);
        assertEquals(2, body.size());
        assertEquals(123, body.get("id"));
        assertEquals("bar", body.get("foo"));

        var recordedRequest = webServer.takeRequest();
        assertEquals("GET /get/123?test=asdf&sort=asc HTTP/1.1", recordedRequest.getRequestLine());
        assertEquals(0, recordedRequest.getBodySize());
        assertEquals("value", recordedRequest.getHeader("test-header"));
    }

    @Test
    void postRequest() throws Exception {
        webServer.enqueue(new MockResponse()
            .setStatus("HTTP/1.1 200 OK")
            .setBody("[\"id\", \"foo\", \"bar\"]")
        );

        var request = new Request("POST", "/post/{id}")
            .addPathParameter("id", "123")
            .addBodyParameter("id", 123)
            .addBodyParameter("test", "foo");
        var response = apiClient.createApiCall(request, new TypeReference<List<String>>() {}).executeCall();
        assertTrue(response.isSuccessful());
        assertEquals(200, response.code());
        assertEquals("OK", response.message());
        assertNull(response.errorBody());
        var body = response.body();
        assertNotNull(body);
        assertEquals(List.of("id", "foo", "bar"), body);

        var recordedRequest = webServer.takeRequest();
        assertEquals("POST /post/123 HTTP/1.1", recordedRequest.getRequestLine());

        assertTrue(recordedRequest.getBodySize() > 0);
        var requestBody = recordedRequest.getBody().readUtf8();
        assertEquals("{\"id\":123,\"test\":\"foo\"}", requestBody);
    }

    @Test
    void getRequestWith401Response() throws Exception {
        webServer.enqueue(new MockResponse()
            .setStatus("HTTP/1.1 401 Unauthorized")
            .setBody("{\"error\": {\"status\": 401,\"message\": \"Invalid access token\"}}")
        );

        var request = new Request("GET", "/get/{id}")
            .addPathParameter("id", "123");
        var response = apiClient.createApiCall(request, new TypeReference<Map<String, Object>>() {}).executeCall();
        assertFalse(response.isSuccessful());
        assertEquals(401, response.code());
        assertEquals("Unauthorized", response.message());
        assertNull(response.body());
        var body = response.errorBody();
        assertNotNull(body);
        assertEquals(401, body.getStatus());
        assertEquals("Invalid access token", body.getMessage());

        var recordedRequest = webServer.takeRequest();
        assertEquals("GET /get/123 HTTP/1.1", recordedRequest.getRequestLine());
        assertEquals(0, recordedRequest.getBodySize());
    }

    @Test
    void getRequestWith502Response() throws Exception {
        webServer.enqueue(new MockResponse()
            .setStatus("HTTP/1.1 502 Bad Gateway")
        );

        var request = new Request("GET", "/get/{id}")
            .addPathParameter("id", "123");
        var response = apiClient.createApiCall(request, new TypeReference<Map<String, Object>>() {}).executeCall();
        assertFalse(response.isSuccessful());
        assertEquals(502, response.code());
        assertEquals("Bad Gateway", response.message());
        assertNull(response.body());
        var body = response.errorBody();
        assertNotNull(body);
        assertEquals(502, body.getStatus());
        assertEquals("Bad Gateway", body.getMessage());

        var recordedRequest = webServer.takeRequest();
        assertEquals("GET /get/123 HTTP/1.1", recordedRequest.getRequestLine());
        assertEquals(0, recordedRequest.getBodySize());
    }

    @Test
    void getRequestWith500Response() throws Exception {
        webServer.enqueue(new MockResponse()
            .setStatus("HTTP/1.1 500 Internal Server Error")
            .setBody("Internal Server Error")
        );

        var request = new Request("GET", "/get/{id}")
            .addPathParameter("id", "123");
        var response = apiClient.createApiCall(request, new TypeReference<Map<String, Object>>() {}).executeCall();
        assertFalse(response.isSuccessful());
        assertEquals(500, response.code());
        assertEquals("Internal Server Error", response.message());
        assertNull(response.body());
        var body = response.errorBody();
        assertNotNull(body);
        assertEquals(500, body.getStatus());
        assertEquals("Internal Server Error", body.getMessage());

        var recordedRequest = webServer.takeRequest();
        assertEquals("GET /get/123 HTTP/1.1", recordedRequest.getRequestLine());
        assertEquals(0, recordedRequest.getBodySize());
    }
}
