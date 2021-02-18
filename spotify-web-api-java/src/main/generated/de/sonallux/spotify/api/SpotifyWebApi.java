package de.sonallux.spotify.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sonallux.spotify.api.apis.*;
import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Getter
public class SpotifyWebApi extends BaseSpotifyApi  {
    public static final HttpUrl SPOTIFY_WEB_API_ENDPOINT = HttpUrl.get("https://api.spotify.com/v1");

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

    public SpotifyWebApi(Retrofit retrofit) {
        super(retrofit);
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

    public SpotifyWebApi(OkHttpClient okHttpClient, HttpUrl baseUrl) {
        this(createDefaultRetrofit(okHttpClient, baseUrl));
    }

    public SpotifyWebApi(HttpUrl baseUrl) {
        this(new OkHttpClient(), baseUrl);
    }

    public SpotifyWebApi(OkHttpClient okHttpClient) {
        this(okHttpClient, SPOTIFY_WEB_API_ENDPOINT);
    }

    public SpotifyWebApi() {
        this(new OkHttpClient());
    }

    private static Retrofit createDefaultRetrofit(OkHttpClient okHttpClient, HttpUrl baseUrl) {
        var mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
    }
}
