package de.jsone_studios.spotify.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TransferUsersPlaybackRequest {
    /**
     * A JSON array containing the ID of the device on which playback should be started/transferred. For example:{device_ids:[&quot;74ASZWbe4lXaubB36ztrGX&quot;]} Note: Although an array is accepted, only a single device_id is currently supported. Supplying more than one will return 400 Bad Request
     */
    @NonNull
    private java.util.List<String> device_ids;
    /**
     * true: ensure playback happens on new device. false or not provided: keep the current playback state.
     */
    private Boolean play;
}