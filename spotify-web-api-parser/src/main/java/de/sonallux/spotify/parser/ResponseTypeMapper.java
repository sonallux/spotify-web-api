package de.sonallux.spotify.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sonallux.spotify.core.Yaml;
import de.sonallux.spotify.core.model.SpotifyWebApiCategory;
import de.sonallux.spotify.core.model.SpotifyWebApiEndpoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
public class ResponseTypeMapper {

    private final Path responseTypesFile;
    private final Map<String, Map<String, EndpointResponse>> responseTypes;
    private final MessageDigest md5Digest;
    private final ObjectMapper objectMapper;

    public ResponseTypeMapper(Path responseTypesFile) throws IOException, NoSuchAlgorithmException {
        this.responseTypesFile = responseTypesFile;
        this.objectMapper = Yaml.create();
        this.md5Digest = MessageDigest.getInstance("MD5");
        this.responseTypes = load();
    }

    private Map<String, Map<String, EndpointResponse>> load() throws IOException {
        try (var inputStream = Files.newInputStream(this.responseTypesFile)) {
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        }
    }

    public List<SpotifyWebApiEndpoint.ResponseType> getEndpointResponseTypes(String categoryId, SpotifyWebApiEndpoint endpoint) {
        var endpointResponse = getEndpointResponse(categoryId, endpoint.getId());
        if (endpointResponse == null) {
            return null;
        }
        var currentHash = calculateMD5Hash(endpoint);
        if (!currentHash.equals(endpointResponse.getMd5Hash())) {
            log.warn("Response description for endpoint {} has changed", endpoint.getId());
            endpointResponse.setMd5Hash(currentHash);
        }
        return endpointResponse.getResponseTypes();
    }

    public void save() throws IOException {
        try (var outputStream = Files.newOutputStream(this.responseTypesFile)) {
            objectMapper.writeValue(outputStream, responseTypes);
        }
    }

    public void update(List<SpotifyWebApiCategory> categories) {
        var scanner = new Scanner(System.in);
        for (var category : categories) {
            for (var endpoint : category.getEndpointList()) {
                var currentHash = calculateMD5Hash(endpoint);
                var endpointResponse = getEndpointResponse(category.getId(), endpoint.getId());
                if (endpointResponse != null && endpointResponse.getResponseTypes().size() != 0 && currentHash.equals(endpointResponse.getMd5Hash())) {
                    //No changes
                    continue;
                } else if (endpointResponse == null) {
                    endpointResponse = new EndpointResponse();
                    setEndpointResponse(category.getId(), endpoint.getId(), endpointResponse);
                }
                endpointResponse.setMd5Hash(currentHash);

                System.out.printf("\nMissing response in %s for %s %s: \n%s\n", category.getId(),
                        endpoint.getHttpMethod(), endpoint.getId(), endpoint.getResponseDescription());

                var i = 0;
                do {
                    if (endpointResponse.getResponseTypes().size() > i) {
                        var responseType = endpointResponse.getResponseTypes().get(i);
                        System.out.printf("Response type (current: %s): ", responseType.getType());
                        responseType.setType(readString(scanner, responseType.getType()));
                        System.out.printf("Response status (current: %s): ", responseType.getStatus());
                        responseType.setStatus(readInt(scanner, responseType.getStatus()));
                    } else {
                        System.out.print("Response type: ");
                        String newType = readString(scanner, null);
                        if (newType == null) {
                            break;
                        }
                        System.out.print("Response status (default: 200): ");
                        int newStatus = readInt(scanner, 200);
                        endpointResponse.getResponseTypes().add(new SpotifyWebApiEndpoint.ResponseType(newType, newStatus, null));
                    }
                    i++;
                } while (true);
            }
        }
    }

    private static String readString(Scanner scanner, String defaultValue) {
        var string = scanner.nextLine().trim();
        if (string.isEmpty()) {
            return defaultValue;
        } else {
            return string;
        }
    }

    private static int readInt(Scanner scanner, int defaultValue) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private EndpointResponse getEndpointResponse(String categoryId, String endpointId) {
        var endpointTypes = responseTypes.get(categoryId);
        if (endpointTypes == null) {
            return null;
        }
        return endpointTypes.get(endpointId);
    }

    private void setEndpointResponse(String categoryId, String endpointId, EndpointResponse endpointResponse) {
        var endpointTypes = responseTypes.get(categoryId);
        if (endpointTypes != null) {
            endpointTypes.put(endpointId, endpointResponse);
        } else {
            Map<String, EndpointResponse> map = new HashMap<>();
            map.put(endpointId, endpointResponse);
            responseTypes.put(categoryId, map);
        }
    }

    private String calculateMD5Hash(SpotifyWebApiEndpoint endpoint) {
        md5Digest.reset();
        md5Digest.update(endpoint.getResponseDescription().getBytes(StandardCharsets.UTF_8));
        var bytes = md5Digest.digest();
        var bigInt = new BigInteger(1, bytes);
        return bigInt.toString(16);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EndpointResponse {
        private String md5Hash;
        private List<SpotifyWebApiEndpoint.ResponseType> responseTypes = new ArrayList<>();
    }
}
