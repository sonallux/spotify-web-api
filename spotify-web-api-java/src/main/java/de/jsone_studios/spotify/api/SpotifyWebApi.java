package de.jsone_studios.spotify.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jsone_studios.spotify.api.apis.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SpotifyWebApi implements SpotifyApi {
    public static final String SPOTIFY_WEB_API_ENDPOINT = "https://api.spotify.com/v1/";

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

    public SpotifyWebApi(Retrofit retrofit) {
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

    public SpotifyWebApi(OkHttpClient okHttpClient) {
        this(createDefaultRetrofit(okHttpClient, HttpUrl.get(SPOTIFY_WEB_API_ENDPOINT)));
    }

    public SpotifyWebApi(OkHttpClient okHttpClient, HttpUrl baseUrl) {
        this(createDefaultRetrofit(okHttpClient, baseUrl));
    }

    public SpotifyWebApi(HttpUrl baseUrl) {
        this(new OkHttpClient(), baseUrl);
    }

    public SpotifyWebApi() {
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

    @Override
    public Retrofit getRetrofit() {
        return retrofit;
    }

    @Override
    public AlbumsApi getAlbumsApi() {
        return albumsApi;
    }

    @Override
    public ArtistsApi getArtistsApi() {
        return artistsApi;
    }

    @Override
    public BrowseApi getBrowseApi() {
        return browseApi;
    }

    @Override
    public EpisodesApi getEpisodesApi() {
        return episodesApi;
    }

    @Override
    public FollowApi getFollowApi() {
        return followApi;
    }

    @Override
    public LibraryApi getLibraryApi() {
        return libraryApi;
    }

    @Override
    public PersonalizationApi getPersonalizationApi() {
        return personalizationApi;
    }

    @Override
    public PlayerApi getPlayerApi() {
        return playerApi;
    }

    @Override
    public PlaylistsApi getPlaylistApi() {
        return playlistsApi;
    }

    @Override
    public SearchApi getSearchApi() {
        return searchApi;
    }

    @Override
    public ShowsApi getShowsApi() {
        return showsApi;
    }

    @Override
    public TracksApi getTracksApi() {
        return tracksApi;
    }

    @Override
    public UsersProfileApi getUsersProfileApi() {
        return usersProfileApi;
    }
}
