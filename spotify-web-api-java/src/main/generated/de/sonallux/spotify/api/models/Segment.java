package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#segment-object">SegmentObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Segment {
    private float confidence;
    private float duration;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_end")
    private float loudnessEnd;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_max")
    private float loudnessMax;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_max_time")
    private float loudnessMaxTime;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_start")
    private float loudnessStart;
    private java.util.List<Float> pitches;
    private float start;
    private java.util.List<Float> timbre;
}
