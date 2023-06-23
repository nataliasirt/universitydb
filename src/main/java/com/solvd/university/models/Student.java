package com.solvd.university.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Student extends User{
    @JsonProperty
    private int studentId;
    @JsonProperty
    private int enrollment;
    @JsonProperty
    private List<Exam> exams;

    public Student(){}

    public Student(int userId, String name, String surname, int personalId, String email, int studentId, int enrollment) {
        super(userId, name, surname, personalId, email);
        this.studentId = studentId;
        this.enrollment = enrollment;
    }

    public int getStudentId() {
        return this.studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getEnrollment() {
        return this.enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public List<Exam> getExams() {
        return this.exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId + " " +
                super.toString() +
                ", enrollment=" + enrollment +
                ", exams=" + exams +
                '}';
    }

}
