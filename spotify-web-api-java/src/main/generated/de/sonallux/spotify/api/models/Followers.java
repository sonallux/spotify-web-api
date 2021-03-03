package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-followersobject">FollowersObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Followers {
    /**
     * <p>A link to the Web API endpoint providing full details of the followers; <code>null</code> if not available. Please note that this will always be set to null, as the Web API does not support it at the moment.</p>
     */
    private String href;
    /**
     * <p>The total number of followers.</p>
     */
    private int total;
}
