package de.sonallux.spotify.api.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Album.class, name = "album"),
    @JsonSubTypes.Type(value = Artist.class, name = "artist"),
    @JsonSubTypes.Type(value = Episode.class, name = "episode"),
    @JsonSubTypes.Type(value = Playlist.class, name = "playlist"),
    @JsonSubTypes.Type(value = Show.class, name = "show"),
    @JsonSubTypes.Type(value = Track.class, name = "track"),
    @JsonSubTypes.Type(value = PrivateUser.class, name = "user"),
})
public abstract class BaseObject {
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify ID</a> for the object.</p>
     */
    public String id;
    /**
     * <p>The object type.</p>
     */
    public String type;
    /**
     * <p>A link to the Web API endpoint providing full details of the object.</p>
     */
    public String href;
    /**
     * <p>The <a href="https://developer.spotify.com/documentation/web-api/#spotify-uris-and-ids">Spotify URI</a> for the object.</p>
     */
    public String uri;
}
