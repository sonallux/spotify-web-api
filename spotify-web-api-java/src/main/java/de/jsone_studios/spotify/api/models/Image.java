package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/object-model/#image-object">ImageObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Image {
    /**
     * The image height in pixels. If unknown: null or not returned.
     */
    private Integer height;
    /**
     * The source URL of the image.
     */
    private String url;
    /**
     * The image width in pixels. If unknown: null or not returned.
     */
    private Integer width;
}