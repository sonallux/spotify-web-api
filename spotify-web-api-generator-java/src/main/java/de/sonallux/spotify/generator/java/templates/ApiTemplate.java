package de.sonallux.spotify.generator.java.templates;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.base.CaseFormat;
import de.sonallux.spotify.core.model.SpotifyWebApiCategory;
import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;
import de.sonallux.spotify.generator.java.EndpointHelper;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import de.sonallux.spotify.generator.java.util.JavaUtils;
import de.sonallux.spotify.generator.java.util.Markdown2Html;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ApiTemplate {
    private Mustache apiTemplate;
    private Mustache requestTemplate;

    private Path outputFolder;

    public ApiTemplate loadTemplate(MustacheFactory mustacheFactory) {
        this.apiTemplate = mustacheFactory.compile("templates/api.mustache");
        this.requestTemplate = mustacheFactory.compile("templates/request.mustache");
        return this;
    }

    public void generate(SpotifyWebApiCategory category, Path outputFolder, JavaPackage basePackage) throws IOException {
        this.outputFolder = outputFolder;

        var apisJavaPackage = basePackage.child("apis");
        var className = JavaUtils.getClassName(category);
        var requestsJavaPackage = apisJavaPackage.child(className.replace("Api", "").toLowerCase());

        var context = new HashMap<String, Object>();
        context.put("package", apisJavaPackage.getName());
        context.put("requestsPackage", requestsJavaPackage.getName());
        context.put("name", category.getName());
        context.put("className", className);
        context.put("documentationLink", category.getLink());
        var endpointContexts = new ArrayList<>();
        for (var endpoint : category.getEndpointList()) {
            endpointContexts.add(buildEndpointContext(endpoint, requestsJavaPackage));
        }
        context.put("endpoints", endpointContexts);

        var packageFolder = getPackageFolder(outputFolder, apisJavaPackage);
        var outputFile = packageFolder.resolve(JavaUtils.getFileName(className));
        generateFile(outputFile, apiTemplate, context);
    }

    private Path getPackageFolder(Path baseFolder, JavaPackage javaPackage) throws IOException {
        var packageFolder = baseFolder.resolve(javaPackage.getPath());
        Files.createDirectories(packageFolder);
        return packageFolder;
    }

    private void generateFile(Path outputFile, Mustache mustacheTemplate, Object scope) throws IOException {
        try (var writer = Files.newBufferedWriter(outputFile, CREATE, TRUNCATE_EXISTING, WRITE)) {
            mustacheTemplate.execute(writer, scope);
        }
    }

    private Map<String, Object> buildEndpointContext(SpotifyWebApiEndpoint endpoint, JavaPackage javaPackage) throws IOException {
        var requiredParameters = generateEndpointRequest(endpoint, javaPackage);

        var context = new HashMap<String, Object>();
        var methodName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, JavaUtils.shrinkEndpointId(endpoint));
        context.put("methodName", methodName);
        context.put("name", endpoint.getName());
        context.put("description", Markdown2Html.convertToSingleLine(endpoint.getDescription()));
        context.put("requestBuilder", JavaUtils.getEndpointRequestBuilderName(endpoint));

        context.put("requiredParameters", requiredParameters.stream()
            .map(Parameter::asMethodParameter)
            .collect(joining(", ")));
        context.put("javaDocParameters", requiredParameters.stream()
            .map(Parameter::asJavaDoc)
            .collect(toList()));

        context.put("arguments", Stream.concat(Stream.of(new Parameter("apiClient", "ApiClient", "")), requiredParameters.stream())
            .map(Parameter::getJavaName)
            .collect(joining(", ")));

        return context;
    }

    private List<Parameter> generateEndpointRequest(SpotifyWebApiEndpoint endpoint, JavaPackage javaPackage) throws IOException {
        EndpointHelper.fixDuplicateEndpointParameters(endpoint);

        var context = new HashMap<String, Object>();
        context.put("package", javaPackage.getName());
        context.put("name", endpoint.getName() + " request");
        context.put("documentationLink", endpoint.getLink());
        context.put("className", JavaUtils.getEndpointRequestBuilderName(endpoint));
        context.put("httpMethod", endpoint.getHttpMethod());
        context.put("path", endpoint.getPath());
        context.put("responseType", getResponseType(endpoint));
        context.put("responseDescription", Markdown2Html.convertToLines(endpoint.getResponseDescription()));
        if (endpoint.getNotes() != null) {
            context.put("hasNotes", true);
            context.put("notes", Markdown2Html.convertToLines(endpoint.getNotes()));
        }
        if (endpoint.getScopes().size() > 0) {
            context.put("scopes", String.join(", ", endpoint.getScopes()));
        }

        var requiredPathParameters = new ArrayList<Parameter>();
        var requiredQueryParameters = new ArrayList<Parameter>();
        var requiredBodyParameters = new ArrayList<Parameter>();
        var optionalPathParameters = new ArrayList<Parameter>();
        var optionalQueryParameters = new ArrayList<Parameter>();
        var optionalBodyParameters = new ArrayList<Parameter>();

        for (var parameter : endpoint.getParameters()) {
            switch (parameter.getLocation()) {
                case PATH: {
                    addParameter(parameter, requiredPathParameters, optionalPathParameters);
                    break;
                }
                case QUERY: {
                    addParameter(parameter, requiredQueryParameters, optionalQueryParameters);
                    break;
                }
                case BODY: {
                    addParameter(parameter, requiredBodyParameters, optionalBodyParameters);
                    break;
                }
            }
        }

        context.put("requiredPathParameters", requiredPathParameters);
        context.put("requiredQueryParameters", requiredQueryParameters);
        context.put("requiredBodyParameters", requiredBodyParameters);
        context.put("optionalPathParameters", optionalPathParameters);
        context.put("optionalQueryParameters", optionalQueryParameters);
        context.put("optionalBodyParameters", optionalBodyParameters);

        List<Parameter> requiredParameterList = new ArrayList<>();
        requiredParameterList.add(new Parameter("apiClient", "ApiClient", "The API client"));
        requiredParameterList.addAll(requiredPathParameters);
        requiredParameterList.addAll(requiredQueryParameters);
        requiredParameterList.addAll(requiredBodyParameters);
        context.put("requiredParameters", requiredParameterList.stream()
            .map(Parameter::asMethodParameter)
            .collect(joining(", ")));

        context.put("requiredJavaDocParameters", requiredParameterList.stream().map(Parameter::asJavaDoc).collect(toList()));

        optionalQueryParameters.stream()
            .filter(p -> "additional_types".equals(p.getName()))
            .findFirst().ifPresent(additionalTypesParameter -> {
                additionalTypesParameter.setDefaultValue("\"track,episode\"");
                context.put("parametersWithDefaultValue", List.of(additionalTypesParameter));
        });

        var packageFolder = getPackageFolder(outputFolder, javaPackage);
        var outputFile = packageFolder.resolve(JavaUtils.getFileName(JavaUtils.getEndpointRequestBuilderName(endpoint)));
        generateFile(outputFile, requestTemplate, context);

        return requiredParameterList.subList(1, requiredParameterList.size());
    }

    private void addParameter(SpotifyWebApiEndpoint.Parameter parameter, List<Parameter> requiredParams, List<Parameter> optionalParams) {
        var mappedParameter = new Parameter(parameter.getName(), parameter.getType(), parameter.getDescription());
        if (parameter.isRequired()) {
            requiredParams.add(mappedParameter);
        } else {
            optionalParams.add(mappedParameter);
        }
    }

    private String getResponseType(SpotifyWebApiEndpoint endpoint) {
        if (endpoint.getResponseTypes().stream()
            .map(SpotifyWebApiEndpoint.ResponseType::getType)
            .distinct().count() == 1) {
            return JavaUtils.mapToPrimitiveJavaType(endpoint.getResponseTypes().get(0).getType());
        }
        var nonVoidResponseTypes = endpoint.getResponseTypes().stream()
            .map(SpotifyWebApiEndpoint.ResponseType::getType).filter(t -> !"Void".equals(t)).distinct().collect(toList());
        if (nonVoidResponseTypes.size() == 1) {
            return JavaUtils.mapToPrimitiveJavaType(endpoint.getResponseTypes().get(0).getType());
        }
        return "";
    }

    @Getter
    @Setter
    private static class Parameter {
        private String name;
        private String javaName;
        private String type;
        private String description;
        private String defaultValue;

        public Parameter(String name, String type, String description) {
            this.name = name;
            this.javaName = JavaUtils.escapeFieldName(name);
            this.type = JavaUtils.mapToPrimitiveJavaType(type);
            this.description = Markdown2Html.convertToSingleLine(description);
            this.defaultValue = null;
        }

        public String asMethodParameter() {
            return type + " " + javaName;
        }
        public String asJavaDoc() {
            return "@param " + javaName + " " + description;
        }
    }
}
