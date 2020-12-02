package de.sonallux.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Copyright {
    /**
     * <p>The copyright text for this album.</p>
     */
    private String text;
    /**
     * <p>The type of copyright: C = the copyright, P = the sound recording (performance) copyright.</p>
     */
    private String type;
}
