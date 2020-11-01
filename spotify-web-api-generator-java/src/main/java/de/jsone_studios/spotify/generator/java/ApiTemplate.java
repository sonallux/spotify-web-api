package de.jsone_studios.spotify.generator.java;

import com.google.common.base.CaseFormat;
import de.jsone_studios.spotify.core.model.SpotifyApiCategory;
import de.jsone_studios.spotify.core.model.SpotifyApiEndpoint;
import de.jsone_studios.spotify.generator.java.util.JavaPackage;
import de.jsone_studios.spotify.generator.java.util.JavaUtils;
import de.jsone_studios.spotify.generator.java.util.Markdown2Html;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.jsone_studios.spotify.core.model.SpotifyApiEndpoint.ParameterLocation.*;

class ApiTemplate extends AbstractTemplate<SpotifyApiCategory> {

    @Override
    String templateName() {
        return "api";
    }

    @Override
    JavaPackage getJavaPackage(SpotifyApiCategory category, JavaPackage basePackage) {
        return basePackage.child("apis");
    }

    @Override
    String getFileName(SpotifyApiCategory category) {
        return JavaUtils.getFileName(getClassName(category));
    }

    @Override
    Map<String, Object> buildContext(SpotifyApiCategory category, Map<String, Object> context) {
        context.put("modelsPackage", getBasePackage().child("models").getName());
        context.put("name", category.getName());
        context.put("className", getClassName(category));
        context.put("documentationLink", category.getLink());
        context.put("endpoints", category.getEndpointList().stream().flatMap(e -> buildEndpointContext(e).stream()).collect(Collectors.toList()));
        return context;
    }

    private List<Map<String, Object>> buildEndpointContext(SpotifyApiEndpoint endpoint) {
        var baseContext = new HashMap<String, Object>();
        var methodName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, JavaUtils.shrinkEndpointId(endpoint));
        baseContext.put("methodName", methodName);
        baseContext.put("name", endpoint.getName());
        baseContext.put("description", Markdown2Html.convertToSingleLine(endpoint.getDescription()));
        baseContext.put("responseDescription", Markdown2Html.convertToSingleLine(endpoint.getResponseDescription()));
        if (endpoint.getNotes() != null) {
            baseContext.put("notes", Markdown2Html.convertToSingleLine(endpoint.getNotes()));
        }
        if (endpoint.getScopes().size() > 0) {
            baseContext.put("scopes", String.join(", ", endpoint.getScopes()));
        }
        baseContext.put("httpMethod", endpoint.getHttpMethod());
        baseContext.put("path", endpoint.getPath());
        baseContext.put("responseType", getResponseType(endpoint));
        baseContext.put("documentationLink", endpoint.getLink());

        List<Map<String, Object>> contexts = new ArrayList<>();
        var arguments = getArguments(endpoint);
        for (var args : arguments) {
            var context = new HashMap<>(baseContext);
            context.put("arguments", args.stream().map(Argument::asMethodArgument).collect(Collectors.joining(", ")));
            context.put("javaDocParams", args.stream().map(Argument::asJavaDoc).collect(Collectors.toList()));
            contexts.add(context);
        }
        return contexts;
    }

    private List<List<Argument>> getArguments(SpotifyApiEndpoint endpoint) {
        fixDuplicateEndpointArguments(endpoint);

        List<Argument> requiredArgs = new ArrayList<>();
        endpoint.getParameters().stream()
                .filter(p -> p.getLocation() == PATH)
                .map(p -> new Argument("@Path(\"" + p.getName() + "\")", JavaUtils.mapToJavaType(p.getType()), p.getName(), p.getDescription()))
                .forEach(requiredArgs::add);

        endpoint.getParameters().stream()
                .filter(p -> p.getLocation() == QUERY && p.isRequired())
                .map(p -> new Argument("@Query(\"" + p.getName() + "\")", JavaUtils.mapToJavaType(p.getType()), p.getName(), p.getDescription()))
                .forEach(requiredArgs::add);

        boolean requestBodyArgAdded = false;
        if (endpoint.getParameters().stream().anyMatch(p -> p.getLocation() == BODY && p.isRequired())) {
            requestBodyArgAdded = true;
            requiredArgs.add(new Argument(
                    "@Body", EndpointRequestBodyHelper.getEndpointRequestBodyName(endpoint), "requestBody", "the request body"));
        }

        List<List<Argument>> args = new ArrayList<>();
        args.add(new ArrayList<>(requiredArgs));

        var optionalQueryArgs = endpoint.getParameters().stream().filter(p -> p.getLocation() == QUERY && !p.isRequired()).collect(Collectors.toList());
        if (optionalQueryArgs.size() == 1) {
            var p = optionalQueryArgs.get(0);
            requiredArgs.add(new Argument(
                    "@Query(\"" + p.getName() + "\")", JavaUtils.mapToJavaType(p.getType()), p.getName(), p.getDescription()));
            args.add(requiredArgs);
        } else if (optionalQueryArgs.size() > 1) {
            requiredArgs.add(new Argument(
                    "@QueryMap",  "java.util.Map<String, Object>",  "queryParameters", "A map of optional query parameters"));
            args.add(requiredArgs);
        }

        if (!requestBodyArgAdded && endpoint.getParameters().stream().anyMatch(p -> p.getLocation() == BODY)) {
            List<List<Argument>> endpointArguments = new ArrayList<>();
            for (var arguments : args) {
                var duplicateArguments = new ArrayList<>(arguments);
                duplicateArguments.add(new Argument(
                        "@Body", EndpointRequestBodyHelper.getEndpointRequestBodyName(endpoint), "requestBody", "The request body"));
                endpointArguments.add(arguments);
                endpointArguments.add(duplicateArguments);
            }
            return endpointArguments;
        }

        return args;
    }

    private String getResponseType(SpotifyApiEndpoint endpoint) {
        if (endpoint.getResponseTypes().size() == 1
                || 1 == endpoint.getResponseTypes().stream()
                .map(SpotifyApiEndpoint.ResponseType::getType).distinct().count()) {
            return JavaUtils.mapToJavaType(endpoint.getResponseTypes().get(0).getType());
        }
        var nonVoidResponseTypes = endpoint.getResponseTypes().stream()
                .map(SpotifyApiEndpoint.ResponseType::getType).filter(t -> !"Void".equals(t)).distinct().collect(Collectors.toList());
        if (nonVoidResponseTypes.size() == 1) {
            return JavaUtils.mapToJavaType(endpoint.getResponseTypes().get(0).getType());
        }
        return "";
    }

    private String getClassName(SpotifyApiCategory category) {
        return category.getName().replace(" ", "").replace("API", "Api");
    }

    private void fixDuplicateEndpointArguments(SpotifyApiEndpoint endpoint) {
        String paramName;
        switch (endpoint.getId()) {
            case "endpoint-remove-albums-user":
            case "endpoint-save-albums-user":
            case "endpoint-follow-artists-users":
            case "endpoint-unfollow-artists-users":
                paramName = "ids";
                break;
            case "endpoint-replace-playlists-tracks":
            case  "endpoint-add-tracks-to-playlist":
                paramName = "uris";
                break;
            default:
                return;
        }
        endpoint.getParameters().removeIf(p -> p.getLocation() == QUERY && paramName.equals(p.getName()));
        for (var param : endpoint.getParameters()) {
            if (param.getLocation() == BODY && paramName.equals(param.getName())) {
                param.setRequired(true);
            } else if (param.getLocation() == HEADER && "Content-Type".equals(param.getName())) {
                param.setRequired(true);
            }
        }
    }

    @Getter
    @Setter
    private static class Argument {
        private String annotation;
        private String type;
        private String fieldName;
        private String description;

        public Argument(String annotation, String type, String fieldName, String description) {
            this.annotation = annotation;
            this.type = type;
            this.fieldName = JavaUtils.escapeFieldName(fieldName);
            this.description = Markdown2Html.convertToSingleLine(description);
        }

        public String asMethodArgument() {
            return annotation + " " + type + " " + fieldName;
        }

        public String asJavaDoc() {
            return "@param " + fieldName + " " + description;
        }
    }
}
