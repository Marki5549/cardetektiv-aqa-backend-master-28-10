package de.cardetecktive.app.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;

public final class JsonParser {
    @Getter
    private static final ObjectMapper mapper;

    static {
        final ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        mapper = objectMapper;
    }

    private JsonParser() {
    }

    public static <T> String objectToJson(final T object) {
        try {
            return mapper.writer().writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException("An error occurred while parsing an Object to JSON", ex);
        }
    }

    public static <T> T jsonToObject(final String jsonString, final Class<T> clazz) {
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException ex) {
            throw new IllegalStateException("An error occurred while parsing JSON", ex);
        }
    }

    public static JsonNode jsonToObject(final String jsonString) {
        try {
            return mapper.readTree(jsonString);
        } catch (IOException ex) {
            throw new IllegalStateException("An error occurred while parsing JSON", ex);
        }
    }

    public static boolean isValidJson(final String json) {
        boolean valid = true;
        try{
            mapper.readTree(json);
        } catch(JsonProcessingException e){
            valid = false;
        }
        return valid;
    }

}
