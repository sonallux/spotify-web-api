package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * Request body for endpoint <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-save-episodes-user">Save Episodes for User</a>
 */
@Getter
@Setter
@AllArgsConstructor
public class SaveEpisodesUserRequest {
    /**
     * <p>A JSON array of the <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify IDs</a>.<br>
     * A maximum of 50 items can be specified in one request. <em>Note: if the <code>ids</code> parameter is present in the query string, any IDs listed here in the body will be ignored.</em></p>
     */
    @NonNull
    private final java.util.List<String> ids;
}
