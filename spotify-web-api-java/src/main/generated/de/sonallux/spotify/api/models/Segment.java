package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#segment-object">SegmentObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Segment {
    private Float confidence;
    private Float duration;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_end")
    private Float loudnessEnd;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_max")
    private Float loudnessMax;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_max_time")
    private Float loudnessMaxTime;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_start")
    private Float loudnessStart;
    private java.util.List<Float> pitches;
    private Float start;
    private java.util.List<Float> timbre;
}
