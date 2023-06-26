package com.solvd.university.service.impl;

import com.solvd.university.models.Student;

import java.util.List;

public interface IStudentService {

    Student getStudentById(int id);
    List<Student> getAllStudents();
}
