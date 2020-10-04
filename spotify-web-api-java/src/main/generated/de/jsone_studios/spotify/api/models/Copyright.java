package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Copyright {
    /**
     * The copyright text for this album.
     */
    private String text;
    /**
     * The type of copyright: C &#x3D; the copyright, P &#x3D; the sound recording (performance) copyright.
     */
    private String type;
}
