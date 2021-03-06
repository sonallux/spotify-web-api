package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#segment-object">SegmentObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Segment {
    public float confidence;
    public float duration;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_end")
    public float loudnessEnd;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_max")
    public float loudnessMax;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_max_time")
    public float loudnessMaxTime;
    @com.fasterxml.jackson.annotation.JsonProperty("loudness_start")
    public float loudnessStart;
    public java.util.List<Float> pitches;
    public float start;
    public java.util.List<Float> timbre;
}
