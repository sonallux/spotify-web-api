package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-imageobject">ImageObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Image {
    /**
     * <p>The image height in pixels. If unknown: <code>null</code> or not returned.</p>
     */
    public int height;
    /**
     * <p>The source URL of the image.</p>
     */
    public String url;
    /**
     * <p>The image width in pixels. If unknown: <code>null</code> or not returned.</p>
     */
    public int width;
}
