package com.solvd.university.designpatterns;

import com.solvd.university.dao.IStudentDAO;
import com.solvd.university.models.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DesignPatternsRunner {

    private static final Logger LOGGER = LogManager.getLogger(DesignPatternsRunner.class);

    public static void main(String[] args) {
        SqlSession session = MyBatisFactory.getSession();
        MyBatisFactory myBatisFactory = (MyBatisFactory) AbstractFactory.getFactory("mybatis");

        IStudentDAO studentMapper = (IStudentDAO) myBatisFactory.getMapper("student");

        StudentBuilder studentBuilder = new StudentBuilder(3, "Test", "Student", 99382, "testmail12@test.com", 83271, 1239);
        Student student = studentBuilder.build();

        studentMapper.insert(student);
        List<Student> allStudents = studentMapper.selectAll();
        allStudents.forEach( (s) -> LOGGER.info(s));
    }
}
