package de.sonallux.spotify.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sonallux.spotify.core.Yaml;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.json.patching.Patch;
import de.sonallux.json.patching.PatchException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
public class ApiPatches {
    private final ObjectMapper mapper;

    private JsonNode webApiAsJsonNode;

    public ApiPatches() {
        mapper = Yaml.create();
    }

    public SpotifyWebApi applyPatches(SpotifyWebApi spotifyWebApi) throws ApiParseException {
        webApiAsJsonNode = mapper.valueToTree(spotifyWebApi);

        walkPatches(this::applyPatch);

        try {
            return mapper.treeToValue(webApiAsJsonNode, SpotifyWebApi.class);
        } catch (JsonProcessingException e) {
            throw new ApiParseException("Result of patching is not a SpotifyWebApi", e);
        }
    }

    private void applyPatch(Path path) {
        try {
            var patch = loadPatch(path);
            webApiAsJsonNode = patch.apply(webApiAsJsonNode);
        } catch (IOException e) {
            log.error("Failed to load patch from path " + path, e);
        } catch (PatchException e) {
            log.warn("Failed to apply patch: " + path.getFileName(), e);
        }
    }

    private void walkPatches(Consumer<Path> patchConsumer) throws ApiParseException {
        var patchesDirectory = "/patches/";
        var resource = ApiPatches.class.getResource(patchesDirectory);
        if (resource == null) {
            throw new ApiParseException("Can not get patches resource");
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
            Files.list(path).forEach(patchConsumer);
        } catch (IOException | URISyntaxException e) {
            throw new ApiParseException("Failed to load patches", e);
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
            return mapper.readValue(inputStream, Patch.class);
        }
    }
}
