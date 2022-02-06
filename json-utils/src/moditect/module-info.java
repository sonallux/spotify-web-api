module de.sonallux.json {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    // JavaDoc does not like this
    // requires com.jayway.jsonpath;
    requires static lombok;

    exports de.sonallux.json;
}
