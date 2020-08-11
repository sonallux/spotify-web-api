package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FollowArtistsUsersRequest {
    /**
     * A JSON array of the artist or user Spotify IDs. For example: {ids:["74ASZWbe4lXaubB36ztrGX", "08td7MxkoHQkXnWAYD8d6Q"]}. A maximum of 50 IDs can be sent in one request. Note: if the ids parameter is present in the query string, any IDs listed here in the body will be ignored.
     */
    @NonNull
    private java.util.List<String> ids;
}