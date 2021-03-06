package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-recommendationsobject">RecommendationsObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Recommendations {
    /**
     * <p>An array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-recommendationseedobject">recommendation seed objects</a>.</p>
     */
    public java.util.List<RecommendationSeed> seeds;
    /**
     * <p>An array of <a href="https://developer.spotify.com/documentation/web-api/reference/#object-simplifiedtrackobject">track object (simplified)</a> ordered according to the parameters supplied.</p>
     */
    public java.util.List<SimplifiedTrack> tracks;
}
