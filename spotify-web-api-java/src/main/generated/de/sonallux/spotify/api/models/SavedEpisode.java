package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-savedepisodeobject">SavedEpisodeObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class SavedEpisode {
    /**
     * <p>The date and time the episode was saved. Timestamps are returned in ISO 8601 format as Coordinated Universal Time (UTC) with a zero offset: YYYY-MM-DDTHH:MM:SSZ.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("added_at")
    public java.time.LocalDateTime addedAt;
    /**
     * <p>Information about the episode.</p>
     */
    public Episode episode;
}
