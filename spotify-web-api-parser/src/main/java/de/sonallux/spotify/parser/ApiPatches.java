package de.sonallux.spotify.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sonallux.spotify.core.Yaml;
import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.parser.patching.Patch;
import de.sonallux.spotify.parser.patching.PatchException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
public class ApiPatches {
    private final ObjectMapper mapper;

    public ApiPatches() {
        mapper = Yaml.create();
    }

    public SpotifyWebApi applyPatches(SpotifyWebApi spotifyWebApi) throws ApiParseException {
        JsonNode apiAsJsonNode = mapper.valueToTree(spotifyWebApi);

        var iterator = getPathsOfPatches().iterator();
        while (iterator.hasNext()) {
            var path = iterator.next();

            try {
                var patch = loadPatch(path);
                apiAsJsonNode = patch.apply(apiAsJsonNode);
            } catch (IOException e) {
                log.error("Failed to load patch from path " + path, e);
            } catch (PatchException e) {
                log.warn("Failed to apply patch: " + path.getFileName(), e);
            }
        }

        try {
            return mapper.treeToValue(apiAsJsonNode, SpotifyWebApi.class);
        } catch (JsonProcessingException e) {
            throw new ApiParseException("Result of patching is not a SpotifyWebApi", e);
        }
    }

    private Stream<Path> getPathsOfPatches() throws ApiParseException {
        try {
            var patchesDir = Path.of(ApiPatches.class.getResource("/patches/").toURI());
            return Files.list(patchesDir);
        } catch (IOException | URISyntaxException e) {
            throw new ApiParseException("Failed to load patches", e);
        }
    }

    private Patch loadPatch(Path path) throws IOException {
        try (var inputStream = Files.newInputStream(path)) {
            return mapper.readValue(inputStream, Patch.class);
        }
    }
}
