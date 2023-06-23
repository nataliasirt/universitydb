package com.solvd.university.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Subject {
    @JsonProperty
    private int subjectId;
    @JsonProperty
    private String name;

    public Subject() {}

    public Subject(int subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", name='" + name + '\'' +
                '}';
    }
}
