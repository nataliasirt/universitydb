package com.solvd.university.service.impl;

import com.solvd.university.models.Career;
import com.solvd.university.models.Student;

public interface ICareerService {
void saveCareerToDB(Career career);
void saveStudentToDB(Student student);
}
