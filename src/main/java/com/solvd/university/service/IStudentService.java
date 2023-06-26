package com.solvd.university.service;

import com.solvd.university.models.Student;

import java.util.List;

public interface IStudentService {

    Student getStudentById(int id);

    List<Student> getAllStudents();

    List<Student> getStudentsAlphabetically();
    void registerStudent(Student student);
    void updateStudentById(Student student, int id);
}
