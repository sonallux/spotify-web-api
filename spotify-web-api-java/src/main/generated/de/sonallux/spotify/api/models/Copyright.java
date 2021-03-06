package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-copyrightobject">CopyrightObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Copyright {
    /**
     * <p>The copyright text for this content.</p>
     */
    public String text;
    /**
     * <p>The type of copyright: <code>C</code> = the copyright, <code>P</code> = the sound recording (performance) copyright.</p>
     */
    public String type;
}
