package de.jsone_studios.spotify.generator.java.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JavaPackageTest {

    @Test
    void fromNamesWithInvalidPackage() {
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromNames());
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromNames((String) null));
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromNames(""));
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromNames(null, ""));
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromNames("", null));
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromNames("test", null, ""));
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromNames("test", "", null));
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromNames(null, "test"));
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromNames("", "test"));
    }

    @Test
    void fromNamesWithValidPackage() {
        assertEquals("test", JavaPackage.fromNames("test").getName());
        assertEquals("test.bar", JavaPackage.fromNames("test", "bar").getName());
        var testArray = new String[]{"test", "foo", "bar"};
        assertEquals("test.foo.bar", JavaPackage.fromNames(testArray).getName());
    }

    @Test
    void fromPackageWithInvalidPackage() {
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromPackage(null));
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromPackage(""));
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromPackage("."));
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromPackage(".test"));
        assertThrows(IllegalArgumentException.class, () -> JavaPackage.fromPackage("foo..bar"));
    }

    @Test
    void fromPackageWithValidPackage() {
        assertEquals("test", JavaPackage.fromPackage("test").getName());
        assertEquals("test", JavaPackage.fromPackage("test.").getName());
        assertEquals("test.bar", JavaPackage.fromPackage("test.bar").getName());
        assertEquals("test.foo.bar", JavaPackage.fromPackage("test.foo.bar").getName());

    }
}
