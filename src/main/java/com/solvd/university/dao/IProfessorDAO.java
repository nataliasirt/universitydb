package com.solvd.university.dao;

import com.solvd.university.dao.DAO;
import com.solvd.university.models.Professor;

import java.util.List;

public interface IProfessorDAO extends DAO<Professor> {
    @Override
    Professor get(int id);

    @Override
    List<Professor> getAll();

    @Override
    void insert(Professor professor);

    @Override
    void update(Professor professor, int id);

    @Override
    void delete(Professor professor);
}
