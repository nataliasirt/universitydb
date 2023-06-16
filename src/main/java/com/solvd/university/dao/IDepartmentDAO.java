package com.solvd.university.dao;

import com.solvd.university.models.Department;

import java.util.List;

public interface IDepartmentDAO extends DAO<Department> {
    Department select(int id);
    List<Department> selectAll();
    void insert();
    void update();
    void delete();


}
