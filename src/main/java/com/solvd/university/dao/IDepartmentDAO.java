package com.solvd.university.dao;

import com.solvd.university.models.Department;
import com.solvd.university.models.Professor;

import java.util.List;

public interface IDepartmentDAO extends DAO<Department> {

    Department select(int id);
    List<Department> selectAll();
    List<Professor> selectDepartmentProfessors(Department department);
    void insert( Department department);
    void update(Department department, int id);
    void delete(Department department);
}
