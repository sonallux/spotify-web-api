package de.sonallux.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference/#object-devicesobject">DevicesObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Devices {
    /**
     * <p>A list of 0..n Device objects</p>
     */
    public java.util.List<Device> devices;
}
