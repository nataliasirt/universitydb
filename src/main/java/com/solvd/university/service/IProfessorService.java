package com.solvd.university.service;

import com.solvd.university.models.Professor;

import java.util.List;

public interface IProfessorService{
    Professor getProfessorById(int id);
    List<Professor>getAllProfessors();
    void update(Professor professor, int id);
    void delete (Professor professor, int id);









}
