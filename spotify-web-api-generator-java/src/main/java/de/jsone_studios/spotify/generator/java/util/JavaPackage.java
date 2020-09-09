package de.jsone_studios.spotify.generator.java.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.EqualsAndHashCode;

import java.nio.file.Path;
import java.util.Arrays;

@EqualsAndHashCode
public class JavaPackage {
    private final String[] packageNames;

    private JavaPackage(String... packageNames) {
        this.packageNames = packageNames;
    }

    public static JavaPackage fromNames(String... packageNames) {
        Preconditions.checkArgument(isValidJavaPackageName(packageNames), "Invalid java package");
        return new JavaPackage(packageNames);
    }

    public static JavaPackage fromPackage(String packageName) {
        Preconditions.checkArgument(packageName != null);
        var packageNames = packageName.split("\\.");
        Preconditions.checkArgument(isValidJavaPackageName(packageNames), "Invalid java package");
        return new JavaPackage(packageNames);
    }

    private static boolean isValidJavaPackageName(String[] packageNames) {
        if (packageNames.length == 0) {
            return false;
        }
        for (String packageName : packageNames) {
            if (Strings.isNullOrEmpty(packageName)) {
                return false;
            }
        }
        return true;
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
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "Invalid java package");
        var newPackageNames = Arrays.copyOf(packageNames, packageNames.length + 1);
        newPackageNames[packageNames.length] = name;
        return new JavaPackage(newPackageNames);
    }

    @Override
    public String toString() {
        return getName();
    }
}
