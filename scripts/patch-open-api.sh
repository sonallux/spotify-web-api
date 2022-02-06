#!/bin/sh

# This script runs the spotify-web-api-open-api to path the official OpenAPI specification.
# This script must be run from the root directory.

OFFICIAL_OPENAPI_FILE=./official-spotify-open-api.yml
FIXED_OPENAPI_FILE=./fixed-spotify-open-api.yml

# Find a '*.cli-jar'
cliJarFile=$(find ./spotify-web-api-open-api/target -maxdepth 1 -type f -name "*-cli.jar" -print -quit)

if [ -z "$cliJarFile" ]
then
  echo "Unable to find cli jar file. Did you run './mvnw package'?"
  exit 1
fi

java -jar $cliJarFile -f $OFFICIAL_OPENAPI_FILE -o $FIXED_OPENAPI_FILE --validate
exitCode=$?
if [ "$exitCode" != "0" ]
then
  echo "Patching official OpenAPI failed. aborting..."
  exit $exitCode
fi

exit 0
