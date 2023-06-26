package com.solvd.university.service.impl;

import com.solvd.university.models.Professor;

import java.util.List;

public interface IProfessorService{

    Professor getProfessorById();

    List<Professor>getAllProfessorsAlphabetically();


}
