package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#section-object">SectionObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Section {
    /**
     * <p>The confidence, from 0.0 to 1.0, of the reliability of the section’s &quot;designation&quot;.</p>
     */
    private Float confidence;
    /**
     * <p>The duration (in seconds) of the section.</p>
     */
    private Float duration;
    private Integer key;
    private Float key_confidence;
    /**
     * <p>The overall loudness of the section in decibels (dB). Loudness values are useful for comparing relative loudness of sections within tracks.</p>
     */
    private Float loudness;
    private Integer mode;
    private Float mode_confidence;
    /**
     * <p>The starting point (in seconds) of the section.</p>
     */
    private Float start;
    /**
     * <p>The overall estimated tempo of the section in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.</p>
     */
    private Float tempo;
    private Float tempo_confidence;
    private Integer time_signature;
    private Float time_signature_confidence;
}
