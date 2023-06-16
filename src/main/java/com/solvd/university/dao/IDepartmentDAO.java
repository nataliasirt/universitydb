package com.solvd.university.dao;

import com.solvd.university.models.Department;

import java.util.List;

public interface IDepartmentDAO extends DAO<Department> {

    @Override
    Department get(int id);

    @Override
    List<Department> getAll();

    @Override
    void insert(Department department);

    @Override
    void update(Department department, int id);

    @Override
    void delete(Department department);



}
