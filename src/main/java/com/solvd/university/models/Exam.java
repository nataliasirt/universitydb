package com.solvd.university.models;

public class Exam {
    private int examID;
    private String examName;
    private double mark;
    private int studentID;
     public Exam(){}

    public Exam(int examID, String examName, double mark, int studentID) {
        this.examID = examID;
        this.examName = examName;
        this.mark = mark;
        this.studentID = studentID;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examID=" + examID +
                ", examName='" + examName + '\'' +
                ", mark=" + mark +
                ", studentID=" + studentID +
                '}';
    }
}
