package com.solvd.university.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.university.models.Group;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JacksonGroup {
    private static final Logger LOGGER = LogManager.getLogger(JacksonGroup.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void writeGroup(Group group) {
        File file = new File("src/main/resources/jackson/jsonoutput.json");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            objectMapper.writeValue(file, group);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }

    public static Group readGroup() {
        File file = new File("src/main/resources/jackson/groupdata.json");
        Group group;

        try {
            group = objectMapper.readValue(file, Group.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info(group);
        return group;
    }

}


