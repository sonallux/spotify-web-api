package de.sonallux.spotify.generator.java.templates;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardOpenOption.*;

public abstract class AbstractTemplate<T> {

    private Mustache mustacheTemplate;
    @Getter
    private JavaPackage basePackage;

    public AbstractTemplate<T> loadTemplate(MustacheFactory mustacheFactory) {
        var fileName = String.format("templates/%s.mustache", templateName());
        this.mustacheTemplate = mustacheFactory.compile(fileName);
        return this;
    }

    public void generate(T t, Path outputFolder, JavaPackage basePackage) throws IOException {
        this.basePackage = basePackage;
        var javaPackage = getJavaPackage(t, basePackage);
        var fullPath = outputFolder.resolve(javaPackage.getPath());
        Files.createDirectories(fullPath);
        var outputFile = fullPath.resolve(getFileName(t));
        try (var writer = Files.newBufferedWriter(outputFile, CREATE, TRUNCATE_EXISTING, WRITE)) {
            var context = new HashMap<String, Object>();
            context.put("package", javaPackage.getName());
            context.put("basePackage", basePackage.getName());
            mustacheTemplate.execute(writer, buildContext(t, context));
        }
    }

    abstract String templateName();
    abstract String getFileName(T t);
    abstract JavaPackage getJavaPackage(T t, JavaPackage basePackage);
    abstract Map<String, Object> buildContext(T t, Map<String, Object> context);
}
