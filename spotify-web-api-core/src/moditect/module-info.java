module de.sonallux.spotify.core {
    requires com.fasterxml.jackson.dataformat.yaml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires static lombok;

    exports de.sonallux.spotify.core;
    exports de.sonallux.spotify.core.model;
}
