package de.sonallux.json.diff;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import de.sonallux.json.diff.changes.JsonChange;
import de.sonallux.json.diff.changes.JsonChangeDeserializer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonDifferTest {
    private final static ObjectMapper MAPPER = YAMLMapper.builder()
        .addModule(new SimpleModule().addDeserializer(JsonChange.class, new JsonChangeDeserializer()))
        .build();

    static Stream<Arguments> testJsonDiff() throws Exception {
        return Files.list(Path.of(JsonDifferTest.class.getResource("/diffs/").toURI()))
            .map(path -> {
                try {
                    var inputStream = Files.newInputStream(path);
                    var documentIterator = MAPPER.readValues(MAPPER.createParser(inputStream), JsonNode.class);
                    var left = documentIterator.nextValue();
                    var right = documentIterator.nextValue();
                    var changesNode = documentIterator.nextValue();
                    var changes = MAPPER.convertValue(changesNode, new TypeReference<List<JsonChange>>() {});
                    return Arguments.of(left, right, changes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    @ParameterizedTest
    @MethodSource
    void testJsonDiff(JsonNode left, JsonNode right, List<JsonChange> expectedChanges) {
        var changes = new JsonDiffer().jsonDiff(left, right);
        assertThat(changes).containsExactlyElementsOf(expectedChanges);
    }
}
