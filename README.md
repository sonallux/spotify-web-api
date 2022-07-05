# Spotify Web API Tools

[![Build](https://github.com/sonallux/spotify-web-api/workflows/Build/badge.svg)](https://github.com/sonallux/spotify-web-api/actions?query=workflow%3ABuild)
[![Update API documentation](https://github.com/sonallux/spotify-web-api/workflows/Update%20API%20documentation/badge.svg)](https://github.com/sonallux/spotify-web-api/actions?query=workflow%3A%22Update+API+documentation%22)
[![Maven Central](https://img.shields.io/maven-central/v/de.sonallux.spotify/spotify-web-api-parent.svg?label=Maven%20Central)](https://search.maven.org/artifact/de.sonallux.spotify/spotify-web-api-parent)
[![GitHub](https://img.shields.io/github/license/sonallux/spotify-web-api)](https://github.com/sonallux/spotify-web-api/blob/main/LICENSE)

This monorepo contains tools for fixing and improving the [official Spotify OpenAPI definition](https://developer.spotify.com/_data/documentation/web-api/reference/open-api-schema.yml). The fixed OpenAPI definition can be found in the [fixed-spotify-open-api.yml](fixed-spotify-open-api.yml) file.

## Modules

| Module                                                                             | Description                                                         |
|------------------------------------------------------------------------------------|---------------------------------------------------------------------|
| [json-utils](json-utils/README.md)                                                 | Utility classes for JSON/YAML                                       |
| [spotify-web-api-open-api](spotify-web-api-open-api/README.md)                     | Apply the fixes and improvements to the official OpenAPI definition |

## Versioning
Unfortunately Spotify does not provide any version information with their Web API reference documentation. Therefore, I do **not** follow [semantic versioning](https://semver.org) when releasing new versions. Version do follow a schema based on the release date: `<year>.<month>.<day>` (e.g. a version released on 30th March 2021 will get the version number `2021.3.30`).

## Disclaimer
Because the documentation are only based on the Spotify Web API Reference, there might be difference to the actual behaviour of the Spotify Web API. Also, neither do I have any connections to Spotify nor am I an employee at Spotify.

## How to release a new version

Just start the Release workflow in GitHub Actions or perform the following steps manually

1. Update the version number with `./mvnw versions:set -DnewVersion="<version>" -DgenerateBackupPoms=false`
2. Run OpenApiGenerator to update the openapi definition with new version
3. Commit and push changes to GitHub
4. Wait till CI is green
5. Tag and push the commit created in step 1. A GitHub actions workflow will automatically deploy the artifacts to Maven Central.
