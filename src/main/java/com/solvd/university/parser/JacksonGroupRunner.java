package com.solvd.university.parser;

import com.solvd.university.models.Group;

public class JacksonGroupRunner {

        public static void main(String[] args) {
            Group group = JacksonGroup.readGroup();
            JacksonGroup.writeGroup(group);
        }
    }


