#!/bin/sh

# This script runs the spotify-web-api-parser to update the api documentation file.
# This script must be run from the root directory.

OUTPUT_FILE=./spotify-web-api-core/src/main/resources/spotify-web-api.yml
RESPONSE_TYPES_FILE=./spotify-web-api-parser/response-types.yml

# Find a '*.cli-jar'
cliJarFile=$(find ./spotify-web-api-parser/target -maxdepth 1 -type f -name "*-cli.jar" -print -quit)

if [ -z "$cliJarFile" ]
then
  echo "Unable to find parser cli jar file. Did you run './mvnw package'?"
  exit 1
fi

java -jar $cliJarFile --response-types $RESPONSE_TYPES_FILE -o $OUTPUT_FILE
generationExitCode=$?
if [ "$generationExitCode" != "0" ]
then
  echo "Parsing failed. aborting..."
  exit $generationExitCode
fi

git diff --exit-code
if [ "$?" != "0" ]
then
  ./mvnw versions:set -DnewVersion="$(date +"%Y.%-m.%-d")" -DgenerateBackupPoms=false
fi

exit 0
