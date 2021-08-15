package de.sonallux.spotify.parser;

import de.sonallux.spotify.core.model.SpotifyWebApiCategory;
import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import static de.sonallux.spotify.core.model.SpotifyWebApiEndpoint.ParameterLocation.*;

@RequiredArgsConstructor
@Slf4j
class ApiEndpointParser {

    private final boolean isInteractive;

    private String documentationUrl;
    private String endpointUrl;
    private ResponseTypeMapper responseTypeMapper;

    SortedMap<String, SpotifyWebApiCategory> parseSpotifyApiCategories(List<Elements> sections, String documentationUrl, String endpointUrl, Path responseTypesFile) throws ApiParseException {
        this.documentationUrl = documentationUrl;
        this.endpointUrl = endpointUrl;
        try {
            this.responseTypeMapper = new ResponseTypeMapper(responseTypesFile);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new ApiParseException("Failed to initialize response type mapper", e);
        }

        var categories = new TreeMap<String, SpotifyWebApiCategory>();
        //The First section is the Reference Index and the last the Objects Index
        for (var element : sections.subList(1, sections.size() - 1)) {
            var category = parseSpotifyApiCategory(element);
            if (categories.containsKey(category.getId())) {
                throw new ApiParseException("Category " + category.getId() + " is defined twice");
            } else {
                categories.put(category.getId(), category);
            }
        }

        addResponseTypes(categories);

        //Apply fixes
        ApiEndpointFixes.fixApiEndpoints(categories);
        return categories;
    }

    private void addResponseTypes(SortedMap<String, SpotifyWebApiCategory> categories) {
        try {
            if (isInteractive) {
                responseTypeMapper.update(new ArrayList<>(categories.values()));
            }

            for (var category : categories.values()) {
                for (var endpoint : category.getEndpointList()) {
                    var responseTypes = responseTypeMapper.getEndpointResponseTypes(category.getId(), endpoint);
                    if (responseTypes == null || responseTypes.isEmpty()) {
                        log.warn("Missing response type in {} for {} {} with response: \n{}\n", category.getId(),
                                endpoint.getHttpMethod(), endpoint.getId(), endpoint.getResponseDescription());
                        continue;
                    }
                    endpoint.setResponseTypes(responseTypes);
                }
            }

            responseTypeMapper.save();
        } catch (IOException e) {
            log.error("Failed to load missing response types", e);
        }
    }

    private SpotifyWebApiCategory parseSpotifyApiCategory(Elements elements) throws ApiParseException {
        var header = elements.first();
        var id = header.attributes().get("id");
        var link = documentationUrl + "/#" + id;
        var name = header.text();

        var endpoints = new TreeMap<String, SpotifyWebApiEndpoint>();
        for (var element : ParseUtils.splitAt(elements, "h2")) {
            var endpoint = parseSpotifyApiEndpoint(element);
            if (endpoints.containsKey(endpoint.getId())) {
                throw new ApiParseException("Endpoint " + endpoint.getId() + " already defined in category " + id);
            } else {
                endpoints.put(endpoint.getId(), endpoint);
            }
        }
        return new SpotifyWebApiCategory(id, name, link, endpoints);
    }

    private SpotifyWebApiEndpoint parseSpotifyApiEndpoint(Elements elements) throws ApiParseException {
        var header = elements.first();
        var id = header.attributes().get("id");
        var link = documentationUrl + "/#" + id;
        var name = header.text();
        var description = Html2Markdown.convert(elements.select("p").first());
        String httpMethod;
        String path;

        var codeBlocks = elements.select(".hidden-xs .highlighter-rouge code");
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
        List<SpotifyWebApiEndpoint.Parameter> parameters = null;
        String notes = null;

        var h5Sections = ParseUtils.splitAt(elements, "h5");
        for (Elements h5Section : h5Sections) {
            switch (h5Section.get(0).text()) {
                case "Request":
                    parameters = parseRequestParameters(h5Section);
                    break;
                case "Response":
                    responseDescription = parseResponseParameters(h5Section.subList(1, h5Section.size()));
                    break;
                case "Notes":
                    notes = parseNotes(h5Section.subList(1, h5Section.size()));
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

        var requestBody = extractRequestBody(parameters);

        var scopes = extractScopes(id, parameters);

        return new SpotifyWebApiEndpoint(id, name, link, description, httpMethod, path, parameters, requestBody, responseDescription, scopes, notes);
    }

    private SpotifyWebApiEndpoint.RequestBody extractRequestBody(List<SpotifyWebApiEndpoint.Parameter> parameters) {
        var bodyParameters = new ArrayList<SpotifyWebApiEndpoint.Parameter>();
        var paramIterator = parameters.iterator();
        while (paramIterator.hasNext()) {
            var param = paramIterator.next();
            if (param.getLocation() == BODY) {
                bodyParameters.add(param);
                paramIterator.remove();
            }
        }

        if (bodyParameters.isEmpty()) {
            return null;
        }

        return new SpotifyWebApiEndpoint.JsonRequestBody("", bodyParameters);
    }

    private List<SpotifyWebApiEndpoint.Parameter> parseRequestParameters(Elements elements) {
        var parameters = new ArrayList<SpotifyWebApiEndpoint.Parameter>();
        for (var table : elements.select("table")) {
            var locationString = table.selectFirst("thead > tr > th").text();
            var location = parse(locationString);
            for (var entry : table.select("tbody > tr")) {
                var name = entry.selectFirst("code").text();
                if (location == PATH) {
                    // Remove surrounding curly brackets
                    name = name.substring(1, name.length() - 1);
                }

                var descriptionElement = entry.selectFirst("small");
                var description = descriptionElement == null ? "" : Html2Markdown.convert(descriptionElement);
                var type = entry.child(1).text();
                var requiredText = entry.child(2).text();

                parameters.add(new SpotifyWebApiEndpoint.Parameter(location, name, description, type, "required".equalsIgnoreCase(requiredText)));
            }
        }
        return parameters;
    }

    private SpotifyWebApiEndpoint.ParameterLocation parse(String text) {
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

    private String parseResponseParameters(List<Element> elements) {
        //Remove, if it is just a link to the spotify web console
        elements.removeIf(e -> "Try in our Web Console".equalsIgnoreCase(e.text()));
        return Html2Markdown.convert(elements);
    }

    private String parseNotes(List<Element> elements) {
        //Remove, if it is just a link to the spotify web console
        elements.removeIf(e -> "Try in our Web Console".equalsIgnoreCase(e.text()));
        return Html2Markdown.convert(elements);
    }

    private List<String> extractScopes(String id, List<SpotifyWebApiEndpoint.Parameter> parameters) {
        var authHeader = parameters.stream().filter(p -> "Authorization".equals(p.getName())).findFirst();
        if (authHeader.isEmpty()) {
            log.warn("Endpoint {} has no Authorization header", id);
            return new ArrayList<>();
        }

        var codePattern = Pattern.compile("`([a-z-]+)`");
        var matcher = codePattern.matcher(authHeader.get().getDescription());
        var scopes = new ArrayList<String>();
        while (matcher.find()) {
            scopes.add(matcher.group(1));
        }
        return scopes;
    }
}
