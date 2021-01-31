package de.sonallux.spotify.api;

import de.sonallux.spotify.api.models.Error;
import de.sonallux.spotify.api.models.ErrorResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;

@AllArgsConstructor
public class BaseSpotifyApi {

    @Getter(AccessLevel.PROTECTED)
    private final Retrofit retrofit;

    public <T> T callApiAndReturnBody(Call<T> call) throws SpotifyApiException {
        return getBody(callApi(call));
    }

    public <T> retrofit2.Response<T> callApi(Call<T> call) throws SpotifyApiException {
        try {
            return call.execute();
        }
        catch (IOException e) {
            throw new SpotifyApiException("Failed to make api call", e);
        }
    }

    public <T> T getBody(retrofit2.Response<T> response) throws SpotifyApiException {
        if (response.isSuccessful() && response.body() != null) {
            return response.body();
        }

        throw new SpotifyApiException("Api call failed", getErrorBody(response));
    }

    public Error getErrorBody(retrofit2.Response<?> response) throws SpotifyApiException {
        if (response.errorBody() == null) {
            throw new SpotifyApiException("Failed to get error body", getErrorDetailsFromResponse(response));
        }
        try {
            Converter<ResponseBody, ErrorResponse> converter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);
            ErrorResponse errorResponse = converter.convert(response.errorBody());
            if (errorResponse != null) {
                return errorResponse.getError();
            }
        }
        catch (IOException ignore) {}
        throw new SpotifyApiException("Failed to parse error body", getErrorDetailsFromResponse(response));
    }

    public Error getErrorDetailsFromResponse(retrofit2.Response<?> response) {
        Error error = new Error();
        error.setStatus(response.code());
        error.setMessage(response.message());
        return error;
    }
}
