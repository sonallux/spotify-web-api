#!/bin/sh

# This script runs the spotify-web-api-generator-ts to generate the TypeScript model definitions.
# This script must be run from the root directory.

API_DOCUMENTATION_FILE=./spotify-web-api-core/src/main/resources/spotify-web-api.yml
OUTPUT_FILE=./spotify-web-api-generator-ts/models.d.ts

# Find a '*.cli-jar'
cliJarFile=$(find ./spotify-web-api-generator-ts/target -maxdepth 1 -type f -name "*-cli.jar" -print -quit)

if [ -z "$cliJarFile" ]
then
  echo "Unable to find generator-ts cli jar file. Did you run './mvnw package'?"
  exit 1
fi

java -jar $cliJarFile -f $API_DOCUMENTATION_FILE -o $OUTPUT_FILE
generationExitCode=$?
if [ "$generationExitCode" != "0" ]
then
  echo "Generation failed. aborting..."
  exit $generationExitCode
fi

exit 0
