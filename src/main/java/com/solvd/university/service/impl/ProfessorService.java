package com.solvd.university.service.impl;


import com.solvd.university.dao.IProfessorDAO;
import com.solvd.university.dao.impl.ProfessorDAO;
import com.solvd.university.models.Professor;
import com.solvd.university.service.IProfessorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

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
    public void update (Professor professor, int id) {
        this.professorDAO.update(professor, id);
    }
    @Override
    public void delete (Professor professor, int id) {
        this.professorDAO.delete(professor);
    }

}
