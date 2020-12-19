package de.sonallux.spotify.core;

import java.util.regex.Pattern;

public class SpotifyWebApiUtils {
    public static final Pattern ARRAY_TYPE_PATTERN = Pattern.compile("^Array\\[(.+)]$");
    public static final Pattern PAGING_OBJECT_TYPE_PATTERN = Pattern.compile("^PagingObject\\[(.+)]$");
    public static final Pattern CURSOR_PAGING_OBJECT_TYPE_PATTERN = Pattern.compile("^CursorPagingObject\\[(.+)]$");
}
