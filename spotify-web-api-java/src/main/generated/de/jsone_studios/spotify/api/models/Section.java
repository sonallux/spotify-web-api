package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/tracks/get-audio-analysis/#section-object">SectionObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Section {
    /**
     * The starting point (in seconds) of the section.
     */
    private Float start;
    /**
     * The duration (in seconds) of the section.
     */
    private Float duration;
    /**
     * The confidence, from 0.0 to 1.0, of the reliability of the section’s "designation".
     */
    private Float confidence;
    /**
     * The overall loudness of the section in decibels (dB). Loudness values are useful for comparing relative loudness of sections within tracks.
     */
    private Float loudness;
    /**
     * The overall estimated tempo of the section in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.
     */
    private Float tempo;
    private Float tempo_confidence;
    private Integer key;
    private Float key_confidence;
    private Integer mode;
    private Float mode_confidence;
    private Integer time_signature;
    private Float time_signature_confidence;
}