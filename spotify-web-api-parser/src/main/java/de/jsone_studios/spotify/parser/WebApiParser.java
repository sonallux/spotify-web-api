package de.jsone_studios.spotify.parser;

import de.jsone_studios.spotify.core.model.SpotifyApiDocumentation;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.Path;

@Slf4j
public class WebApiParser {

    public final static String DEFAULT_WEB_API_DOCUMENTATION_URL = "https://developer.spotify.com/documentation/web-api/reference-beta";
    public static final String DEFAULT_WEB_API_ENDPOINT_URL = "https://api.spotify.com/v1";

    private final ApiObjectParser apiObjectParser;
    private final ApiEndpointParser apiEndpointParser;
    private final ApiScopesParser apiScopesParser;

    public WebApiParser(boolean isInteractive) {
        this.apiObjectParser = new ApiObjectParser();
        this.apiEndpointParser = new ApiEndpointParser(isInteractive);
        this.apiScopesParser = new ApiScopesParser();
    }

    public SpotifyApiDocumentation parse(Path responseTypesFile) throws IOException, ApiParseException {
        return parse(DEFAULT_WEB_API_DOCUMENTATION_URL, DEFAULT_WEB_API_ENDPOINT_URL, responseTypesFile);
    }

    public SpotifyApiDocumentation parse(String documentationUrl, String endpointUrl, Path responseTypesFile) throws IOException, ApiParseException {
        var document = Jsoup.connect(documentationUrl).get();
        return parse(documentationUrl, endpointUrl, document, responseTypesFile);
    }

    public SpotifyApiDocumentation parse(String documentationUrl, String endpointUrl, Document document, Path responseTypesFile) throws IOException, ApiParseException {
        var content = document.body().selectFirst("div.post-content").children();
        var allSections = ParseUtils.splitAt(content, "h1");

        var objects = apiObjectParser.parseSpotifyObjects(allSections, documentationUrl);
        var categories = apiEndpointParser.parseSpotifyApiCategories(allSections, documentationUrl, endpointUrl, responseTypesFile);
        var scopes = apiScopesParser.parseScopes();
        apiScopesParser.validateScopes(scopes, categories);
        return new SpotifyApiDocumentation(documentationUrl, endpointUrl, objects, categories, scopes);
    }
}
