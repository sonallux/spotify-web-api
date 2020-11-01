package de.jsone_studios.spotify.parser;

import de.jsone_studios.spotify.core.model.SpotifyApiCategory;
import de.jsone_studios.spotify.core.model.SpotifyScope;
import de.jsone_studios.spotify.core.model.SpotifyScopes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.stream.Collectors;

class ApiScopesParser {

    private static final String DEFAULT_SCOPES_URL = "https://developer.spotify.com/documentation/general/guides/scopes";

    private String scopesUrl;

    SpotifyScopes parseScopes() throws IOException, ApiParseException {
        return parseScopes(DEFAULT_SCOPES_URL);
    }

    SpotifyScopes parseScopes(String scopesUrl) throws IOException, ApiParseException {
        this.scopesUrl = scopesUrl;
        var document = Jsoup.connect(scopesUrl).get();
        return parseScopes(document);
    }

    private SpotifyScopes parseScopes(Document document) throws ApiParseException {
        var scopes = new ArrayList<SpotifyScope>();

        var content = document.body().selectFirst("div.post-content").children();
        var scopeElements = ParseUtils.splitAt(content, "h2").get(1);
        for (var scopeElement : scopeElements.select("div")) {
            var scope = parseScope(scopeElement);
            if (scopes.contains(scope)) {
                throw new ApiParseException("Scope is defined twice: " + scope.getId());
            }
            scopes.add(scope);
        }
        scopes.sort(Comparator.comparing(SpotifyScope::getId));
        return new SpotifyScopes(scopesUrl, scopes);
    }

    private SpotifyScope parseScope(Element scopeElement) throws ApiParseException {
        String id = scopeElement.attributes().get("id");
        String link = scopesUrl + "/#" + id;
        String text = scopeElement.select("h3").text();
        if (!id.equals(text)) {
            throw new ApiParseException("Scope has id " + id + " and text " + text);
        }

        var tableEntries = scopeElement.select("tr");
        if (tableEntries.size() != 3) {
            throw new ApiParseException("Expected three table entries for scope: " + id);
        }
        var description = tableEntries.get(0).child(1).text();
        var userDescription = tableEntries.get(1).child(1).text();
        var endpoints = new ArrayList<SpotifyScope.EndpointLink>();
        for (var element : tableEntries.get(2).select("li > a")) {
            endpoints.add(parseEndpointLink(element));
        }
        return new SpotifyScope(id, link, description, userDescription, endpoints);
    }

    private SpotifyScope.EndpointLink parseEndpointLink(Element element) throws ApiParseException {
        var absUrl = element.absUrl("href");
        var url = element.attributes().get("href");
        var urlSegments = url.split("/");
        if (urlSegments.length == 6){
            //Links to web-api have 6 segments(/documentation/web-api/reference/<category>/<endpoint>)
            return new SpotifyScope.EndpointLink(absUrl, urlSegments[2], fixEndpoint(urlSegments[5]));
        } else if (urlSegments.length == 3) {
            //Links to other apis have 3 segments (/documentation/<api>)
            return new SpotifyScope.EndpointLink(absUrl, urlSegments[2]);
        } else {
            throw new ApiParseException("Unknown link for scope: " + url);
        }
    }

    private String fixEndpoint(String endpoint) {
        if ("get-shows-episodes".equals(endpoint)) {
            return "endpoint-get-a-shows-episodes";
        } else if ("check-user-following-playlist".equals(endpoint)) {
            return "endpoint-check-if-user-follows-playlist";
        } else if ("reorder-playlists-tracks".equals(endpoint)) {
            return "endpoint-reorder-or-replace-playlists-tracks";
        } else if ("replace-playlists-tracks".equals(endpoint)) {
            return "endpoint-reorder-or-replace-playlists-tracks";
        } else {
            return "endpoint-" + endpoint.replace("several", "multiple");
        }
    }

    void validateScopes(SpotifyScopes scopes, SortedMap<String, SpotifyApiCategory> categories) throws ApiParseException {
        var error = new StringBuilder();

        //Validate if endpoints referenced by scopes are present
        for (var scope : scopes.getScopes()) {
            for (var link : scope.getEndpoints()) {
                if (link.getEndpoint() == null) {
                    continue;
                }
                var endpoint = categories.values().stream()
                        .flatMap(c -> c.getEndpointList().stream())
                        .filter(e -> e.getId().equals(link.getEndpoint()))
                        .findFirst();
                if (endpoint.isEmpty()) {
                    error.append(String.format("Scope %s has unknown endpoint reference: %s", scope.getId(), link.getEndpoint())).append("\n");
                }
            }
        }

        //Validate if scopes referenced by endpoints are present
        var endpointScopes = categories.values().stream().flatMap(c -> c.getEndpointList().stream())
                .flatMap(e -> e.getScopes().stream())
                .distinct()
                .collect(Collectors.toList());
        for (var endpointScope : endpointScopes) {
            var scope = scopes.getScopes().stream().filter(s -> s.getId().equals(endpointScope)).findFirst();
            if (scope.isEmpty()) {
                error.append(String.format("Endpoint has unknown scope: %s", endpointScope)).append("\n");
            }
        }
        var errorText = error.toString();
        if (!errorText.isEmpty()) {
            throw new ApiParseException(errorText);
        }
    }
}
