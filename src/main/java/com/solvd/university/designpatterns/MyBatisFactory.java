package com.solvd.university.designpatterns;

import com.solvd.university.dao.*;
import com.solvd.university.models.*;
import com.solvd.university.service.mybatisimpl.SessionFactory;
import org.apache.ibatis.session.SqlSession;

public class MyBatisFactory extends AbstractFactory {
    private static SessionFactory factory = SessionFactory.getInstance();
    private static SqlSession session = factory.getFactory().openSession();

    public DAO<?> getMapper(String mapper) {
        switch (mapper.toLowerCase()) {
            case "user" :
                DAO<User> userMapper = session.getMapper(IUserDAO.class);
                return userMapper;
            case "student" :
                DAO<Student> studentMapper = session.getMapper(IStudentDAO.class);
                return studentMapper;
            case "professor" :
                DAO<Professor> professorMapper = session.getMapper(IProfessorDAO.class);
                return professorMapper;
            case "employee" :
                DAO<Employee> employeeMapper = session.getMapper(IEmployeeDAO.class);
                return employeeMapper;
            case "subject" :
                DAO<Subject> subjectMapper = session.getMapper(ISubjectDAO.class);
                return subjectMapper;
            case "group" :
                DAO<Group> groupMapper = session.getMapper(IGroupDAO.class);
                return groupMapper;
            case "department" :
                DAO<Department> departmentMapper = session.getMapper(IDepartmentDAO.class);
                return departmentMapper;
            case "exam" :
                DAO<Exam> examMapper = session.getMapper(IExamDAO.class);
                return examMapper;
            case "workedhours" :
                DAO<WorkedHours> workedHoursMapper = session.getMapper(IWorkedHoursDAO.class);
                return workedHoursMapper;
            case "academicrecord" :
                DAO<AcademicRecord> academicRecordMapper = session.getMapper(IAcademicRecordDAO.class);
                return academicRecordMapper;
        }
        return null;
    }

    public static SqlSession getSession() {
        return session;
    }
}
