package de.sonallux.spotify.parser;

import de.sonallux.spotify.core.model.SpotifyWebApi;
import de.sonallux.spotify.core.model.SpotifyWebApiObject;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;

@Slf4j
public class WebApiParser {

    public final static String DEFAULT_WEB_API_DOCUMENTATION_URL = "https://developer.spotify.com/documentation/web-api/reference";
    public static final String DEFAULT_WEB_API_ENDPOINT_URL = "https://api.spotify.com/v1";

    private final ApiObjectParser apiObjectParser;
    private final ApiEndpointParser apiEndpointParser;
    private final ApiScopesParser apiScopesParser;

    public WebApiParser(boolean isInteractive) {
        this.apiObjectParser = new ApiObjectParser();
        this.apiEndpointParser = new ApiEndpointParser(isInteractive);
        this.apiScopesParser = new ApiScopesParser();
    }

    public SpotifyWebApi parse(Path responseTypesFile) throws IOException, ApiParseException {
        return parse(DEFAULT_WEB_API_DOCUMENTATION_URL, DEFAULT_WEB_API_ENDPOINT_URL, responseTypesFile);
    }

    public SpotifyWebApi parse(String documentationUrl, String endpointUrl, Path responseTypesFile) throws IOException, ApiParseException {
        var document = Jsoup.connect(documentationUrl).get();
        return parse(documentationUrl, endpointUrl, document, responseTypesFile);
    }

    public SpotifyWebApi parse(String documentationUrl, String endpointUrl, Document document, Path responseTypesFile) throws IOException, ApiParseException {
        var content = document.body().selectFirst("div.post-content").children();
        var allSections = ParseUtils.splitAt(content, "h1");

        var objects = apiObjectParser.parseSpotifyObjects(allSections, documentationUrl);
        var categories = apiEndpointParser.parseSpotifyApiCategories(allSections, documentationUrl, endpointUrl, responseTypesFile);
        var scopes = apiScopesParser.parseScopes();

        var spotifyWebApi = new SpotifyWebApi(documentationUrl, endpointUrl, objects, categories, scopes);

        var apiPatches = new ApiPatches();
        spotifyWebApi = apiPatches.applyPatches(spotifyWebApi);

        // Sort object properties by name
        for (var object : spotifyWebApi.getObjectList()) {
            object.getProperties().sort(Comparator.comparing(SpotifyWebApiObject.Property::getName));
        }

        ScopeValidator.validateScopes(spotifyWebApi);
        return spotifyWebApi;
    }
}
