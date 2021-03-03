package de.sonallux.spotify.generator.ts;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.base.Strings;
import de.sonallux.spotify.core.model.SpotifyWebApiObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static java.nio.file.StandardOpenOption.*;

public class ObjectTemplate {
    private Mustache mustacheTemplate;

    ObjectTemplate loadTemplate(MustacheFactory mustacheFactory) {
        this.mustacheTemplate = mustacheFactory.compile("templates/object.mustache");
        return this;
    }

    void generate(Collection<SpotifyWebApiObject> spotifyWebApiObjects, Path outputFile) throws IOException {
        try (var writer = Files.newBufferedWriter(outputFile, CREATE, TRUNCATE_EXISTING, WRITE)) {
            mustacheTemplate.execute(writer, generateContext(spotifyWebApiObjects));
        }
    }

    private Map<String, Object> generateContext(Collection<SpotifyWebApiObject> spotifyWebApiObjects) {
        return Map.of(
                "objects", spotifyWebApiObjects.stream()
                    .map(this::generateObjectContext)
                    .collect(Collectors.toList()));
    }

    private Map<String, Object> generateObjectContext(SpotifyWebApiObject object) {
        var context = new HashMap<String, Object>();
        context.put("name", TSUtils.getObjectClassName(object.getName()));
        context.put("properties", object.getProperties().stream().map(this::generatePropertyContext).collect(Collectors.toList()));
        if (!Strings.isNullOrEmpty(object.getLink())) {
            context.put("documentationLink", object.getLink());
        }

        if (object.getProperties().size() == 0) {
            context.put("parentType", "Record<string, any>");
        }

        if ("PagingObject".equals(object.getName()) || "CursorPagingObject".equals(object.getName())) {
            fixContextForPaging(context);
        }
        return context;
    }

    private Map<String, Object> generatePropertyContext(SpotifyWebApiObject.Property property) {
        var context = new HashMap<String, Object>();
        // Convert spotify property name in lower camel case format
        context.put("fieldName", LOWER_UNDERSCORE.converterTo(LOWER_CAMEL).convert(property.getName()));
        context.put("type", TSUtils.mapToTsType(property.getType()));
        var description = property.getDescription();
        if (description != null && description.length() > 0) {
            context.put("hasDescription", true);
            context.put("description", Arrays.stream(description.split("\n")).map(String::trim).collect(Collectors.toList()));
        }
        return context;
    }

    private void fixContextForPaging(Map<String, Object> context) {
        context.put("name", context.get("name") + "<T>");
        @SuppressWarnings("unchecked")
        var properties = (List<Map<String, Object>>)context.get("properties");
        for (var property : properties) {
            if ("items".equals(property.get("fieldName"))) {
                property.put("type", "T[]");
            }
        }
    }
}
