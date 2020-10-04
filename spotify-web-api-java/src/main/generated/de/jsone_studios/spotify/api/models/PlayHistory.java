package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-playhistoryobject">PlayHistoryObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class PlayHistory {
    /**
     * The context the track was played from.
     */
    private Context context;
    /**
     * The date and time the track was played.
     */
    private java.time.LocalDateTime played_at;
    /**
     * The track the user listened to.
     */
    private SimplifiedTrack track;
}
