package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-publicuserobject">PublicUserObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class PublicUser {
    /**
     * <p>The name displayed on the user's profile. <code>null</code> if not available.</p>
     */
    public String displayName;
    /**
     * <p>Known public external URLs for this user.</p>
     */
    public ExternalUrl externalUrls;
    /**
     * <p>Information about the followers of this user.</p>
     */
    public Followers followers;
    /**
     * <p>A link to the Web API endpoint for this user.</p>
     */
    public String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify user ID</a> for this user.</p>
     */
    public String id;
    /**
     * <p>The user's profile image.</p>
     */
    public java.util.List<Image> images;
    /**
     * <p>The object type: &quot;user&quot;</p>
     */
    public String type;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for this user.</p>
     */
    public String uri;
}
