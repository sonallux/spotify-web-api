package de.sonallux.spotify.api.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import okhttp3.internal.http.HttpMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class Request {
    private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json; charset=UTF-8");

    private final String method;
    private final String path;
    private final Map<String, String> pathParameter = new HashMap<>();
    private final Map<String, String> queryParameters = new LinkedHashMap<>();
    private final Map<String, String> headerParameters = new LinkedHashMap<>();
    private final Map<String, Object> bodyParameters = new LinkedHashMap<>();
    private final Map<String, String> formParameters = new LinkedHashMap<>();

    /**
     * Add a path parameter
     */
    public Request addPathParameter(String key, String value) {
        pathParameter.put(key, value);
        return this;
    }

    /**
     * Add a query parameter
     */
    public Request addQueryParameter(String key, String value) {
        queryParameters.put(key, value);
        return this;
    }

    /**
     * Add a header parameter.
     */
    public Request addHeaderParameter(String key, String value) {
        headerParameters.put(key, value);
        return this;
    }

    /**
     * Add a parameter that is passed in the request body.
     */
    public Request addBodyParameter(String key, Object value) {
        bodyParameters.put(key, value);
        return this;
    }

    /**
     * Add a field for a form url encoded request
     */
    public Request addFormUrlEncodedField(String key, String value) {
        formParameters.put(key, value);
        return this;
    }

    okhttp3.Request.Builder toOkHttpRequest(ApiClient apiClient) throws IOException {
        var requestBody = createRequestBody(apiClient.getObjectMapper());
        if (requestBody == null && HttpMethod.requiresRequestBody(getMethod())) {
            requestBody = RequestBody.create(new byte[0]);
        }
        return new okhttp3.Request.Builder()
            .url(createFullUrl(apiClient.getBaseUrl()))
            .method(getMethod(), requestBody)
            .headers(Headers.of(getHeaderParameters()));
    }

    private RequestBody createRequestBody(ObjectMapper objectMapper) throws IOException {
        if (getBodyParameters().size() > 0 && getFormParameters().size() > 0) {
            throw new IllegalArgumentException("Can not use body parameters and form fields in one request");
        } else if (getBodyParameters().size() > 0) {
            byte[] bytes = objectMapper.writeValueAsBytes(getBodyParameters());
            return RequestBody.create(bytes, JSON_MEDIA_TYPE);
        } else if (getFormParameters().size() > 0) {
            var formBuilder = new FormBody.Builder();
            getFormParameters().forEach(formBuilder::add);
            return formBuilder.build();
        }
        return null;
    }

    private HttpUrl createFullUrl(HttpUrl baseUrl) {
        var relativeUrl = getPath();
        for (var entry : getPathParameter().entrySet()) {
            relativeUrl = relativeUrl.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        if (relativeUrl.startsWith("/")) {
            relativeUrl = relativeUrl.substring(1);
        }

        var urlBuilder = baseUrl.newBuilder();
        if (!relativeUrl.isBlank()) {
            urlBuilder.addPathSegments(relativeUrl);
        }
        for (var entry : getQueryParameters().entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return urlBuilder.build();
    }
}
