package com.solvd.university.dao;

import com.solvd.university.models.Employee;

import java.util.List;

public interface IEmployeeDAO extends DAO<Employee> {
    Employee select(int id);
    List<Employee> selectAll();
    void insert( Employee employee);
    void update(Employee employee, int id);
    void delete(Employee employee);

}
