package com.solvd.university.service.mybatisimpl;

import com.solvd.university.dao.ISubjectDAO;
import com.solvd.university.models.Subject;
import com.solvd.university.service.ISubjectService;
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

public class SubjectService implements ISubjectService {
    private static final Logger LOGGER = LogManager.getLogger(SubjectService.class);
    @Override
    public Subject getSubjectById(int id){
        if (id > 0) {
            try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml")) {
                try (SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
                    return session.selectOne("com.solvd.delivery.service.ISubjectService.CustomerMapper.getByID", id);
                }
            } catch (IOException e) {
                LOGGER.warn("IOException, error creating the sql session" + e.getMessage());
                e.printStackTrace();
            }
        } else LOGGER.warn("Invalid ID provided! ");
        return null;
    }
    @Override
    public List<Subject> getAllSubjects() {
        try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml")) {
            try (SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
                return session.selectList("com.solvd.universitydb.ISubjectService.studentMapper.getAll");
            }
        } catch (IOException e) {
            LOGGER.warn("IOException, error creating the sql session" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public void update(Subject subject, int id){
        if(subject !=null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                ISubjectDAO subjectDAO = session.getMapper(ISubjectDAO.class);
                subjectDAO.update(subject, id);
                session.commit();
            }
        }else{
            LOGGER.error("Subject object is null.");
            throw new NullPointerException();
        }
    }
    @Override
    public void delete (Subject subject, int id){
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                ISubjectDAO subjectDAO = session.getMapper(ISubjectDAO.class);
                subjectDAO.delete(subject);
                session.commit();
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }
    @Override
    public void registerSubject(Subject subject, int id) {
        if(subject !=null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                ISubjectDAO subjectDAO = session.getMapper(ISubjectDAO.class);
                subjectDAO.insert(subject);
                session.commit();
            }
        }else{
            LOGGER.error("Subject object is null.");
            throw new NullPointerException();
        }
    }
    }

