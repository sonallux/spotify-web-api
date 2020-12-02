package de.sonallux.spotify.generator.java;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardOpenOption.*;

abstract class AbstractTemplate<T> {

    private Template template;
    @Getter
    private JavaPackage basePackage;

    AbstractTemplate<T> loadTemplate(Mustache.Compiler compiler) {
        this.template = compiler.loadTemplate(templateName());
        return this;
    }

    void generate(T t, Path outputFolder, JavaPackage basePackage) throws IOException {
        this.basePackage = basePackage;
        var javaPackage = getJavaPackage(t, basePackage);
        var fullPath = outputFolder.resolve(javaPackage.getPath());
        Files.createDirectories(fullPath);
        var outputFile = fullPath.resolve(getFileName(t));
        try (var writer = Files.newBufferedWriter(outputFile, CREATE, TRUNCATE_EXISTING, WRITE)) {
            var context = new HashMap<String, Object>();
            context.put("package", javaPackage.getName());
            context.put("basePackage", basePackage.getName());
            context.put("javaDoc", new JavaDocLambda());
            template.execute(buildContext(t, context), writer);
        }
    }

    abstract String templateName();
    abstract String getFileName(T t);
    abstract JavaPackage getJavaPackage(T t, JavaPackage basePackage);
    abstract Map<String, Object> buildContext(T t, Map<String, Object> context);
}
