package com.solvd.university.dao;

import com.solvd.university.dao.DAO;
import com.solvd.university.models.Professor;

import java.util.List;

public interface IProfessorDAO extends DAO<Professor> {
    Professor select(int id);
    List<Professor> selectAll();



}
