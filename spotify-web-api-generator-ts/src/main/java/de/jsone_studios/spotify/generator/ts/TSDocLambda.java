package de.jsone_studios.spotify.generator.ts;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.io.IOException;
import java.io.Writer;

public class TSDocLambda implements Mustache.Lambda {

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        var fullText = frag.execute();
        if (fullText.isBlank()) {
            return;
        }
        var lines = fullText.split("\n");
        var indentCount = countIndentation(lines[0]); // The first line determines the indentation
        var indent = " ".repeat(indentCount >= 4 ? indentCount - 4 : indentCount);
        out.write(indent + "/**\n");
        for (var line : lines) {
            if (line.isBlank()) {
                out.write(indent + " * \n");
            } else {
                out.write(indent + " * " + trimTrailing(line, indentCount) + "\n");
            }
        }
        out.write(indent + " */\n");
    }

    private String trimTrailing(String text, int maxCount) {
        var i = 0;
        for (; i < maxCount; i++) {
            if (!Character.isWhitespace(text.codePointAt(i))) {
                break;
            }
        }
        return text.substring(i);
    }

    private int countIndentation(String text) {
        var i = 0;
        for (; i < text.length(); i++) {
            if (!Character.isWhitespace(text.codePointAt(i))) {
                return i;
            }
        }
        return i;
    }
}
