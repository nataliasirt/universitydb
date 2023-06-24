package com.solvd.university.service.impl;

import com.solvd.university.impl.ProfessorDAO;
import com.solvd.university.models.Professor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProfessorService {
    private final static Logger LOGGER = LogManager.getLogger(ProfessorService.class);
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    public Professor getProfessorById(int id) {
        return this.professorDAO.select(id);
    }

    public List<Professor> getAllProfessors() {
        return this.professorDAO.selectAll();
    }

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



