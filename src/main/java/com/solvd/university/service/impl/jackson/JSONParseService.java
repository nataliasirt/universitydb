package com.solvd.university.service.impl.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.university.Main;
import com.solvd.university.service.impl.jackson.GroupService;
import com.solvd.university.service.impl.jackson.JSONMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JSONParseService{
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final ObjectMapper mapper = JSONMapper.getObjectMapper();

    public JSONParseService(){
    }

    public void serializeToJsonFile(GroupService groupService, String writeFilePath) {
        try {
            mapper.writeValue(new File(writeFilePath), groupService);
            logger.info("Groupservice object serialized and written to file: " + writeFilePath);
        } catch (IOException e) {
            logger.info("Error occurred while serializing customer to JSON file: " + e.getMessage());
        }
    }

    public GroupService deserializeFromJsonFile(String filePath ) {
        try {
            return mapper.readValue(new File(filePath), GroupService.class);
        } catch (IOException e) {
            logger.info("Error occurred while deserializing customer from JSON file: " + e.getMessage());
            return null;
        }
    }
}
