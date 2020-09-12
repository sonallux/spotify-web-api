#!/bin/sh

# This script runs the spotify-web-api-generator-open-api to generate the open-api specification.
# This script must be run from the root directory.

API_DOCUMENTATION_FILE=./spotify-web-api-parser/api-documentation.yaml
OUTPUT_FOLDER=./spotify-web-api-generator-open-api/spotify-open-api.yaml

# Find a '*.cli-jar'
cliJarFile=$(find ./spotify-web-api-generator-open-api/target -maxdepth 1 -type f -name "*-cli.jar" -print -quit)

if [ -z "$cliJarFile" ]
then
  echo "Unable to find generator-open-api cli jar file. Did you run './mvnw package'?"
  exit 1
fi

java -jar $cliJarFile -f $API_DOCUMENTATION_FILE -o $OUTPUT_FOLDER --validate
generationExitCode=$?
if [ "$generationExitCode" != "0" ]
then
  echo "Generation failed. aborting..."
  exit $generationExitCode
fi

exit 0
