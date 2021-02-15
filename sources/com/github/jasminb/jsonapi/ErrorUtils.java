package com.github.jasminb.jsonapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.models.errors.Errors;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.ResponseBody;

public class ErrorUtils {
    private ErrorUtils() {
    }

    public static <T extends Errors> T parseErrorResponse(ObjectMapper objectMapper, ResponseBody responseBody, Class<T> cls) throws IOException {
        return (Errors) objectMapper.readValue(responseBody.bytes(), cls);
    }

    public static <T extends Errors> T parseError(ObjectMapper objectMapper, JsonNode jsonNode, Class<T> cls) throws JsonProcessingException {
        return (Errors) objectMapper.treeToValue(jsonNode, cls);
    }

    public static <T extends Errors> T parseError(ObjectMapper objectMapper, InputStream inputStream, Class<T> cls) throws IOException {
        return (Errors) objectMapper.readValue(inputStream, cls);
    }
}
