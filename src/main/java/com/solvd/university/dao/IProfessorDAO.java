package com.solvd.university.dao;

import com.solvd.university.models.Professor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IProfessorDAO extends DAO<Professor> {
    Professor select(int id);
    List<Professor> selectAll();
    void insert(@Param("professor") Professor professor);
    void update(@Param("professor") Professor professor, @Param("id") int id);
    void delete(@Param("professor") Professor professor);
}
