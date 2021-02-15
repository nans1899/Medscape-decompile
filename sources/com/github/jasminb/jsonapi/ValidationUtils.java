package com.github.jasminb.jsonapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.exceptions.InvalidJsonApiResourceException;
import com.github.jasminb.jsonapi.exceptions.ResourceParseException;
import com.github.jasminb.jsonapi.models.errors.Errors;

public class ValidationUtils {
    private ValidationUtils() {
    }

    public static void ensureValidResource(JsonNode jsonNode) {
        if (!jsonNode.has("data") && !jsonNode.has(JSONAPISpecConstants.META)) {
            throw new InvalidJsonApiResourceException();
        }
    }

    public static boolean isRelationshipParsable(JsonNode jsonNode) {
        return jsonNode != null && jsonNode.hasNonNull("id") && jsonNode.hasNonNull("type") && !jsonNode.get("id").isContainerNode() && !jsonNode.get("type").isContainerNode();
    }

    public static void ensureNotError(ObjectMapper objectMapper, JsonNode jsonNode) {
        if (jsonNode != null && jsonNode.hasNonNull(JSONAPISpecConstants.ERRORS)) {
            try {
                throw new ResourceParseException(ErrorUtils.parseError(objectMapper, jsonNode, Errors.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
