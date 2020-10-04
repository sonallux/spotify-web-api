package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#time-interval-object">TimeIntervalObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class TimeInterval {
    /**
     * The starting point (in seconds) of the time interval.
     */
    private Float start;
    /**
     * The duration (in seconds) of the time interval.
     */
    private Float duration;
    /**
     * The confidence, from 0.0 to 1.0, of the reliability of the interval.
     */
    private Float confidence;
}
