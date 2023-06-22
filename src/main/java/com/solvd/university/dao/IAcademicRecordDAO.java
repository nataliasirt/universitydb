package com.solvd.university.dao;
import com.solvd.university.models.AcademicRecord;
import java.util.List;

public interface IAcademicRecordDAO extends DAO<AcademicRecord> {
    AcademicRecord select(int id);
    List<AcademicRecord> selectAll();
    void insert(AcademicRecord academicRecord);
    void update(AcademicRecord academicRecord, int id);
    void delete(AcademicRecord academicRecord);
}


