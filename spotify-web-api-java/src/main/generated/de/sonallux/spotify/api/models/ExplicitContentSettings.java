package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-explicitcontentsettingsobject">ExplicitContentSettingsObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class ExplicitContentSettings {
    /**
     * <p>When <code>true</code>, indicates that explicit content should not be played.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("filter_enabled")
    public boolean filterEnabled;
    /**
     * <p>When <code>true</code>, indicates that the explicit content setting is locked and can't be changed by the user.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("filter_locked")
    public boolean filterLocked;
}
