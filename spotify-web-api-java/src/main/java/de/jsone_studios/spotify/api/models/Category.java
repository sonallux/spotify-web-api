package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-categoryobject">CategoryObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Category {
    /**
     * A link to the Web API endpoint returning full details of the category.
     */
    private String href;
    /**
     * The category icon, in various sizes.
     */
    private java.util.List<Image> icons;
    /**
     * The Spotify category ID of the category.
     */
    private String id;
    /**
     * The name of the category.
     */
    private String name;
}