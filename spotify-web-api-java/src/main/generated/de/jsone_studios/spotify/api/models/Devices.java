package de.jsone_studios.spotify.api.models;

import lombok.*;

/**
 * <a href="https://developer.spotify.com/documentation/web-api/reference-beta/#object-devicesobject">DevicesObject</a>
 */
@Getter
@Setter
@NoArgsConstructor
public class Devices {
    /**
     * <p>A list of 0..n Device objects</p>
     */
    private java.util.List<Device> devices;
}
