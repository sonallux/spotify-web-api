package de.jsone_studios.spotify.generator.java.util;

import lombok.EqualsAndHashCode;

import java.nio.file.Path;
import java.util.Arrays;

@EqualsAndHashCode
public class JavaPackage {
    private final String[] packageNames;

    public JavaPackage(String... packageNames) {
        this.packageNames = packageNames;
    }

    public String getName() {
        return String.join(".", packageNames);
    }

    public Path getPath() {
        return Path.of(packageNames[0], Arrays.copyOfRange(packageNames, 1, packageNames.length));
    }

    public JavaPackage parent() {
        return new JavaPackage(Arrays.copyOfRange(packageNames, 0, packageNames.length - 1));
    }

    public JavaPackage child(String name) {
        var newPackageNames = Arrays.copyOf(packageNames, packageNames.length + 1);
        newPackageNames[packageNames.length] = name;
        return new JavaPackage(newPackageNames);
    }

    @Override
    public String toString() {
        return getName();
    }
}
