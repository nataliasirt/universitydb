package com.solvd.university.service.impl.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMapper {
    private static ObjectMapper objectMapper;

    private JSONMapper() {
    }

    public static synchronized ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = createObjectMapper();
        }
        return objectMapper;
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }
}


