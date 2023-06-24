package com.solvd.university.models;

public class Exam {
    private int examId;
    private double mark;
    private Subject subject;
    private int student_id;

    public Exam() {};

    public Exam(int examId, double mark, Subject subject, int student_id) {
        this.examId = examId;
        this.mark = mark;
        this.student_id = student_id;
    }

    public int getExamId() {
        return examId;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }


    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", mark=" + mark +
                '}';
    }

}
