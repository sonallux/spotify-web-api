package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CurrentPlayback {
    private java.time.LocalDateTime timestamp;
    private Device device;
    private Integer progress_ms;
    private Boolean is_playing;
    private String currently_playing_type;
    private java.util.Map<String, Object> item;
    private Boolean shuffle_state;
    private String repeat_state;
    private Context context;
}
