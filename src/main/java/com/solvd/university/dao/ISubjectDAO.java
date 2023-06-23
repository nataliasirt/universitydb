package com.solvd.university.dao;

import com.solvd.university.models.Student;

import java.util.List;

public interface ISubjectDAO extends DAO<Student>{
    Student select(int id);
    List<Student> selectAll();
    void insert(Student student);
    void update(Student student,int id);
    void delete(Student student);
}
