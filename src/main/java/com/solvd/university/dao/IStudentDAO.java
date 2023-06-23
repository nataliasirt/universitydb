package com.solvd.university.dao;

import com.solvd.university.models.Student;

import java.util.List;

public interface IStudentDAO extends DAO<Student> {
    Student select(int id);

    List<Student> selectAll();

    @Override
    Student get(int id);

    @Override
    List<Student> getAll();

    @Override
    void insert(Student student);

    @Override
    void update(Student student, int id);

    @Override
    void delete(Student student);
}
