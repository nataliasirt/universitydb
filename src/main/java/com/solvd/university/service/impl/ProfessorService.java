package com.solvd.university.service.impl;


import com.solvd.university.dao.IProfessorDAO;
import com.solvd.university.dao.impl.ProfessorDAO;
import com.solvd.university.models.Professor;
import com.solvd.university.service.IProfessorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProfessorService implements IProfessorService {
    private final static Logger LOGGER = LogManager.getLogger(StudentService.class);
    private final IProfessorDAO professorDAO = new ProfessorDAO();
    @Override
    public Professor getProfessorById(int id) {
        if (id > 0) {
            return professorDAO.select(id);
        } else LOGGER.warn("Invalid ID provided! ");
        return null;
    }
    @Override
    public List<Professor> getAllProfessors() {
        return this.professorDAO.selectAll();
    }
    @Override
    public List<Professor> getAllProfessorsAlphabetically() {
        return this.professorDAO.selectAll().stream()
                .sorted(Comparator.comparing(Professor::getName))
                .collect(Collectors.toList());
    }

    public void registerProfessor(Professor professor) {
        this.professorDAO.insert(professor);
    }

    public void updateProfessorById(Professor professor, int id) {
        this.professorDAO.update(professor, id);
    }

    public void deleteProfessor(Professor professor) {
        this.professorDAO.delete(professor);
    }

}
