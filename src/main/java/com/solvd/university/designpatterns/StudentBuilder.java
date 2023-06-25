package com.solvd.university.designpatterns;
import com.solvd.university.models.Student;


public class StudentBuilder {
    private int userId;
    private String name;
    private String surname;
    private int personalId;
    private String email;
    private int studentId;
    private int enrollment;

    public StudentBuilder () {}

    public StudentBuilder(int userId, String name, String surname, int personalId, String email, int studentId, int enrollment) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.personalId = personalId;
        this.email = email;
        this.studentId = studentId;
        this.enrollment = enrollment;
    }

    public StudentBuilder setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public StudentBuilder setPersonalId(int personalId) {
        this.personalId = personalId;
        return this;
    }

    public StudentBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder setStudentId(int studentId) {
        this.studentId = studentId;
        return this;
    }

    public StudentBuilder setEnrollment(int enrollment) {
        this.enrollment = enrollment;
        return this;
    }

    public Student build() {
        return new Student(userId, name, surname, personalId, email, studentId, enrollment);
    }
}
