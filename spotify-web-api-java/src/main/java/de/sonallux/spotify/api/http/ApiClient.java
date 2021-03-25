package de.sonallux.spotify.api.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Objects;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ApiClient {
    private final HttpUrl baseUrl;
    private final Call.Factory callFactory;
    private final ObjectMapper objectMapper;
    private final ApiCall.Factory apiCallFactory;

    public <T> ApiCall<T> createApiCall(Request request, TypeReference<T> responseType) {
        return apiCallFactory.createApiCall(this, request, responseType);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private HttpUrl baseUrl;
        private Call.Factory callFactory;
        private ObjectMapper objectMapper;
        private ApiCall.Factory apiCallFactory;

        public Builder(){}

        public Builder baseUrl(String baseUrl) {
            Objects.requireNonNull(baseUrl, "baseUrl == null");
            return baseUrl(HttpUrl.get(baseUrl));
        }

        public Builder baseUrl(HttpUrl baseUrl) {
            this.baseUrl = Objects.requireNonNull(baseUrl, "baseUrl == null");
            return this;
        }

        public Builder httpClient(OkHttpClient httpClient) {
            return callFactory(Objects.requireNonNull(httpClient, "httpClient == null"));
        }

        public Builder callFactory(Call.Factory factory) {
            this.callFactory = Objects.requireNonNull(factory, "factory == null");
            return this;
        }

        public Builder objectMapper(ObjectMapper objectMapper) {
            this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper == null");
            return this;
        }

        public Builder apiCallFactory(ApiCall.Factory apiCallFactory) {
            this.apiCallFactory = Objects.requireNonNull(apiCallFactory, "apiCallFactory == null");
            return this;
        }

        public ApiClient build() {
            if (baseUrl == null) {
                throw new IllegalStateException("Base URL required.");
            }

            if (objectMapper == null) {
                objectMapper = new ObjectMapper();
            }

            if (callFactory == null) {
                callFactory = new OkHttpClient();
            }

            if (apiCallFactory == null) {
                apiCallFactory = new ApiCall.Factory();
            }

            return new ApiClient(baseUrl, callFactory, objectMapper, apiCallFactory);
        }
    }
}
