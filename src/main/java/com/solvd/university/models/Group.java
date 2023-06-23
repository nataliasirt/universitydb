package com.solvd.university.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Group {
    @JsonProperty
    private int groupId;
    @JsonProperty
    private String groupCode;
    @JsonProperty
    private Professor head;
    @JsonProperty
    private Subject subject;
    @JsonProperty
    private List<Student> students;

    public Group() {}

    public Group(int groupId, String groupCode, Professor head, Subject subject) {
        this.groupId = groupId;
        this.groupCode = groupCode;
        this.head = head;
        this.subject = subject;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Professor getHead() {
        return head;
    }

    public void setHead(Professor head) {
        this.head = head;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupCode='" + groupCode + '\'' +
                ", head=" + head +
                ", subject=" + subject +
                ", students=" + students +
                '}';
    }
}
