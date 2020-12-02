package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-playhistoryobject">PlayHistoryObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class PlayHistory {
    /**
     * <p>The context the track was played from.</p>
     */
    private Context context;
    /**
     * <p>The date and time the track was played.</p>
     */
    private java.time.LocalDateTime played_at;
    /**
     * <p>The track the user listened to.</p>
     */
    private SimplifiedTrack track;
}
