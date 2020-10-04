package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-deviceobject">DeviceObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Device {
    /**
     * The device ID. This may be null.
     */
    private String id;
    /**
     * If this device is the currently active device.
     */
    private Boolean is_active;
    /**
     * If this device is currently in a private session.
     */
    private Boolean is_private_session;
    /**
     * Whether controlling this device is restricted. At present if this is “true” then no Web API commands will be accepted by this device.
     */
    private Boolean is_restricted;
    /**
     * The name of the device.
     */
    private String name;
    /**
     * Device type, such as “computer”, “smartphone” or “speaker”.
     */
    private String type;
    /**
     * The current volume in percent. This may be null.
     */
    private Integer volume_percent;
}
