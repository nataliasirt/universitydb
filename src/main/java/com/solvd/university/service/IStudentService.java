package com.solvd.university.service;

import com.solvd.university.models.Student;

import java.util.List;

public interface IStudentService {

    Student getStudentById(int id);
    List<Student> getAllStudents();
    void insert (Student student, int id);
    void update(Student student, int id);
    void delete (Student student, int id);
}
