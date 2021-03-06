package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-categoryobject">CategoryObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Category {
    /**
     * <p>A link to the Web API endpoint returning full details of the category.</p>
     */
    public String href;
    /**
     * <p>The category icon, in various sizes.</p>
     */
    public java.util.List<Image> icons;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify category ID</a> of the category.</p>
     */
    public String id;
    /**
     * <p>The name of the category.</p>
     */
    public String name;
}
