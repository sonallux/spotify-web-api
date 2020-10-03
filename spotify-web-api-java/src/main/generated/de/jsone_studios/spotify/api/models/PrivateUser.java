package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-privateuserobject">PrivateUserObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class PrivateUser {
    /**
     * The country of the user, as set in the user’s account profile. An ISO 3166-1 alpha-2 country code. This field is only available when the current user has granted access to the user-read-private scope.
     */
    private String country;
    /**
     * The name displayed on the user’s profile. null if not available.
     */
    private String display_name;
    /**
     * The user’s email address, as entered by the user when creating their account. Important! This email address is unverified; there is no proof that it actually belongs to the user. This field is only available when the current user has granted access to the user-read-email scope.
     */
    private String email;
    /**
     * The user’s explicit content settings. This field is only available when the current user has granted access to the user-read-private scope.
     */
    private ExplicitContentSettings explicit_content;
    /**
     * Known external URLs for this user.
     */
    private ExternalUrl external_urls;
    /**
     * Information about the followers of the user.
     */
    private Followers followers;
    /**
     * A link to the Web API endpoint for this user.
     */
    private String href;
    /**
     * The Spotify user ID for the user.
     */
    private String id;
    /**
     * The user’s profile image.
     */
    private java.util.List<Image> images;
    /**
     * The user’s Spotify subscription level: “premium”, “free”, etc. (The subscription level “open” can be considered the same as “free”.) This field is only available when the current user has granted access to the user-read-private scope.
     */
    private String product;
    /**
     * The object type: “user”
     */
    private String type;
    /**
     * The Spotify URI for the user.
     */
    private String uri;
}