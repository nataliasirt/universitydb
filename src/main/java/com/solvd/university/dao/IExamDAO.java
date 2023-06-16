package com.solvd.university.dao;

import com.solvd.university.models.Exam;

import java.util.List;

public interface IExamDAO extends DAO<Exam>{
    Exam select(int id);
    List<Exam> selectAll();
    void insert();
    void update();
    void delete();

}
