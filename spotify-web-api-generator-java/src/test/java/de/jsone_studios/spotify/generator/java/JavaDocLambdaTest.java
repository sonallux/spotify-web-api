package de.jsone_studios.spotify.generator.java;

import com.samskivert.mustache.Template;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.StringWriter;
import java.util.stream.Stream;

public class JavaDocLambdaTest {

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("/**\n * Test\n */\n", "Test"),
                Arguments.of("/**\n * Hallo World\n */\n", "Hallo World"),
                Arguments.of("/**\n * Hallo World\n */\n", "Hallo World\n"),
                Arguments.of("/**\n * Hallo World\n * Hallo World\n */\n", "Hallo World\nHallo World"),
                Arguments.of("/**\n * Hallo World\n * Hallo World\n */\n", "Hallo World\nHallo World\n"),
                Arguments.of("/**\n * Hallo World\n * \n * Hallo World\n */\n", "Hallo World\n\nHallo World"),

                //intend of 2 spaces
                Arguments.of("  /**\n   * Hallo World\n   * Hallo World\n   */\n", "  Hallo World\nHallo World"),
                Arguments.of("  /**\n   * Hallo World\n   * \n   * Hallo World\n   */\n", "  Hallo World\n\nHallo World"),
                Arguments.of("/**\n * Hallo World\n *   Hallo World\n */\n", "Hallo World\n  Hallo World"),
                Arguments.of("/**\n * Hallo World\n * \n *   Hallo World\n */\n", "Hallo World\n\n  Hallo World"),

                //intend of 4 spaces
                Arguments.of("/**\n * Hallo World\n * Hallo World\n */\n", "    Hallo World\n    Hallo World"),
                Arguments.of("  /**\n   * Hallo World\n   * Hallo World\n   */\n", "      Hallo World\n    Hallo World"),
                Arguments.of("/**\n * Hallo World\n *   Hallo World\n */\n", "    Hallo World\n      Hallo World"),
                Arguments.of("/**\n * Hallo World\n * \n * Hallo World\n */\n", "    Hallo World\n\n    Hallo World"),

                //intend of 8 spaces
                Arguments.of("    /**\n     * Hallo World\n     * Hallo World\n     */\n", "        Hallo World\n        Hallo World"),
                Arguments.of("      /**\n       * Hallo World\n       * Hallo World\n       */\n", "          Hallo World\n        Hallo World"),
                Arguments.of("    /**\n     * Hallo World\n     *   Hallo World\n     */\n", "        Hallo World\n          Hallo World"),
                Arguments.of("    /**\n     * Hallo World\n     * \n     * Hallo World\n     */\n", "        Hallo World\n\n        Hallo World")

        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testJavaDocLambda(String expectedJavaDoc, String fragment) throws IOException {
        var fragmentMock = Mockito.mock(Template.Fragment.class);
        Mockito.when(fragmentMock.execute()).thenReturn(fragment);

        var out = new StringWriter();
        new JavaDocLambda().execute(fragmentMock, out);
        Assertions.assertEquals(expectedJavaDoc, out.toString());
    }
}
