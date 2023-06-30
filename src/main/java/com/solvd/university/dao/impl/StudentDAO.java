package com.solvd.university.dao.impl;
import com.solvd.university.dao.IStudentDAO;
import com.solvd.university.models.Student;
import com.solvd.university.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class StudentDAO implements IStudentDAO {
    private final static Logger LOGGER = LogManager.getLogger(ExamDAO.class);
    private static final String SELECT = "SELECT s.user_id, s.id, u.name, u.surname, u.email, u.personal_id, s.enrollment FROM users u JOIN students s on u.id = s.user_id and s.id = ";
    private static final String SELECTALL = "SELECT s.user_id, s.id, u.name, u.surname, u.email, u.personal_id, s.enrollment FROM users u RIGHT JOIN students s on u.id = s.user_id";
    private static final String DELETE = "DELETE FROM users u JOIN students s on u.id = s.user_id WHERE u.id = ?";
    private static final String INSERT = "INSERT into students (user_id, enrollment) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE users u JOIN students s on u.id = s.user_id SET u.name = ?, u.surname = ?, u.email = ?, u.personal_id = ?, s.enrollment = ? WHERE u.id = ?";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();

    @Override
    public Student select(int id) {
        Student student;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(SELECT);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int userId = resultSet.getInt("user_id");
            int studentId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            int personalId = resultSet.getInt("personal_id");
            int enrollment = resultSet.getInt("enrollment");
            student = new Student(userId, name, surname, personalId, email, studentId, enrollment);
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return student;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> students = new ArrayList<>();
        Student student;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(SELECTALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int studentId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int personalId = resultSet.getInt("personal_id");
                int enrollment = resultSet.getInt("enrollment");
                student = new Student(userId, name, surname, personalId, email, studentId, enrollment);
                students.add(student);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            }
        return students;
    }

    @Override
    public void insert(Student student) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setInt(1, student.getUserId());
            statement.setInt(2, student.getEnrollment());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }


    @Override
    public void update(Student student, int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setString(3, student.getEmail());
            statement.setInt(4, student.getPersonalId());
            statement.setInt(5, student.getEnrollment());
            statement.setInt(6, student.getUserId());
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
        public void delete(Student student) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
            try {PreparedStatement statement = connection.prepareStatement(DELETE);
                statement.setInt(1, student.getUserId());
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("Unable to execute Prepared Statement.");
                throw new RuntimeException(e);
            }finally {
                connectionPool.releaseConnection(connection);
                }
            }
        }




