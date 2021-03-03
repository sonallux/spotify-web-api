package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AudioFeaturesArray {
    @com.fasterxml.jackson.annotation.JsonProperty("audio_features")
    private java.util.List<AudioFeatures> audioFeatures;
}
