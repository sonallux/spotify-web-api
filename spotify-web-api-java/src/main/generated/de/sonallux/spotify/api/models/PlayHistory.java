package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-playhistoryobject">PlayHistoryObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class PlayHistory {
    /**
     * <p>The context the track was played from.</p>
     */
    public Context context;
    /**
     * <p>The date and time the track was played.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("played_at")
    public java.time.LocalDateTime playedAt;
    /**
     * <p>The track the user listened to.</p>
     */
    public SimplifiedTrack track;
}
