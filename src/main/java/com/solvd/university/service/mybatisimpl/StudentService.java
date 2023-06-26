package com.solvd.university.service.mybatisimpl;

import com.solvd.university.dao.IStudentDAO;
import com.solvd.university.models.Student;
import com.solvd.university.service.IStudentService;
import com.solvd.university.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StudentService implements IStudentService {
    private static final Logger LOGGER = LogManager.getLogger(StudentService.class);
    @Override
    public Student getStudentById(int id){
        if (id > 0) {
            try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml")) {
                try (SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
                    return session.selectOne("com.solvd.delivery.service.ICustomerService.CustomerMapper.getByID", id);
                }
            } catch (IOException e) {
                LOGGER.warn("IOException, error creating the sql session" + e.getMessage());
                e.printStackTrace();
            }
        } else LOGGER.warn("Invalid ID provided! ");
        return null;
    }
    @Override
    public List<Student> getAllStudents() {
        try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml")) {
            try (SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
                return session.selectList("com.solvd.universitydb.IStudentService.studentMapper.getAll");
            }
        } catch (IOException e) {
            LOGGER.warn("IOException, error creating the sql session" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void registerStudent(Student student){
        if (student.getName() != null &&
                student.getName() != null &&
                student.getEmail() != null) {
        try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml")) {
            try (SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
                int rowsAffected =  session.insert("com.solvd.universitydb.IStudentService.studentMapper.insert", student);
                session.commit();
                LOGGER.info("Successfully added to database, rows Affected " + rowsAffected);
            }
        } catch (IOException e) {
            LOGGER.warn("Error inserting customer to database\n");
            LOGGER.warn("IOException, error creating the sql session\n" + e.getMessage());
            e.printStackTrace();
        }
    } else
            LOGGER.warn("Error inserting customer to database");
}
    @Override
    public List<Student> getStudentsAlphabetically(){
        try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml")) {
            try (SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession()) {
                return session.selectList("com.solvd.universitydb.IStudentService.studentMapper.getAll");
            }
        } catch (IOException e) {
            LOGGER.warn("IOException, error creating the sql session" + e.getMessage());
            e.printStackTrace();
        }
        return null;

    }
    @Override
    public void updateStudentById(Student student, int id){
        if(student !=null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IStudentDAO studentDAO = session.getMapper(IStudentDAO.class);
                studentDAO.update(student, id);
                session.commit();
            }
        }else{
            LOGGER.error("Student object is null.");
            throw new NullPointerException();
        }
    }
}





