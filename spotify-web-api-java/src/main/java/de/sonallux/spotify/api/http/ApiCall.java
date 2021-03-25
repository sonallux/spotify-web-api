package de.sonallux.spotify.api.http;

import com.fasterxml.jackson.core.type.TypeReference;
import de.sonallux.spotify.api.SpotifyApiException;
import de.sonallux.spotify.api.models.Error;
import de.sonallux.spotify.api.models.ErrorResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import okio.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ApiCall<T> {
    private static final TypeReference<ErrorResponse> ERROR_RESPONSE_TYPE = new TypeReference<>() {};

    private final ApiClient apiClient;
    private final Request request;
    private final TypeReference<T> responseType;

    private volatile boolean canceled;
    private Call rawCall;
    private Throwable creationFailure; // RuntimeException | IOException
    private boolean executed;

    /**
     * Returns the raw call, initializing it if necessary. Throws if initializing the raw call throws,
     * or has thrown in previous attempts to create it.
     */
    protected okhttp3.Call getRawCall() throws IOException {
        if (rawCall != null) {
            return rawCall;
        }

        // Re-throw previous failures if this isn't the first attempt.
        if (creationFailure != null) {
            if (creationFailure instanceof IOException) {
                throw (IOException) creationFailure;
            } else {
                throw (RuntimeException) creationFailure;
            }
        }

        // Create and remember either the success or the failure.
        try {
            var rawRequest = request.toOkHttpRequest(apiClient).build();
            return rawCall = apiClient.getCallFactory().newCall(rawRequest);
        } catch (RuntimeException | IOException e) {
            creationFailure = e;
            throw e;
        }
    }

    public synchronized boolean isExecuted() {
        return executed;
    }

    public Response<T> executeCall() throws IOException {
        okhttp3.Call call;

        synchronized (this) {
            if (executed) throw new IllegalStateException("Already executed.");
            executed = true;

            call = getRawCall();
        }

        if (canceled) {
            call.cancel();
        }

        return parseResponse(call.execute());
    }

    public T execute() throws SpotifyApiException {
        Response<T> response;
        try {
            response = executeCall();
        } catch (IOException e) {
            throw new SpotifyApiException("Failed to make api call", e);
        }
        if (response.isSuccessful()) {
            return response.body();
        }
        throw new SpotifyApiException("Api call failed", response.errorBody());
    }

    protected Response<T> parseResponse(okhttp3.Response rawResponse) throws IOException {
        try (ResponseBody rawBody = rawResponse.body()) {
            if (rawResponse.isSuccessful()) {
                var code = rawResponse.code();
                if (responseType.getType() == Void.class || code == 204 || code == 205) {
                    return Response.success(null, rawResponse);
                }

                ExceptionCatchingResponseBody catchingBody = new ExceptionCatchingResponseBody(rawBody);
                try {
                    T body = parseResponseBody(catchingBody, responseType);
                    return Response.success(body, rawResponse);
                } catch (RuntimeException e) {
                    // If the underlying source threw an exception, propagate that rather than indicating it was
                    // a runtime exception.
                    catchingBody.throwIfCaught();
                    throw e;
                }
            }
            Error error = getErrorBody(rawBody);
            if (error == null) {
                error = getErrorDetailsFromResponse(rawResponse);
            }
            return Response.error(error, rawResponse);
        }
    }

    private <RT> RT parseResponseBody(ResponseBody responseBody, TypeReference<RT> responseType) throws IOException {
        try (responseBody) {
            return apiClient.getObjectMapper().readValue(responseBody.byteStream(), responseType);
        }
    }

    private Error getErrorBody(ResponseBody rawResponse) {
        if (rawResponse.contentLength() == 0) {
            return null;
        }
        try {
            var errorResponse = parseResponseBody(rawResponse, ERROR_RESPONSE_TYPE);
            if (errorResponse != null) {
                return errorResponse.getError();
            }
        }
        catch (IOException ignore) {}
        return null;
    }

    private Error getErrorDetailsFromResponse(okhttp3.Response response) {
        var error = new Error();
        error.setStatus(response.code());
        error.setMessage(response.message());
        return error;
    }

    public void cancel() {
        canceled = true;

        okhttp3.Call call;
        synchronized (this) {
            call = rawCall;
        }
        if (call != null) {
            call.cancel();
        }
    }

    public boolean isCanceled() {
        if (canceled) {
            return true;
        }
        synchronized (this) {
            return rawCall != null && rawCall.isCanceled();
        }
    }

    static final class ExceptionCatchingResponseBody extends ResponseBody {
        private final ResponseBody delegate;
        private final BufferedSource delegateSource;
        private IOException thrownException;

        ExceptionCatchingResponseBody(ResponseBody delegate) {
            this.delegate = delegate;
            this.delegateSource =
                Okio.buffer(
                    new ForwardingSource(delegate.source()) {
                        @Override
                        public long read(@NotNull Buffer sink, long byteCount) throws IOException {
                            try {
                                return super.read(sink, byteCount);
                            } catch (IOException e) {
                                thrownException = e;
                                throw e;
                            }
                        }
                    });
        }

        @Override
        public MediaType contentType() {
            return delegate.contentType();
        }

        @Override
        public long contentLength() {
            return delegate.contentLength();
        }

        @NotNull
        @Override
        public BufferedSource source() {
            return delegateSource;
        }

        @Override
        public void close() {
            delegate.close();
        }

        void throwIfCaught() throws IOException {
            if (thrownException != null) {
                throw thrownException;
            }
        }
    }

    public static class Factory {
        public <T> ApiCall<T> createApiCall(ApiClient apiClient, Request request, TypeReference<T> responseType) {
            return new ApiCall<>(apiClient, request, responseType);
        }
    }
}
