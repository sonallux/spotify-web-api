package de.sonallux.spotify.generator.java.templates;

import de.sonallux.spotify.core.SpotifyWebApiObjectUtils;
import de.sonallux.spotify.core.model.SpotifyWebApiObject;
import de.sonallux.spotify.generator.java.util.JavaUtils;

import java.util.Map;

public class BaseObjectTemplate extends ObjectTemplate {
    @Override
    public String templateName() {
        return "base-object";
    }

    @Override
    public String getFileName(SpotifyWebApiObject object) {
        return JavaUtils.getFileName(SpotifyWebApiObjectUtils.BASE_OBJECT_NAME);
    }

    @Override
    public Map<String, Object> buildContext(SpotifyWebApiObject object, Map<String, Object> rootContext) {
        var context = super.buildContext(object, rootContext);
        context.put("className", SpotifyWebApiObjectUtils.BASE_OBJECT_NAME);

        return context;
    }
}
