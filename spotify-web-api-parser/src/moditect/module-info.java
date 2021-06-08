module de.sonallux.spotify.parser {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires flexmark;
    requires flexmark.html2md.converter;
    requires flexmark.util.data;
    requires info.picocli;
    requires org.slf4j;
    requires org.jsoup;
    requires de.sonallux.spotify.core;
    requires static lombok;

    exports de.sonallux.spotify.parser;
}
