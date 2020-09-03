package de.jsone_studios.spotify.generator.ts;

import com.google.common.base.Strings;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import de.jsone_studios.spotify.parser.model.SpotifyObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.*;

public class ObjectTemplate {
    private Template template;

    ObjectTemplate loadTemplate(Mustache.Compiler compiler) {
        this.template = compiler.loadTemplate("object");
        return this;
    }

    void generate(List<SpotifyObject> spotifyObjects, Path outputFile) throws IOException {
        try (var writer = Files.newBufferedWriter(outputFile, CREATE, TRUNCATE_EXISTING, WRITE)) {
            template.execute(generateContext(spotifyObjects), writer);
        }
    }

    private Map<String, Object> generateContext(List<SpotifyObject> spotifyObjects) {
        return Map.of(
                "tsDoc", new TSDocLambda(),
                "objects", spotifyObjects.stream()
                    .map(this::generateObjectContext)
                    .collect(Collectors.toList()));
    }

    private Map<String, Object> generateObjectContext(SpotifyObject object) {
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

    private Map<String, Object> generatePropertyContext(SpotifyObject.Property property) {
        var context = new HashMap<String, Object>();
        context.put("fieldName", property.getName());
        context.put("type", TSUtils.mapToTsType(property.getType()));
        context.put("description", property.getDescription());
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
