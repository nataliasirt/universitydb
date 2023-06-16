package com.solvd.university.dao;

import com.solvd.university.models.Exam;

import java.util.List;

public interface IExamDAO extends DAO<Exam>{
    @Override
    Exam get(int id);

    @Override
    List<Exam> getAll();

    @Override
    void insert(Exam exam);

    @Override
    void update(Exam exam, int id);

    @Override
    void delete(Exam exam);
}
