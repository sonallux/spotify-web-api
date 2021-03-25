package de.sonallux.spotify.api.http;

import de.sonallux.spotify.api.models.Error;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {

    public static <T> Response<T> success(T body, okhttp3.Response rawResponse) {
        return new Response<>(rawResponse, body, null);
    }

    public static <T> Response<T> error(Error error, okhttp3.Response rawResponse) {
        return new Response<>(rawResponse, null, error);
    }

    private final okhttp3.Response rawResponse;
    private final T body;
    private final Error error;

    /**
     * Returns the underlying OkHttp response object
     */
    public okhttp3.Response raw() {
        return rawResponse;
    }

    /**
     * Returns the HTTP status code
     */
    public int code() {
        return rawResponse.code();
    }

    /**
     * Returns the HTTP status message
     */
    public String message() {
        return rawResponse.message();
    }

    /**
     * Returns true if the code is in [200..300), which means the request was successful.
     */
    public boolean isSuccessful() {
        return rawResponse.isSuccessful();
    }

    /**
     * Returns the parsed response body for successful responses. null is returned if the response type is Void or
     * the response has no body.
     * For non successful response this is always null. Use {{@link #errorBody()}} to get the associated error.
     */
    public T body() {
        return body;
    }

    /**
     * Returns an error representation for non successful responses. This is null if the response is successful.
     */
    public Error errorBody() {
        return error;
    }
}
