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
    public float confidence;
    /**
     * <p>The duration (in seconds) of the section.</p>
     */
    public float duration;
    public int key;
    @com.fasterxml.jackson.annotation.JsonProperty("key_confidence")
    public float keyConfidence;
    /**
     * <p>The overall loudness of the section in decibels (dB). Loudness values are useful for comparing relative loudness of sections within tracks.</p>
     */
    public float loudness;
    public int mode;
    @com.fasterxml.jackson.annotation.JsonProperty("mode_confidence")
    public float modeConfidence;
    /**
     * <p>The starting point (in seconds) of the section.</p>
     */
    public float start;
    /**
     * <p>The overall estimated tempo of the section in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.</p>
     */
    public float tempo;
    @com.fasterxml.jackson.annotation.JsonProperty("tempo_confidence")
    public float tempoConfidence;
    @com.fasterxml.jackson.annotation.JsonProperty("time_signature")
    public int timeSignature;
    @com.fasterxml.jackson.annotation.JsonProperty("time_signature_confidence")
    public float timeSignatureConfidence;
}
