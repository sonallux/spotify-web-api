package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CurrentPlayback {
    private Context context;
    private String currently_playing_type;
    private Device device;
    private Boolean is_playing;
    private java.util.Map<String, Object> item;
    private Integer progress_ms;
    private String repeat_state;
    private Boolean shuffle_state;
    private java.time.LocalDateTime timestamp;
}
