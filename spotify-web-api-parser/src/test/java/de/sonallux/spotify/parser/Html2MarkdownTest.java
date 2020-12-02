package de.sonallux.spotify.parser;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class Html2MarkdownTest {

    static List<String[]> html2MarkdownTestCases() {
        return List.of(
                new String[]{"<a href=\"/foo/bar\">Test</a>", "[Test](/foo/bar)"},
                new String[]{"<code>playlist_id</code>", "`playlist_id`"},
                new String[]{"<strong>Bold</strong> is bolder", "**Bold** is bolder"},
                new String[]{"<ul><li>foo</li><li>bar</li></ul>", "- foo\n- bar"},
                new String[]{"“Spanish (Mexico)”", "\"Spanish (Mexico)\""},
                new String[]{"foo<br><br>", "foo"},
                new String[]{"<p>Text</p><div>\t</div>", "Text"},
                new String[]{"<p>Test</p><p>Bar</p>", "Test\n\nBar"}
        );
    }

    @ParameterizedTest
    @MethodSource("html2MarkdownTestCases")
    void testHtml2MarkdownConverter(String html, String expectedMarkdown) {
        var document = Jsoup.parse(html);
        var actualMarkdown = Html2Markdown.convert(document);
        Assertions.assertEquals(expectedMarkdown, actualMarkdown);
    }

    @Test
    void removeAllTrainingWhitespace() {
        var node1 = Jsoup.parse("<p>Test</p>");
        var node2 = Jsoup.parse("<div></div>");
        var markdown = Html2Markdown.convert(List.of(node1, node2));
        Assertions.assertEquals("Test", markdown);
    }

    @Test
    void preserveNewlines() {
        var node1 = Jsoup.parse("<p>Test</p>");
        var node2 = Jsoup.parse("<p>Bar</p>");
        var markdown = Html2Markdown.convert(List.of(node1, node2));
        Assertions.assertEquals("Test\n\nBar", markdown);
    }

    @Test
    void addDocumentHostToRelativeLinks() {
        var linkWithoutProto = Jsoup.parse("<a href=\"/link/relative\">Test</a>", "www.example.com");
        Assertions.assertEquals("[Test](/link/relative)", Html2Markdown.convert(linkWithoutProto));

        var linkWithProto = Jsoup.parse("<a href=\"/link/relative\">Test</a>", "https://www.example.com");
        Assertions.assertEquals("[Test](https://www.example.com/link/relative)", Html2Markdown.convert(linkWithProto));
    }
}
