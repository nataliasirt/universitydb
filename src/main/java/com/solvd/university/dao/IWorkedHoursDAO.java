package com.solvd.university.dao;

import com.solvd.university.models.WorkedHours;

import java.util.List;

public interface IWorkedHoursDAO extends DAO<WorkedHours> {

    WorkedHours select(int id);

    List<WorkedHours> selectAll();

    void insert(WorkedHours workedHours);
    void update(WorkedHours workedHours, int id);
    void delete(WorkedHours workedHours);
}
