package de.jsone_studios.spotify.parser;

import de.jsone_studios.spotify.parser.model.SpotifyApiDocumentation;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public class WebApiParser {

    public final static String DEFAULT_WEB_API_DOCUMENTATION_URL = "https://developer.spotify.com/documentation/web-api/reference-beta";
    public static final String DEFAULT_WEB_API_ENDPOINT_URL = "https://api.spotify.com/v1";

    private ApiObjectParser apiObjectParser;
    private ApiEndpointParser apiEndpointParser;
    private ApiScopesParser apiScopesParser;

    public WebApiParser() {
        this.apiObjectParser = new ApiObjectParser();
        this.apiEndpointParser = new ApiEndpointParser();
        this.apiScopesParser = new ApiScopesParser();
    }

    public SpotifyApiDocumentation parse() throws IOException, ApiParseException {
        return parse(DEFAULT_WEB_API_DOCUMENTATION_URL, DEFAULT_WEB_API_ENDPOINT_URL);
    }

    public SpotifyApiDocumentation parse(String documentationUrl, String endpointUrl) throws IOException, ApiParseException {
        var document = Jsoup.connect(documentationUrl).get();
        return parse(documentationUrl, endpointUrl, document);
    }

    public SpotifyApiDocumentation parse(String documentationUrl, String endpointUrl, Document document) throws IOException, ApiParseException {
        var content = document.body().selectFirst("div.post-content").children();
        var allSections = ParseUtils.splitAt(content, "h1");

        var objects = apiObjectParser.parseSpotifyObjects(allSections, documentationUrl);
        var categories = apiEndpointParser.parseSpotifyApiCategories(allSections, documentationUrl, endpointUrl);
        var scopes = apiScopesParser.parseScopes();
        apiScopesParser.validateScopes(scopes, categories);
        return new SpotifyApiDocumentation(documentationUrl, endpointUrl, objects, categories, scopes);
    }
}
