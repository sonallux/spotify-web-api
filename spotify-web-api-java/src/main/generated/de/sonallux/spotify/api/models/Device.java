package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-deviceobject">DeviceObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Device {
    /**
     * <p>The device ID. This may be <code>null</code>.</p>
     */
    private String id;
    /**
     * <p>If this device is the currently active device.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("is_active")
    private Boolean isActive;
    /**
     * <p>If this device is currently in a private session.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("is_private_session")
    private Boolean isPrivateSession;
    /**
     * <p>Whether controlling this device is restricted. At present if this is &quot;true&quot; then no Web API commands will be accepted by this device.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("is_restricted")
    private Boolean isRestricted;
    /**
     * <p>The name of the device.</p>
     */
    private String name;
    /**
     * <p>Device type, such as &quot;computer&quot;, &quot;smartphone&quot; or &quot;speaker&quot;.</p>
     */
    private String type;
    /**
     * <p>The current volume in percent. This may be null.</p>
     */
    @com.fasterxml.jackson.annotation.JsonProperty("volume_percent")
    private Integer volumePercent;
}
