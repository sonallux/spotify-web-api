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
    private Float loudness_end;
    private Float loudness_max;
    private Float loudness_max_time;
    private Float loudness_start;
    private java.util.List<Float> pitches;
    private Float start;
    private java.util.List<Float> timbre;
}
