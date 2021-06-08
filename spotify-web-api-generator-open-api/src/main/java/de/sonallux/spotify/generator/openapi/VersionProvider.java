package de.sonallux.spotify.generator.openapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class VersionProvider {
    private static final Pattern VERSION_PATTERN = Pattern.compile("<version>([0-9a-z\\.-]+)</version>");

    public static String getVersion() {
        try {
            var lines = Files.readAllLines(Path.of("./pom.xml"));
            var versionLine = -1;
            for (int i = 0; i < lines.size(); i++) {
                if ("<artifactId>spotify-web-api-parent</artifactId>".equals(lines.get(i).trim())) {
                    versionLine = i + 1;
                    break;
                }
            }

            if (versionLine != -1) {
                var matcher = VERSION_PATTERN.matcher(lines.get(versionLine).trim());
                if (matcher.matches()) {
                    return matcher.group(1);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            var versions = new CLI.ManifestVersionProvider().getVersion();
            if (!"unknown".equals(versions[1])) {
                return versions[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "unknown";
    }
}
