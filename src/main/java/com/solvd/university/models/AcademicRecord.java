package com.solvd.university.models;

import javax.security.auth.Subject;
import java.util.Map;

public class AcademicRecord {
    private int recordId;
    private int studentId;

    private Map<Subject, Double> subjectsHistory;

    public AcademicRecord() {

    }

    public AcademicRecord(int recordId, int studentId) {
        this.recordId = recordId;
        this.studentId = studentId;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int id) {
        this.studentId = id;
    }

    public Map<Subject, Double> getSubjectsHistory() {
        return this.subjectsHistory;
    }

    public void setSubjectsHistory(Map<Subject, Double> subjectsHistory) {
        this.subjectsHistory = subjectsHistory;
    }
}
