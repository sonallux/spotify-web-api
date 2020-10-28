package de.jsone_studios.spotify.generator.java;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class JavaDocLambda implements Mustache.Lambda {

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        var fullText = frag.execute();
        if (fullText.isBlank()) {
            return;
        }
        var lines = fullText.split("\n");
        var intendCount = Arrays.stream(lines).filter(s -> !s.isBlank()).mapToInt(this::countIndentation).min().orElse(0);
        var intend = " ".repeat(intendCount >= 4 ? intendCount - 4 : intendCount);
        out.write(intend + "/**\n");
        for (var line : lines) {
            if (line.isBlank()) {
                out.write(intend + " * \n");
            } else {
                out.write(intend + " * " + line.substring(intendCount) + "\n");
            }
        }
        out.write(intend + " */\n");
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
