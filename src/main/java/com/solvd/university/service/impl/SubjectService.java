package com.solvd.university.service.impl;

import com.solvd.university.dao.ISubjectDAO;
import com.solvd.university.dao.impl.SubjectDAO;
import com.solvd.university.models.Subject;
import com.solvd.university.service.ISubjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SubjectService implements ISubjectService {
    private final static Logger LOGGER = LogManager.getLogger(SubjectService.class);
    private final ISubjectDAO subjectDAO = new SubjectDAO();

    @Override
    public Subject getSubjectById(int id){
        if (id > 0) {
        return subjectDAO.select(id);
    } else LOGGER.warn("Invalid ID! ");
        return null;
}
    @Override
    public List<Subject> getAllSubjects() {
        return this.subjectDAO.selectAll();
    }
    @Override
    public void update(Subject subject, int id) {
        this.subjectDAO.update(subject, id);
    }
    @Override
    public void delete(Subject subject, int id) {
        this.subjectDAO.delete(subject);
    }
    @Override
    public void registerSubject(Subject subject, int id){ this.subjectDAO.select(id);}
}
