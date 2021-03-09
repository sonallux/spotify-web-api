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
    public float loudnessEnd;
    public float loudnessMax;
    public float loudnessMaxTime;
    public float loudnessStart;
    public java.util.List<Float> pitches;
    public float start;
    public java.util.List<Float> timbre;
}
