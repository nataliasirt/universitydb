package com.solvd.university.service;

import com.solvd.university.models.Subject;

import java.util.List;

public interface ISubjectService {

    Subject getSubjectById(int id);
    List<Subject> getAllSubjects();
    void update (Subject subject, int id);
    void delete (Subject subject, int id);
    void registerSubject(Subject subject, int id);
}

