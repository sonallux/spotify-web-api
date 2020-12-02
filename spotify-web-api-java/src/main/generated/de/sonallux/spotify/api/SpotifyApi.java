package de.sonallux.spotify.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sonallux.spotify.api.apis.*;
import de.sonallux.spotify.api.models.ErrorDetails;
import de.sonallux.spotify.api.models.ErrorResponse;
import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;

@Getter
public class SpotifyApi {
    public static final String SPOTIFY_WEB_API_ENDPOINT = "https://api.spotify.com/v1";

    private final Retrofit retrofit;

    private final AlbumsApi albumsApi;
    private final ArtistsApi artistsApi;
    private final BrowseApi browseApi;
    private final EpisodesApi episodesApi;
    private final FollowApi followApi;
    private final LibraryApi libraryApi;
    private final PersonalizationApi personalizationApi;
    private final PlayerApi playerApi;
    private final PlaylistsApi playlistsApi;
    private final SearchApi searchApi;
    private final ShowsApi showsApi;
    private final TracksApi tracksApi;
    private final UsersProfileApi usersProfileApi;

    public SpotifyApi(Retrofit retrofit) {
        this.retrofit = retrofit;
        this.albumsApi = retrofit.create(AlbumsApi.class);
        this.artistsApi = retrofit.create(ArtistsApi.class);
        this.browseApi = retrofit.create(BrowseApi.class);
        this.episodesApi = retrofit.create(EpisodesApi.class);
        this.followApi = retrofit.create(FollowApi.class);
        this.libraryApi = retrofit.create(LibraryApi.class);
        this.personalizationApi = retrofit.create(PersonalizationApi.class);
        this.playerApi = retrofit.create(PlayerApi.class);
        this.playlistsApi = retrofit.create(PlaylistsApi.class);
        this.searchApi = retrofit.create(SearchApi.class);
        this.showsApi = retrofit.create(ShowsApi.class);
        this.tracksApi = retrofit.create(TracksApi.class);
        this.usersProfileApi = retrofit.create(UsersProfileApi.class);
    }

    public SpotifyApi(OkHttpClient okHttpClient) {
        this(createDefaultRetrofit(okHttpClient, HttpUrl.get(SPOTIFY_WEB_API_ENDPOINT)));
    }

    public SpotifyApi(OkHttpClient okHttpClient, HttpUrl baseUrl) {
        this(createDefaultRetrofit(okHttpClient, baseUrl));
    }

    public SpotifyApi(HttpUrl baseUrl) {
        this(new OkHttpClient(), baseUrl);
    }

    public SpotifyApi() {
        this(new OkHttpClient());
    }

    private static Retrofit createDefaultRetrofit(OkHttpClient okHttpClient, HttpUrl baseUrl) {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
    }

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

    public ErrorDetails getErrorBody(retrofit2.Response<?> response) throws SpotifyApiException {
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

    public ErrorDetails getErrorDetailsFromResponse(retrofit2.Response<?> response) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(response.code());
        errorDetails.setMessage(response.message());
        return errorDetails;
    }
}
