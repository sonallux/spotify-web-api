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
     * A list of 0..n Device objects
     */
    private java.util.List<Device> devices;
}