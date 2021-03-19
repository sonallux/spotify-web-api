package de.sonallux.spotify.generator.java.templates;

import com.google.common.base.CaseFormat;
import de.sonallux.spotify.core.model.SpotifyWebApiCategory;
import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;
import de.sonallux.spotify.generator.java.EndpointRequestBodyHelper;
import de.sonallux.spotify.generator.java.util.JavaPackage;
import de.sonallux.spotify.generator.java.util.JavaUtils;
import de.sonallux.spotify.generator.java.util.Markdown2Html;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CategoryTemplate extends AbstractTemplate<SpotifyWebApiCategory> {

    @Override
    String templateName() {
        return "category";
    }

    @Override
    JavaPackage getJavaPackage(SpotifyWebApiCategory category, JavaPackage basePackage) {
        return basePackage.child("apis");
    }

    @Override
    String getFileName(SpotifyWebApiCategory category) {
        return JavaUtils.getFileName(JavaUtils.getClassName(category));
    }

    @Override
    Map<String, Object> buildContext(SpotifyWebApiCategory category, Map<String, Object> context) {
        context.put("modelsPackage", getBasePackage().child("models").getName());
        context.put("name", category.getName());
        context.put("className", JavaUtils.getClassName(category));
        context.put("documentationLink", category.getLink());
        context.put("endpoints", category.getEndpointList().stream().flatMap(e -> buildEndpointContext(e).stream()).collect(toList()));
        return context;
    }

    private List<Map<String, Object>> buildEndpointContext(SpotifyWebApiEndpoint endpoint) {
        var baseContext = new HashMap<String, Object>();
        var methodName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, JavaUtils.shrinkEndpointId(endpoint));
        baseContext.put("methodName", methodName);
        baseContext.put("name", endpoint.getName());
        baseContext.put("description", Markdown2Html.convertToSingleLine(endpoint.getDescription()));
        var responseDescriptionLines = Markdown2Html.convertToLines(endpoint.getResponseDescription());
        baseContext.put("responseDescriptionFirstLine", responseDescriptionLines.get(0));
        baseContext.put("responseDescriptionOthers", responseDescriptionLines.subList(1, responseDescriptionLines.size()));
        if (endpoint.getNotes() != null) {
            baseContext.put("hasNotes", true);
            baseContext.put("notes", Markdown2Html.convertToLines(endpoint.getNotes()));
        }
        if (endpoint.getScopes().size() > 0) {
            baseContext.put("scopes", String.join(", ", endpoint.getScopes()));
        }
        baseContext.put("httpMethod", endpoint.getHttpMethod());
        baseContext.put("path", endpoint.getPath());
        baseContext.put("responseType", getResponseType(endpoint));
        baseContext.put("documentationLink", endpoint.getLink());

        EndpointRequestBodyHelper.fixDuplicateEndpointParameters(endpoint);

        var rawArguments = argumentsFromEndpoint(endpoint);
        List<Parameter> requiredParameters = new ArrayList<>();
        List<Parameter> optionalParameters = new ArrayList<>();
        for (var rawArgument : rawArguments) {
            if (rawArgument.isRequired()) {
                requiredParameters.add(rawArgument);
            } else {
                optionalParameters.add(rawArgument);
            }
        }

        requiredParameters.sort(Comparator.comparing(Parameter::getOrder));
        optionalParameters.sort(Comparator.comparing(Parameter::getOrder));

        if (endpoint.getHttpMethod().equals("DELETE") && rawArguments.stream().anyMatch(a -> a.getAnnotation().startsWith("@Body"))) {
            // Officially DELETE does not allow a request body, but Spotify uses it.
            // This adjusts the http method annotation, so retrofit does not throw an error.
            baseContext.put("deleteWithBody", true);
        }

        List<Parameter> allParameters;
        if (optionalParameters.size() == 0) {
            baseContext.put("requiredParametersMethod", false);
            allParameters = requiredParameters;
        } else {
            allParameters = new ArrayList<>(requiredParameters);
            var arguments = requiredParameters.stream().map(Parameter::getFieldName).collect(toList());

            // If multiple query parameters are optional, wrap them together in one @QueryMap parameter
            var optionalArgumentsWithoutQuery = optionalParameters.stream().filter(a -> !a.getAnnotation().startsWith("@Query")).collect(toList());
            if (optionalParameters.size() - optionalArgumentsWithoutQuery.size() > 1) {
                allParameters.add(new Parameter("@QueryMap",  "java.util.Map<String, Object>",  "queryParameters", "A map of optional query parameters", false, 4));
                arguments.add("java.util.Map.of()");
                optionalParameters = optionalArgumentsWithoutQuery;
            }

            optionalParameters.forEach(optionalArg -> {
                allParameters.add(optionalArg);
                arguments.add(getDefaultArgumentValue(endpoint, optionalArg));
            });
            baseContext.put("requiredParametersMethod", true);
            baseContext.put("requiredParameters", requiredParameters.stream().map(Parameter::asMethodArgumentWithoutAnnotation).collect(joining(", ")));
            baseContext.put("requiredJavaDocParameters", requiredParameters.stream().map(Parameter::asJavaDoc).collect(toList()));
            baseContext.put("arguments", String.join(", ", arguments));
        }

        baseContext.put("parameters", allParameters.stream().map(Parameter::asMethodArgument).collect(joining(", ")));
        baseContext.put("javaDocParameters", allParameters.stream().map(Parameter::asJavaDoc).collect(toList()));
        return List.of(baseContext);
    }

    private String getDefaultArgumentValue(SpotifyWebApiEndpoint endpoint, Parameter parameter) {
        if (parameter.getAnnotation().startsWith("@Body")) {
            return "new " + EndpointRequestBodyHelper.getEndpointRequestBodyName(endpoint) + "()";
        } else {
            return "null";
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

    private List<Parameter> argumentsFromEndpoint(SpotifyWebApiEndpoint endpoint) {
        var hasBodyParameter = new AtomicBoolean(false);
        var hasRequiredBodyParameter = new AtomicBoolean(false);

        var arguments = endpoint.getParameters().stream().map(parameter -> {
            switch (parameter.getLocation()) {
                case PATH: {
                    return new Parameter("@Path(\"" + parameter.getName() + "\")", parameter, 1);
                }
                case QUERY: {
                    return new Parameter("@Query(\"" + parameter.getName() + "\")", parameter, 2);
                }
                case BODY: {
                    hasBodyParameter.set(true);
                    if (parameter.isRequired()) {
                        hasRequiredBodyParameter.set(true);
                    }
                    return null;
                }
                case HEADER: // Ignore header parameters because they are only Authorization and Content-Type header
                default: return null;
            }
        }).filter(Objects::nonNull).collect(toList());

        if (hasBodyParameter.get()) {
            var requestBodyType = EndpointRequestBodyHelper.getEndpointRequestBodyName(endpoint);
            arguments.add(new Parameter("@Body", requestBodyType, "requestBody", "The request body", hasRequiredBodyParameter.get(), 3));
        }
        return arguments;
    }

    @Getter
    @Setter
    private static class Parameter {
        private String annotation;
        private String type;
        private String fieldName;
        private String description;
        private boolean isRequired;
        private int order;

        public Parameter(String annotation, String type, String fieldName, String description, boolean isRequired, int order) {
            this.annotation = annotation;
            this.type = type;
            this.fieldName = JavaUtils.escapeFieldName(fieldName);
            this.description = Markdown2Html.convertToSingleLine(description);
            this.isRequired = isRequired;
            this.order = order;
        }

        private Parameter(String annotation, SpotifyWebApiEndpoint.Parameter parameter, int order) {
            // Do not map type to primitive type, so we can pass null to it if it is optional
            this(annotation, JavaUtils.mapToJavaType(parameter.getType()), parameter.getName(), parameter.getDescription(), parameter.isRequired(), order);
        }

        public String asMethodArgument() {
            return annotation + " " + type + " " + fieldName;
        }

        public String asMethodArgumentWithoutAnnotation() {
            return type + " " + fieldName;
        }
        public String asJavaDoc() {
            return "@param " + fieldName + " " + description;
        }
    }
}
