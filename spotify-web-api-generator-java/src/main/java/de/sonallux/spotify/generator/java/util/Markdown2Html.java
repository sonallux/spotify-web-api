package de.sonallux.spotify.generator.java.util;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Markdown2Html {
    private static final Parser PARSER;
    private static final HtmlRenderer HTML_RENDERER;

    static {
        var parserOptions = new MutableDataSet();
        PARSER = Parser.builder(parserOptions).build();

        var rendererOptions = new MutableDataSet();
        HTML_RENDERER = HtmlRenderer.builder(rendererOptions).build();
    }

    public static String convert(String markdown) {
        var document = PARSER.parse(markdown);
        var html = HTML_RENDERER.render(document);
        return html.replaceAll("<br />", "<br>");
    }

    public static String convertToSingleLine(String markdown) {
        return convert(markdown)
            .replaceAll(">\n", ">")
            .replaceAll("\n", " ")
            .trim();
    }

    public static List<String> convertToLines(String markdown) {
        var html = convert(markdown);
        return Arrays.stream(html.split("\n"))
            .map(String::trim)
            .collect(Collectors.toList());
    }
}
