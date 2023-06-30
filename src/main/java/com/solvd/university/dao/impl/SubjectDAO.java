package com.solvd.university.dao.impl;

import com.solvd.university.dao.ISubjectDAO;
import com.solvd.university.models.Subject;
import com.solvd.university.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements ISubjectDAO {
    private final static Logger LOGGER = LogManager.getLogger(ExamDAO.class);
    private static final String SELECT = "SELECT id, name FROM subjects WHERE id = ?";
    private static final String SELECTALL = "SELECT id, name FROM subjects";
    private static final String DELETE = "DELETE FROM subjects WHERE id = ?";
    private static final String INSERT = "INSERT INTO subjects (name) VALUES (?)";
    private static final String UPDATE = "UPDATE subjects SET name = ? WHERE id = ";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();


    @Override
    public Subject select(int id) {
        Subject subject;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(SELECT);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int subjectId = resultSet.getInt("id");
            String subjectName = resultSet.getString("name");
            subject = new Subject(subjectId, subjectName);
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            }
        return subject;
    }


    @Override
    public List<Subject> selectAll() {
        List<Subject> subjects = new ArrayList<>();
        Subject subject;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(SELECTALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int subjectId = resultSet.getInt("id");
                String subjectName = resultSet.getString("name");
                subject = new Subject(subjectId, subjectName);
                subjects.add(subject);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            }
        return subjects;
    }


    @Override
    public void insert(Subject subject) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, subject.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            }
        }


    @Override
    public void update(Subject subject, int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, subject.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            }
        }


    @Override
    public void delete(Subject subject) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, subject.getSubjectId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            }
        }
    }

