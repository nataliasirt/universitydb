package com.solvd.university.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Student")
public class Student extends User {
    private int studentID;
    private String fullName;
    private int enrollment;
    private List<Exam> exams;
    public Student(){}

    public Student(int studentID, String fullName, int enrollment, List<Exam> exams) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.enrollment = enrollment;
        this.exams = exams;
    }

    @Override
    public int getStudentID() {
        return studentID;
    }

    @Override
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}



