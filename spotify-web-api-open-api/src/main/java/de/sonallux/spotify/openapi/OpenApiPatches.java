package de.sonallux.spotify.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import de.sonallux.json.patching.Patch;
import de.sonallux.json.patching.PatchException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
public class OpenApiPatches {
    private static final ObjectMapper MAPPER = new YAMLMapper();

    private JsonNode openApiJsonNode;

    private OpenApiPatches(JsonNode openApiJsonNode) {
        this.openApiJsonNode = openApiJsonNode;
    }

    public static JsonNode applyPatches(JsonNode openApiJsonNode) throws OpenApiPatchException {
        final var openApiPatches = new OpenApiPatches(openApiJsonNode);

        openApiPatches.walkPatches(openApiPatches::applyPatch);

        return openApiPatches.openApiJsonNode;
    }

    private void applyPatch(Path path) {
        log.debug("Applying patch {}", path);
        try {
            var patch = loadPatch(path);
            openApiJsonNode = patch.apply(openApiJsonNode);
        } catch (IOException e) {
            log.error("Failed to load patch from path " + path, e);
        } catch (PatchException e) {
            log.warn("Failed to apply patch: " + path.getFileName(), e);
        }
    }

    private void walkPatches(Consumer<Path> patchConsumer) throws OpenApiPatchException {
        var patchesDirectory = "/patches/";
        var resource = OpenApiPatches.class.getResource(patchesDirectory);
        if (resource == null) {
            throw new OpenApiPatchException("Can not get patches resource");
        }

        FileSystem fs = null;
        try {
            Path path;
            var uri = resource.toURI();
            if ("jar".equals(uri.getScheme())) {
                fs = FileSystems.newFileSystem(uri, Map.of());
                path = fs.getPath(patchesDirectory);
            } else {
                path = Path.of(uri);
            }
            Files.list(path)
                // Sort files by filename to have a consistent order
                .sorted(Comparator.comparing(p -> p.getFileName().toString()))
                .forEach(patchConsumer);
        } catch (IOException | URISyntaxException e) {
            throw new OpenApiPatchException("Failed to load patches", e);
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException ignore) {
                }
            }
        }
    }

    private Patch loadPatch(Path path) throws IOException {
        try (var inputStream = Files.newInputStream(path)) {
            return MAPPER.readValue(inputStream, Patch.class);
        }
    }
}
