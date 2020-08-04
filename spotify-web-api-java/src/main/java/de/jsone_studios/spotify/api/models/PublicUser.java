package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-publicuserobject">PublicUserObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class PublicUser {
    /**
     * The name displayed on the user’s profile. null if not available.
     */
    private String display_name;
    /**
     * Known public external URLs for this user.
     */
    private ExternalUrl external_urls;
    /**
     * Information about the followers of this user.
     */
    private Followers followers;
    /**
     * A link to the Web API endpoint for this user.
     */
    private String href;
    /**
     * The Spotify user ID for this user.
     */
    private String id;
    /**
     * The user’s profile image.
     */
    private java.util.List<Image> images;
    /**
     * The object type: “user”
     */
    private String type;
    /**
     * The Spotify URI for this user.
     */
    private String uri;
}