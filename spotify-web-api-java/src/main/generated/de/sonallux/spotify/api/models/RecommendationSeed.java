package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-recommendationseedobject">RecommendationSeedObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class RecommendationSeed {
    /**
     * <p>The number of tracks available after min_* and max_* filters have been applied.</p>
     */
    private Integer afterFilteringSize;
    /**
     * <p>The number of tracks available after relinking for regional availability.</p>
     */
    private Integer afterRelinkingSize;
    /**
     * <p>A link to the full track or artist data for this seed. For tracks this will be a link to a <a href="https://developer.spotify.com/documentation/web-api/reference/#object-trackobject">Track Object</a>. For artists a link to <a href="https://developer.spotify.com/documentation/web-api/reference/#object-artistobject">an Artist Object</a>. For genre seeds, this value will be <code>null</code>.</p>
     */
    private String href;
    /**
     * <p>The id used to select this seed. This will be the same as the string used in the <code>seed_artists</code>, <code>seed_tracks</code> or <code>seed_genres</code> parameter.</p>
     */
    private String id;
    /**
     * <p>The number of recommended tracks available for this seed.</p>
     */
    private Integer initialPoolSize;
    /**
     * <p>The entity type of this seed. One of <code>artist</code>, <code>track</code> or <code>genre</code>.</p>
     */
    private String type;
}
