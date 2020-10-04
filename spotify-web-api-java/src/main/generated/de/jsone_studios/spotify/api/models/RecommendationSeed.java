package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-recommendationseedobject">RecommendationSeedObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class RecommendationSeed {
    /**
     * The number of tracks available after min_* and max_* filters have been applied.
     */
    private Integer afterFilteringSize;
    /**
     * The number of tracks available after relinking for regional availability.
     */
    private Integer afterRelinkingSize;
    /**
     * A link to the full track or artist data for this seed. For tracks this will be a link to a Track Object. For artists a link to an Artist Object. For genre seeds, this value will be null.
     */
    private String href;
    /**
     * The id used to select this seed. This will be the same as the string used in the seed_artists, seed_tracks or seed_genres parameter.
     */
    private String id;
    /**
     * The number of recommended tracks available for this seed.
     */
    private Integer initialPoolSize;
    /**
     * The entity type of this seed. One of artist, track or genre.
     */
    private String type;
}
