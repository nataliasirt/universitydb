package com.solvd.university.dao.impl;

import com.solvd.university.dao.IExamDAO;
import com.solvd.university.models.Exam;
import com.solvd.university.models.Subject;
import com.solvd.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ExamDAO implements IExamDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Exam select(int id) {
        String query = "SELECT e.id, e.mark, e.student_id, e.subject_id, s.name FROM exams e JOIN subjects s ON e.subject_id = s.id WHERE id = ?";
        Exam exam;
        Subject subject = new Subject();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int examId = resultSet.getInt("id");
                double mark = resultSet.getDouble("mark");
                subject.setSubjectId(resultSet.getInt("subject_id"));
                subject.setName(resultSet.getString("name"));
                int studentId = resultSet.getInt("student_id");

                exam = new Exam(examId, mark, subject, studentId);
            } else {
                throw new RuntimeException("Exam not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exam;
    }


    @Override
    public List<Exam> selectAll() {
        String query = "SELECT e.id, e.mark, e.subject_id, s.name FROM exams e JOIN subjects s ON e.subject_id = s.id";
        List<Exam> exams = new ArrayList<>();
        Subject subject;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int examId = resultSet.getInt("id");
                double mark = resultSet.getDouble("mark");
                subject = new Subject(resultSet.getInt("subject_id"), resultSet.getString("name"));
                int studentId = resultSet.getInt("student_id");

                Exam exam = new Exam(examId, mark, subject, studentId);
                exams.add(exam);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exams;
    }

    @Override
    public void insert(Exam exam) {
        String query = "INSERT INTO exams (mark, subject_id, student_id) VALUES (?, ?, ?)";

        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setDouble(1, exam.getMark());
            statement.setInt(2, exam.getSubject().getSubjectId());
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
    public void update(Exam exam, int id) {
        String query = "UPDATE exams SET mark = ?, student_id = ?, subject_id = ? WHERE id = " + id;

        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, exam.getMark());
            statement.setInt(3, exam.getSubject().getSubjectId());
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
    public void delete(Exam exam) {
        String query = "DELETE FROM exams WHERE id = ?";

        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, exam.getExamId());
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

