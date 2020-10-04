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
     * An array of recommendation seed objects.
     */
    private java.util.List<RecommendationSeed> seeds;
    /**
     * An array of track object (simplified) ordered according to the parameters supplied.
     */
    private java.util.List<SimplifiedTrack> tracks;
}
