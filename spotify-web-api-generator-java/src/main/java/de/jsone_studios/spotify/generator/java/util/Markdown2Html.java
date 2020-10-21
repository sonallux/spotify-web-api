package de.jsone_studios.spotify.generator.java.util;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

public class Markdown2Html {
    private static final Parser PARSER;
    private static final HtmlRenderer HTML_RENDERER;

    static {
        var parserOptions = new MutableDataSet();
        PARSER = Parser.builder(parserOptions).build();

        var rendererOptions = new MutableDataSet();
        HTML_RENDERER = HtmlRenderer.builder(rendererOptions).build();
    }

    public static String convertToSingleLine(String markdown) {
        var document = PARSER.parse(markdown);
        return HTML_RENDERER.render(document).replaceAll("\n", " ").replaceAll("<br />", "<br>").trim();
    }
}
