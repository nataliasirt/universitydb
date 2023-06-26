package com.solvd.university.dao.impl;

import com.solvd.university.dao.ISubjectDAO;
import com.solvd.university.models.Subject;
import com.solvd.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements ISubjectDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Subject select(int id) {
        String query = "SELECT id, name FROM subjects WHERE id = ?";
        Subject subject;
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int subjectId = resultSet.getInt("id");
            String subjectName = resultSet.getString("name");
            subject = new Subject(subjectId, subjectName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return subject;
    }


    @Override
    public List<Subject> selectAll() {
        String query = "SELECT id, name FROM subjects";
        List<Subject> subjects = new ArrayList<>();
        Subject subject;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int subjectId = resultSet.getInt("id");
                String subjectName = resultSet.getString("name");
                subject = new Subject(subjectId, subjectName);
                subjects.add(subject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return subjects;
    }


    @Override
    public void insert(Subject subject) {
        String query = "INSERT INTO subjects (name) VALUES (?)";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, subject.getName());
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
    public void update(Subject subject, int id) {
        String query = "UPDATE subjects SET name = ? WHERE id = " + id;
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, subject.getName());
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
    public void delete(Subject subject) {
        String query = "DELETE FROM subjects WHERE id = ?";
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, subject.getSubjectId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }
}
