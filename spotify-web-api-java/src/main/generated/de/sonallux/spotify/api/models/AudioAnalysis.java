package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#audio-analysis-object">AudioAnalysisObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class AudioAnalysis {
    /**
     * <p>The time intervals of the bars throughout the track. A bar (or measure) is a segment of time defined as a given number of beats. Bar offsets also indicate downbeats, the first beat of the measure.</p>
     */
    private java.util.List<TimeInterval> bars;
    /**
     * <p>The time intervals of beats throughout the track. A beat is the basic time unit of a piece of music; for example, each tick of a metronome. Beats are typically multiples of tatums.</p>
     */
    private java.util.List<TimeInterval> beats;
    /**
     * <p>Sections are defined by large variations in rhythm or timbre, e.g. chorus, verse, bridge, guitar solo, etc. Each section contains its own descriptions of tempo, key, mode, time_signature, and loudness.</p>
     */
    private java.util.List<Section> sections;
    /**
     * <p>Audio segments attempts to subdivide a song into many segments, with each segment containing a roughly consistent sound throughout its duration.</p>
     */
    private java.util.List<Segment> segments;
    /**
     * <p>A tatum represents the lowest regular pulse train that a listener intuitively infers from the timing of perceived musical events (segments).</p>
     */
    private java.util.List<TimeInterval> tatums;
}
