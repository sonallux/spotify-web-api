package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-recommendationsresponseobject">RecommendationsResponseObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class RecommendationsResponse {
    /**
     * <p>An array of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#recommendations-seed-object">recommendation seed objects</a>.</p>
     */
    private java.util.List<RecommendationSeed> seeds;
    /**
     * <p>An array of <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#track-object-simplified">track object (simplified)</a> ordered according to the parameters supplied.</p>
     */
    private java.util.List<SimplifiedTrack> tracks;
}
