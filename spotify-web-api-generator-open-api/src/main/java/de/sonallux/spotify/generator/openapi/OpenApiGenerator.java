package de.sonallux.spotify.generator.openapi;

import com.google.common.base.Strings;
import de.sonallux.spotify.core.SpotifyWebApiUtils;
import de.sonallux.spotify.core.model.*;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Slf4j
public class OpenApiGenerator {

    private static final String MEDIA_TYPE_JSON = "application/json";
    private static final String SPOTIFY_SECURITY_SCHEME = "oauth_2_0";

    //TODO: possible response status codes: https://developer.spotify.com/documentation/web-api/#response-status-codes

    private OpenAPI openAPI;
    private final CloneHelper cloneHelper;

    public OpenApiGenerator() {
        this.cloneHelper = new CloneHelper();
    }

    public OpenAPI generate(SpotifyWebApi apiDocumentation) {
        this.openAPI = new OpenAPI();
        this.openAPI
                .openapi("3.0.3")
                .info(new Info()
                        .title("Spotify Web API")
                        .version(VersionProvider.getVersion())
                        .contact(new Contact()
                            .name("sonallux")
                            .url("https://github.com/sonallux/spotify-web-api")
                        )
                )
                .servers(List.of(new Server().url(apiDocumentation.getEndpointUrl())))
                .components(new Components()
                        .schemas(generateSchemaObjects(apiDocumentation.getObjectList()))
                        .addResponses("ErrorResponse", getDefaultErrorResponse())
                        .securitySchemes(Map.of(SPOTIFY_SECURITY_SCHEME, getSpotifySecurityScheme(apiDocumentation.getScopes())))
                )
                .tags(generateTags(apiDocumentation.getCategoryList()))
                .paths(generatePaths(apiDocumentation.getCategoryList()))
        ;
        return openAPI;
    }

    private SecurityScheme getSpotifySecurityScheme(SpotifyAuthorizationScopes scopes) {
        var openApiScopes = new Scopes();
        openApiScopes.putAll(scopes.getScopeList().stream().collect(Collectors
                .toMap(SpotifyScope::getId, SpotifyScope::getDescription)));

        return new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .flows(new OAuthFlows().authorizationCode(new OAuthFlow()
                        .authorizationUrl("https://accounts.spotify.com/authorize")
                        .tokenUrl("https://accounts.spotify.com/api/token")
                        .scopes(openApiScopes)
                ))
        ;
    }

    private Paths generatePaths(Collection<SpotifyWebApiCategory> categories) {
        var paths = new Paths();
        for (var category : categories) {
            for (var endpoint : category.getEndpointList()) {
                var path = paths.computeIfAbsent(endpoint.getPath(), s -> new PathItem());
                var operation = generateOperation(endpoint);
                operation.addTagsItem(category.getId());
                switch (endpoint.getHttpMethod()) {
                    case "GET":
                        path.setGet(operation);
                        break;
                    case "PUT":
                        path.setPut(operation);
                        break;
                    case "POST":
                        path.setPost(operation);
                        break;
                    case "DELETE":
                        path.setDelete(operation);
                        break;
                    default:
                        log.warn("Unknown http method at endpoint " + endpoint.getId() + ": " + endpoint.getHttpMethod());
                }
            }
        }
        return paths;
    }

    private Operation generateOperation(SpotifyWebApiEndpoint endpoint) {
        var parameters = endpoint.getParameters().stream()
                .map(this::generateParameter)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        var requestBody = generateRequestBody(endpoint);

        var apiResponses = new ApiResponses()._default(new ApiResponse().$ref("#/components/responses/ErrorResponse"));

        for (var responseType : endpoint.getResponseTypes()) {
            var response = getApiResponse(responseType);
            if (response != null) {
                response.description(endpoint.getResponseDescription());
                apiResponses.put(String.valueOf(responseType.getStatus()), response);
            }
        }

        return new Operation()
                .operationId(endpoint.getId().replace("endpoint-", ""))
                .summary(endpoint.getName())
                .description(endpoint.getDescription())
                .parameters(parameters)
                .requestBody(requestBody)
                .responses(apiResponses)
                .security(List.of(new SecurityRequirement().addList(SPOTIFY_SECURITY_SCHEME, endpoint.getScopes())))
        ;
    }

    private ApiResponse getApiResponse(SpotifyWebApiEndpoint.ResponseType responseType) {
        if ("Void".equals(responseType.getType())) {
            return new ApiResponse();
        }

        var responseSchema = getSchema(responseType.getType(), openAPI.getComponents().getSchemas());
        if (responseSchema == null) {
            return null;
        }
        var content = new Content().addMediaType(MEDIA_TYPE_JSON, new MediaType().schema(responseSchema));
        return new ApiResponse().content(content);
    }

    private io.swagger.v3.oas.models.parameters.Parameter generateParameter(SpotifyWebApiEndpoint.Parameter param) {
        var parameter = new io.swagger.v3.oas.models.parameters.Parameter()
                .name(param.getName())
                .required(param.isRequired())
                .schema(getSchema(param.getType(), Map.of())
                    .description(param.getDescription())
                );
        switch (param.getLocation()) {
            case HEADER:
                parameter.in("header");
                break;
            case PATH:
                parameter.in("path");
                break;
            case QUERY:
                parameter.in("query");
                break;
            case BODY:
                //Body parameter can not be mapped to io.swagger.v3.oas.models.parameters.Parameter
                //and are handled separately.
                return null;
            default:
                log.warn("Parameter " + param.getName() + " has unknown location: " + param.getLocation());
                return null;
        }
        return parameter;
    }

    public RequestBody generateRequestBody(SpotifyWebApiEndpoint endpoint) {
        var requestBody = endpoint.getRequestBody();
        if (requestBody == null) {
            return null;
        }

        if (requestBody instanceof SpotifyWebApiEndpoint.JsonRequestBody) {
            return generateJsonRequestBody((SpotifyWebApiEndpoint.JsonRequestBody) requestBody);
        } else if (requestBody instanceof SpotifyWebApiEndpoint.Base64ImageRequestBody) {
            return generateBase64ImageRequestBody((SpotifyWebApiEndpoint.Base64ImageRequestBody) requestBody);
        } else {
            log.warn("Unsupported RequestBody type: " + requestBody.getClass().getName());
            return null;
        }
    }

    private RequestBody generateJsonRequestBody(SpotifyWebApiEndpoint.JsonRequestBody requestBody) {
        var requiredProps = new ArrayList<String>();
        var props = new LinkedHashMap<String, Schema>();
        for (var param : requestBody.getParameters()) {
            var paramSchema = getSchema(param.getType(), Map.of());
            if (paramSchema == null) {
                continue;
            }
            paramSchema.description(param.getDescription());
            props.put(param.getName(), paramSchema);
            if (param.isRequired()) {
                requiredProps.add(param.getName());
            }
        }
        var schema = new ObjectSchema().properties(props);
        if (requiredProps.size() != 0) {
            schema.required(requiredProps);
        }
        return new RequestBody()
            .description(Strings.isNullOrEmpty(requestBody.getDescription()) ? null : requestBody.getDescription())
            .content(new Content().addMediaType(requestBody.getContentType(), new MediaType().schema(schema)))
            .required(requiredProps.size() != 0);
    }

    private RequestBody generateBase64ImageRequestBody(SpotifyWebApiEndpoint.Base64ImageRequestBody requestBody) {
        return new RequestBody()
            .description(requestBody.getDescription())
            .content(new Content().addMediaType(requestBody.getContentType(), new MediaType()
                .schema(new StringSchema()
                    // in OAS 3.1 it should be contentEncoding("base64") see also https://github.com/OAI/OpenAPI-Specification/pull/2200
                    .format("base64"))))
            .required(true);
    }

    public ApiResponse getDefaultErrorResponse() {
        return new ApiResponse()
                .description("Unexpected error")
                .content(new Content()
                        .addMediaType(MEDIA_TYPE_JSON, new MediaType()
                                .schema(new Schema().$ref("#/components/schemas/ErrorResponseObject"))));
    }

    public List<Tag> generateTags(Collection<SpotifyWebApiCategory> categories) {
        return categories.stream()
                .map(c -> new Tag()
                        .name(c.getId())
                        .description(c.getName())
                )
                .sorted(Comparator.comparing(Tag::getName))
                .collect(Collectors.toList());
    }

    private Map<String, Schema> generateSchemaObjects(Collection<SpotifyWebApiObject> objects) {
        var schemas = new LinkedHashMap<String, Schema>();

        SpotifyWebApiObject pagingObject = null;
        SpotifyWebApiObject cursorPagingObject = null;

        //First pass just adds empty objects, so we can add references
        for (var object : objects) {
            if ("PagingObject".equals(object.getName())) {
                pagingObject = object;
            } else if ("CursorPagingObject".equals(object.getName())) {
                cursorPagingObject = object;
            }
            var objectSchema = new ObjectSchema();
            schemas.put(object.getName(), objectSchema);
        }

        if (pagingObject == null || cursorPagingObject == null) {
            log.warn("Can not find PagingObject or CursorPagingObject");
        } else {
            //Generate properties for PagingObject and CursorPagingObject first, as they are needed to generate the
            //generic types (e.g. PagingCursor[TrackObject])
            schemas.get(pagingObject.getName()).properties(generateObjectProperties(pagingObject, schemas));
            schemas.get(cursorPagingObject.getName()).properties(generateObjectProperties(cursorPagingObject, schemas));
        }

        for (var object : objects) {
            schemas.get(object.getName()).properties(generateObjectProperties(object, schemas));
        }
        return schemas;
    }

    private Map<String, Schema> generateObjectProperties(SpotifyWebApiObject object, Map<String, Schema> customSchemas) {
        var properties = new LinkedHashMap<String, Schema>();
        for (var prop : object.getProperties()) {
            var schema = getSchema(prop.getType(), customSchemas);
            if (schema == null) {
                continue;
            }
            if (schema.get$ref() != null) {
                var newSchema = new ComposedSchema()
                    .allOf(List.of(schema))
                    .description(prop.getDescription());
                properties.put(prop.getName(), newSchema);
            } else {
                schema.description(prop.getDescription());
                properties.put(prop.getName(), schema);
            }
        }
        return properties;
    }

    /**
     * Maps the given type to a {@link Schema} object. Other objects that can be referenced by the given type
     * (e.g. <code>Array[OtherObject]</code>) can be specified via <code>customSchemas</code>. The type
     * <code>Void</code> is mapped to <code>null</code>.
     *
     * @param type the type to map
     * @param customSchemas schemas that can be referenced
     * @return the schema
     */
    private Schema getSchema(String type, Map<String, Schema> customSchemas) {
        Matcher matcher;
        if ("String".equals(type)) {
            return new StringSchema();
        } else if ("Integer".equals(type)) {
            return new IntegerSchema();
        } else if ("Float".equals(type) || "Number".equals(type)) {
            return new NumberSchema();
        } else if ("Boolean".equals(type)) {
            return new BooleanSchema();
        } else if ("Timestamp".equals(type)) {
            //TODO: Check if DateTimeSchema is ok
            return new DateTimeSchema();
        } else if ("Object".equals(type)) {
            return new ObjectSchema();
        } else if ("Void".equals(type)) {
            return null;
        } else if (customSchemas.containsKey(type)) {
            return new Schema().$ref("#/components/schemas/" + type);
        } else if ((matcher = SpotifyWebApiUtils.ARRAY_TYPE_PATTERN.matcher(type)).matches()) {
            var arrayItemSchema = getSchema(matcher.group(1), customSchemas);
            return new ArraySchema()
                    .items(arrayItemSchema);
        } else if ((matcher = SpotifyWebApiUtils.PAGING_OBJECT_TYPE_PATTERN.matcher(type)).matches()) {
            var arrayItemSchema = getSchema(matcher.group(1), customSchemas);
            var pagingObjectSchema = cloneHelper.cloneSchema(customSchemas.get("PagingObject"));
            var pagingItemsSchema = (ArraySchema)pagingObjectSchema.getProperties().get("items");
            pagingItemsSchema.items(arrayItemSchema);
            return pagingObjectSchema;
        } else if ((matcher = SpotifyWebApiUtils.CURSOR_PAGING_OBJECT_TYPE_PATTERN.matcher(type)).matches()) {
            var arrayItemSchema = getSchema(matcher.group(1), customSchemas);
            var pagingObjectSchema = cloneHelper.cloneSchema(customSchemas.get("CursorPagingObject"));
            var pagingItemsSchema = (ArraySchema)pagingObjectSchema.getProperties().get("items");
            pagingItemsSchema.items(arrayItemSchema);
            return pagingObjectSchema;
        } else if (type.contains(" | ")) {
            var types = Arrays.stream(type.split(" \\| "))
                    .map(t -> getSchema(t, customSchemas))
                    .collect(Collectors.toList());
            return new ComposedSchema().oneOf(types);
        } else {
            throw new RuntimeException("Missing type: " + type);
        }
    }
}
