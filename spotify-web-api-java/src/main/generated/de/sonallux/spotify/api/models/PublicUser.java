package de.sonallux.spotify.api.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-publicuserobject">PublicUserObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE) // Disable deserialization based on @JsonTypeInfo
public class PublicUser extends BaseObject {
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
     * <p>The user's profile image.</p>
     */
    public java.util.List<Image> images;
}
