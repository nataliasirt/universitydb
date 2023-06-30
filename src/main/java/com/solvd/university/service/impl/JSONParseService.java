package com.solvd.university.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.university.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JSONParseService {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final ObjectMapper mapper = JSONMapper.getObjectMapper();

    public JSONParseService(){

    }

    private static final Logger LOGGER = LogManager.getLogger(JSONParseService.class);
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static void serializeObject(Object object, File file) {
        try {
            objectMapper.writeValue(file, object);
            System.out.println(objectMapper.writeValueAsString(object));
        } catch (IOException e) {
            LOGGER.warn("Error writing the value, IOException" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static <T> T deserializeToObject(Class<T> object, File file) {
        try {
            return objectMapper.readValue(file, object);
        } catch (IOException e) {
            LOGGER.warn("Error processing JSON" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}


