package com.solvd.university.dao;

import com.solvd.university.models.Professor;

import java.util.List;

public interface IProfessorDAO extends DAO<Professor> {
    Professor select(int id);
    List<Professor> selectAll();
    void insert( Professor professor);
    void update( Professor professor, int id);
    void delete( Professor professor);
}
