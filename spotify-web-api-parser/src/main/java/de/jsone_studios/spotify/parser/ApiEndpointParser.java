package de.jsone_studios.spotify.parser;

import de.jsone_studios.spotify.parser.model.SpotifyApiCategory;
import de.jsone_studios.spotify.parser.model.SpotifyApiEndpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static de.jsone_studios.spotify.parser.model.SpotifyApiEndpoint.ParameterLocation.*;

@RequiredArgsConstructor
@Slf4j
class ApiEndpointParser {

    private final boolean isInteractive;

    private String documentationUrl;
    private String endpointUrl;
    private ResponseTypeMapper responseTypeMapper;

    List<SpotifyApiCategory> parseSpotifyApiCategories(List<Elements> sections, String documentationUrl, String endpointUrl, Path responseTypesFile) throws ApiParseException {
        this.documentationUrl = documentationUrl;
        this.endpointUrl = endpointUrl;
        try {
            this.responseTypeMapper = new ResponseTypeMapper(responseTypesFile);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new ApiParseException("Failed to initialize response type mapper", e);
        }

        var categories = new ArrayList<SpotifyApiCategory>();
        //The First section is the Reference Index and the last the Objects Index
        for (var element : sections.subList(1, sections.size() - 1)) {
            var category = parseSpotifyApiCategory(element);
            if (categories.contains(category)) {
                throw new ApiParseException("Category " + category.getId() + " is defined twice");
            } else {
                categories.add(category);
            }
        }

        addResponseTypes(categories);

        //Apply fixes
        ApiEndpointFixes.fixApiEndpoints(categories);
        categories.sort(Comparator.comparing(SpotifyApiCategory::getId));
        return categories;
    }

    private void addResponseTypes(List<SpotifyApiCategory> categories) {
        try {
            if (isInteractive) {
                responseTypeMapper.update(categories);
                responseTypeMapper.save();
            }

            for (var category : categories) {
                for (var endpoint : category.getEndpoints()) {
                    var endpointResponse = responseTypeMapper.getEndpointResponse(category.getId(), endpoint.getId());
                    if (endpointResponse == null || endpointResponse.getResponseTypes().isEmpty()) {
                        log.warn("Missing response type in {} for {} {} with response: \n{}\n", category.getId(),
                                endpoint.getHttpMethod(), endpoint.getId(), endpoint.getResponseDescription());
                        continue;
                    }
                    endpoint.setResponseTypes(endpointResponse.getResponseTypes());
                }
            }
        } catch (IOException e) {
            log.error("Failed to load missing response types", e);
        }
    }

    private SpotifyApiCategory parseSpotifyApiCategory(Elements elements) throws ApiParseException {
        var header = elements.first();
        var id = header.attributes().get("id");
        var link = documentationUrl + "/#" + id;
        var name = header.text();

        var endpoints = new ArrayList<SpotifyApiEndpoint>();
        for (var element : ParseUtils.splitAt(elements, "h2")) {
            var endpoint = parseSpotifyApiEndpoint(element);
            if (endpoints.contains(endpoint)) {
                throw new ApiParseException("Endpoint " + endpoint.getId() + " already defined in category " + id);
            } else {
                endpoints.add(endpoint);
            }
        }
        endpoints.sort(Comparator.comparing(SpotifyApiEndpoint::getId));
        return new SpotifyApiCategory(id, name, link, endpoints);
    }

    private SpotifyApiEndpoint parseSpotifyApiEndpoint(Elements elements) throws ApiParseException {
        var header = elements.first();
        var id = header.attributes().get("id");
        var link = documentationUrl + "/#" + id;
        var name = header.text();
        var description = elements.select("p").first().text();
        String httpMethod;
        String path;

        var codeBlocks = elements.select(".highlighter-rouge code");
        if (codeBlocks.size() == 1) {
            var parts = codeBlocks.get(0).text().split(" ");
            httpMethod = parts[0].toUpperCase();
            if (parts[1].startsWith(endpointUrl)) {
                path = parts[1].substring(endpointUrl.length());
            } else {
                throw new ApiParseException("Found different base url for endpoint " + id + ": " + parts[1]);
            }
        } else {
            throw new ApiParseException("Found multiple urls for endpoint: " + id);
        }

        //Remove query parameter from url
        var queryParamStart = path.indexOf('?');
        if (queryParamStart != -1) {
            path = path.substring(0, queryParamStart);
        }

        String responseDescription = null;
        List<SpotifyApiEndpoint.Parameter> parameters = null;

        var h5Sections = ParseUtils.splitAt(elements, "h5");
        for (Elements h5Section : h5Sections) {
            switch (h5Section.get(0).text()) {
                case "Request":
                    parameters = parseRequestParameters(h5Section);
                    break;
                case "Response":
                    responseDescription = parseResponseParameters(h5Section);
                    break;
                default:
                    log.warn("Unknown h5 section in endpoint " + id + ": " + h5Section.get(0).text());
            }
        }

        if (parameters == null) {
            throw new ApiParseException("Can not find request parameters for endpoint " + id);
        }
        if (responseDescription == null) {
            throw new ApiParseException("Can not find response description for endpoint " + id);
        }

        var scopes = extractScopes(id, parameters);

        return new SpotifyApiEndpoint(id, name, link, description, httpMethod, path, parameters, responseDescription, scopes);
    }

    private List<SpotifyApiEndpoint.Parameter> parseRequestParameters(Elements elements) {
        var parameters = new ArrayList<SpotifyApiEndpoint.Parameter>();
        for (var table : elements.select("table")) {
            var locationString = table.selectFirst("thead > tr > th").text();
            var location = parse(locationString);
            for (var entry : table.select("tbody > tr")) {
                var name = entry.selectFirst("code").text();
                if (location == PATH) {
                    name = name.substring(1, name.length() - 1);
                }
                var description = entry.selectFirst("small");
                var type = entry.child(1).text();
                var requiredText = entry.child(2).text();
                if (description == null) {
                    parameters.add(new SpotifyApiEndpoint.Parameter(location, name, "", type, "required".equalsIgnoreCase(requiredText)));
                } else {
                    parameters.add(new SpotifyApiEndpoint.Parameter(location, name, description, type, "required".equalsIgnoreCase(requiredText)));
                }
            }
        }
        return parameters;
    }

    private SpotifyApiEndpoint.ParameterLocation parse(String text) {
        switch (text) {
            case "Header":
                return HEADER;
            case "Path Parameter":
                return PATH;
            case "Query Parameter":
                return QUERY;
            case "JSON Body Parameter":
                return BODY;
            default:
                throw new IllegalArgumentException("Unknown parameter location: " + text);
        }
    }

    private String parseResponseParameters(Elements elements) {
        var responseSection = elements.select("p");
        //Remove p, if it is just a link to the spotify web console
        responseSection.removeIf(e -> "Try in our Web Console".equalsIgnoreCase(e.text()));
        return responseSection.text();
    }

    private List<String> extractScopes(String id, List<SpotifyApiEndpoint.Parameter> parameters) {
        var authHeader = parameters.stream().filter(p -> "Authorization".equals(p.getName())).findFirst();
        if (authHeader.isEmpty()) {
            if (!"endpoint-get-information-about-the-users-current-playback".equals(id)) {
                log.warn("Endpoint {} has no Authorization header", id);
            }
            return new ArrayList<>();
        }
        return authHeader.get().getDescriptionElement()
                .select("code.highlighter-rouge").stream()
                .map(e -> fixScope(e.text()))
                .collect(Collectors.toList());
    }

    private String fixScope(String scope) {
        if ("user-libary-read".equals(scope)) {
            return "user-library-read";
        }
        return scope.split(" ")[0];
    }
}
