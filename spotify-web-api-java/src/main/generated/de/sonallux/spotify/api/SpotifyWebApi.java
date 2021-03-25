package de.sonallux.spotify.api;

import de.sonallux.spotify.api.apis.*;
import de.sonallux.spotify.api.http.ApiClient;
import lombok.Getter;
import okhttp3.HttpUrl;

@Getter
public class SpotifyWebApi  {
    public static final HttpUrl SPOTIFY_WEB_API_ENDPOINT = HttpUrl.get("https://api.spotify.com/v1");

    private final AlbumsApi albumsApi;
    private final ArtistsApi artistsApi;
    private final BrowseApi browseApi;
    private final EpisodesApi episodesApi;
    private final FollowApi followApi;
    private final LibraryApi libraryApi;
    private final MarketsApi marketsApi;
    private final PersonalizationApi personalizationApi;
    private final PlayerApi playerApi;
    private final PlaylistsApi playlistsApi;
    private final SearchApi searchApi;
    private final ShowsApi showsApi;
    private final TracksApi tracksApi;
    private final UsersProfileApi usersProfileApi;

    SpotifyWebApi(ApiClient apiClient) {
        this.albumsApi = new AlbumsApi(apiClient);
        this.artistsApi = new ArtistsApi(apiClient);
        this.browseApi = new BrowseApi(apiClient);
        this.episodesApi = new EpisodesApi(apiClient);
        this.followApi = new FollowApi(apiClient);
        this.libraryApi = new LibraryApi(apiClient);
        this.marketsApi = new MarketsApi(apiClient);
        this.personalizationApi = new PersonalizationApi(apiClient);
        this.playerApi = new PlayerApi(apiClient);
        this.playlistsApi = new PlaylistsApi(apiClient);
        this.searchApi = new SearchApi(apiClient);
        this.showsApi = new ShowsApi(apiClient);
        this.tracksApi = new TracksApi(apiClient);
        this.usersProfileApi = new UsersProfileApi(apiClient);
    }

    public static SpotifyWebApiBuilder builder() {
        return new SpotifyWebApiBuilder();
    }
}
