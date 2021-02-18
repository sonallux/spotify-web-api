package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * Request body for endpoint <a href="https://developer.spotify.com/documentation/web-api/reference/#endpoint-transfer-a-users-playback">Transfer a User's Playback</a>
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TransferUsersPlaybackRequest {
    /**
     * <p>A JSON array containing the ID of the device on which playback should be started/transferred.<br> For example:<code>{device_ids:[&quot;74ASZWbe4lXaubB36ztrGX&quot;]}</code><br> Note: Although an array is accepted, only a single device_id is currently supported. Supplying more than one will return <code>400 Bad Request</code></p>
     */
    @NonNull
    private final java.util.List<String> device_ids;
    /**
     * <p><strong>true</strong> : ensure playback happens on new device.<br> <strong>false</strong> or not provided: keep the current playback state.</p>
     */
    private Boolean play;
}
