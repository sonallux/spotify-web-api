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
    @com.fasterxml.jackson.annotation.JsonProperty("key_confidence")
    private Float keyConfidence;
    /**
     * <p>The overall loudness of the section in decibels (dB). Loudness values are useful for comparing relative loudness of sections within tracks.</p>
     */
    private Float loudness;
    private Integer mode;
    @com.fasterxml.jackson.annotation.JsonProperty("mode_confidence")
    private Float modeConfidence;
    /**
     * <p>The starting point (in seconds) of the section.</p>
     */
    private Float start;
    /**
     * <p>The overall estimated tempo of the section in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.</p>
     */
    private Float tempo;
    @com.fasterxml.jackson.annotation.JsonProperty("tempo_confidence")
    private Float tempoConfidence;
    @com.fasterxml.jackson.annotation.JsonProperty("time_signature")
    private Integer timeSignature;
    @com.fasterxml.jackson.annotation.JsonProperty("time_signature_confidence")
    private Float timeSignatureConfidence;
}
