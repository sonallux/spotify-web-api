package de.sonallux.spotify.generator.java;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EndpointRequestBodyObject {
    private String name;
    private String endpointName;
    private String endpointLink;
    private List<Property> properties;

    public EndpointRequestBodyObject addProperty(Property property) {
        properties.add(property);
        return this;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Property {
        private String name;
        private String type;
        private String description;
        private boolean required;
    }
}
