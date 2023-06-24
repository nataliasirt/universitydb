package com.solvd.university.service;

import com.solvd.university.dao.IStudentDAO;
import com.solvd.university.impl.StudentDAO;
import com.solvd.university.models.Student;
import com.solvd.university.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class StudentService {
    private final static Logger LOGGER = LogManager.getLogger(ProfessorService.class);
    private final IStudentDAO studentDAO = new StudentDAO();

    public Student getStudentById(int id) {
        return this.studentDAO.select(id);
    }

    public List<Student> getAllStudents() {
        return this.studentDAO.selectAll();
    }

    public void registerStudentToDB(Student student, int id) {
        if(student !=null) {
            try (SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IStudentDAO iStudentDAO = session.getMapper(IStudentDAO.class);
                studentDAO.insert(student);
                session.commit();
            }
        } else{
            LOGGER.error("Doctor object is null.");
            throw new NullPointerException();
        }

    }

}

