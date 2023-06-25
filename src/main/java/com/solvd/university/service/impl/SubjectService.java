package com.solvd.university.service.impl;

import com.solvd.university.impl.SubjectDAO;
import com.solvd.university.models.Subject;

import java.util.List;

public class SubjectService {
    private final SubjectDAO subjectDAO = new SubjectDAO();

    public Subject getSubjectById(int id) {
        return this.subjectDAO.select(id);
    }

    public List<Subject> getAllSubjects() {
        return this.subjectDAO.selectAll();
    }

    public void registerSubject(Subject subject) {
        this.subjectDAO.insert(subject);
    }

    public void updateSubject(Subject subject, int id) {
        this.subjectDAO.update(subject, id);
    }

    public void deleteSubject(Subject subject) {
        this.subjectDAO.delete(subject);
    }
}
