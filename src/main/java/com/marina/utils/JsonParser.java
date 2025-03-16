package com.marina.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marina.model.ApiResponse;

public class JsonParser {
    public static <T> List<T> parseJson(String json, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ApiResponse<T> response = objectMapper.readValue(json, objectMapper.getTypeFactory()
                    .constructParametricType(ApiResponse.class, clazz));
            return response.getItems();
        } catch (IOException e) {
            throw new RuntimeException("Error parsing JSON: " + e.getMessage());
        }
    }
}
