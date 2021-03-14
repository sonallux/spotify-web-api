package de.sonallux.spotify.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@RequiredArgsConstructor
public class JacksonConverterFactory extends Converter.Factory {
    private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json; charset=UTF-8");

    private final ObjectMapper mapper;

    public JacksonConverterFactory() {
        this(new ObjectMapper());
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        var javaType = mapper.getTypeFactory().constructType(type);
        var reader = mapper.readerFor(javaType);
        return (ResponseBody body) -> {
            try (body) {
                return reader.readValue(body.byteStream());
            }
        };
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        var javaType = mapper.getTypeFactory().constructType(type);
        var writer = mapper.writerFor(javaType);
        return (Object value) -> {
            byte[] bytes = writer.writeValueAsBytes(value);
            return RequestBody.create(JSON_MEDIA_TYPE, bytes);
        };
    }
}
