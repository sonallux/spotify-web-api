package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-explicitcontentsettingsobject">ExplicitContentSettingsObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class ExplicitContentSettings {
    /**
     * When true, indicates that explicit content should not be played.
     */
    private Boolean filter_enabled;
    /**
     * When true, indicates that the explicit content setting is locked and can’t be changed by the user.
     */
    private Boolean filter_locked;
}