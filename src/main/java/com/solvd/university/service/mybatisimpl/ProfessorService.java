package com.solvd.university.service.mybatisimpl;

import com.solvd.university.dao.IProfessorDAO;
import com.solvd.university.models.Professor;
import com.solvd.university.service.IProfessorService;
import com.solvd.university.util.IdException;
import com.solvd.university.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProfessorService implements IProfessorService {
    private static final Logger LOGGER = LogManager.getLogger(StudentService.class);
    @Override
    public Professor getProfessorById(int id) {
        if (id >= 1) {
            try (SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IProfessorDAO professorDAO = session.getMapper(IProfessorDAO.class);
                return professorDAO.select(id);
            }
        } else {
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }
    @Override
    public List<Professor> getAllProfessors() {
        try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml")) {
            try (SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
                return session.selectList("com.solvd.universitydb.IProfessorService.professorMapper.getAll");
            }
        } catch (IOException e) {
            LOGGER.warn("IOException, error creating the sql session" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void update(Professor professor, int id){
        if(professor !=null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IProfessorDAO professorDAO = session.getMapper(IProfessorDAO.class);
                professorDAO.update(professor, id);
                session.commit();
            }
        }else{
            LOGGER.error("Professor object is null.");
            throw new NullPointerException();
        }}

    @Override
    public void delete (Professor professor, int id){
            if(id>=1) {
                try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                    IProfessorDAO professorDAO = session.getMapper(IProfessorDAO.class);
                    professorDAO.delete(professor);
                    session.commit();
                }
            }else{
                LOGGER.error("Invalid id was entered.");
                throw new IdException("Id must be 1 or greater");
            }}}








