package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#followers-object">FollowersObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Followers {
    /**
     * <p>A link to the Web API endpoint providing full details of the followers; null if not available. Please note that this will always be set to null, as the Web API does not support it at the moment.</p>
     */
    private String href;
    /**
     * <p>The total number of followers.</p>
     */
    private Integer total;
}
