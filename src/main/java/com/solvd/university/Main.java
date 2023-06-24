package com.solvd.university;

import com.solvd.university.models.Student;
import com.solvd.university.service.StudentService;
import com.solvd.university.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();


    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        Student student = studentService.getStudentById(1);
        LOGGER.info(student);

        List<Student> studentList = studentService.getAllStudents();
        LOGGER.info(studentList);








    }

}
