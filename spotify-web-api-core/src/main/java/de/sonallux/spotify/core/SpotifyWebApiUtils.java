package de.sonallux.spotify.core;

import de.sonallux.spotify.core.model.SpotifyWebApi;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class SpotifyWebApiUtils {
    public static final Pattern ARRAY_TYPE_PATTERN = Pattern.compile("^Array\\[(.+)]$");
    public static final Pattern PAGING_OBJECT_TYPE_PATTERN = Pattern.compile("^PagingObject\\[(.+)]$");
    public static final Pattern CURSOR_PAGING_OBJECT_TYPE_PATTERN = Pattern.compile("^CursorPagingObject\\[(.+)]$");

    public static SpotifyWebApi load() throws IOException {
        try (var stream = SpotifyWebApiUtils.class.getResourceAsStream("/spotify-web-api.yml")) {
            return load(stream);
        }
    }

    public static SpotifyWebApi load(Path path) throws IOException {
        try (var stream = Files.newInputStream(path)) {
            return load(stream);
        }
    }

    public static SpotifyWebApi load(InputStream inputStream) throws IOException {
        return Yaml.create().readValue(inputStream, SpotifyWebApi.class);
    }
}
