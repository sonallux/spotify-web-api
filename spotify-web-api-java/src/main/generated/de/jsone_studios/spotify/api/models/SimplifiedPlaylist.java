package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SimplifiedPlaylist {
    private Boolean collaborative;
    private String description;
    private ExternalUrl external_urls;
    private String href;
    private String id;
    private java.util.List<Image> images;
    private String name;
    private PublicUser owner;
    @com.fasterxml.jackson.annotation.JsonProperty("public")
    private Boolean _public;
    private String snapshot_id;
    private PlaylistTracksInfo tracks;
    private String type;
    private String uri;
}