package de.jsone_studios.spotify.generator.java.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class Markdown2HtmlTest {

    static List<String[]> markdown2HtmlTestCases() {
        return List.of(
                new String[]{"[Test](/foo/bar)", "<p><a href=\"/foo/bar\">Test</a></p>"},
                new String[]{"`playlist_id`", "<p><code>playlist_id</code></p>"},
                new String[]{"This is `playlist_id`, `uri`", "<p>This is <code>playlist_id</code>, <code>uri</code></p>"},
                new String[]{"**Bold** is bolder", "<p><strong>Bold</strong> is bolder</p>"},
                new String[]{"- foo\n- bar", "<ul> <li>foo</li> <li>bar</li> </ul>"},
                new String[]{"Foo\n\nTest", "<p>Foo</p> <p>Test</p>"}
        );
    }

    @ParameterizedTest
    @MethodSource("markdown2HtmlTestCases")
    void testMarkdown2HtmlConverter(String markdown, String expectedHtml) {
        var actualHtml = Markdown2Html.convertToSingleLine(markdown);
        Assertions.assertEquals(expectedHtml, actualHtml);
    }

    @Test
    void testSingleLine() {
        var markdown = "Search [query](#writing-a-query---guidelines) keywords and optional field filters and operators.   \nFor example:   \n`q=roadhouse%20blues`.";
        var expectedHtml = "<p>Search <a href=\"#writing-a-query---guidelines\">query</a> keywords and optional field filters and operators.<br> For example:<br> <code>q=roadhouse%20blues</code>.</p>";
        var actualHtml = Markdown2Html.convertToSingleLine(markdown);
        Assertions.assertEquals(expectedHtml, actualHtml);
    }
}
