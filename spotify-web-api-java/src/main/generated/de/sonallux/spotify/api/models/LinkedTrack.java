package de.sonallux.spotify.api.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-linkedtrackobject">LinkedTrackObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE) // Disable deserialization based on @JsonTypeInfo
public class LinkedTrack extends BaseObject {
    /**
     * <p>Known external URLs for this track.</p>
     */
    public ExternalUrl externalUrls;
}
