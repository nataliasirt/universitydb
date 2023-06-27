package com.solvd.university.service.impl;

import com.solvd.university.dao.IStudentDAO;
import com.solvd.university.dao.impl.StudentDAO;
import com.solvd.university.models.Student;
import com.solvd.university.service.IStudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class StudentService implements IStudentService {
    private final static Logger LOGGER = LogManager.getLogger(StudentService.class);
    private final IStudentDAO studentDAO = new StudentDAO();
    @Override
    public Student getStudentById(int id) {
        if (id > 0) {
            return studentDAO.select(id);
        } else LOGGER.warn("Invalid ID provided! ");
        return null;
    }
    @Override
    public List<Student> getAllStudents() {
        return this.studentDAO.selectAll();
    }
    @Override
    public void update(Student student, int id) {
        this.studentDAO.update(student, id);
    }
    @Override
    public void delete(Student student, int id) {
        this.studentDAO.delete(student);
    }
    @Override
    public void insert (Student student, int id) {this.studentDAO.insert(student);}

    }


