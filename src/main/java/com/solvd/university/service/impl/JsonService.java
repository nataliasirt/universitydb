package com.solvd.university.service.impl;

import java.io.File;

public class JsonService {
    public void serializeObjectToJson(Object object, File file) {
        JSONParseService.serializeObject(object, file);
    }

    public <T> T deserializeJsonToObject(Class<T> object, File file) {
        return JSONParseService.deserializeToObject(object, file);
    }
}
