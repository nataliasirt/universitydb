package com.solvd.university.dao.impl;

import com.solvd.university.dao.IExamDAO;
import com.solvd.university.models.Exam;
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


public class ExamDAO implements IExamDAO {
    private final static Logger LOGGER = LogManager.getLogger(ExamDAO.class);
    private static final String SELECT = "SELECT e.id, e.mark, e.student_id, e.subject_id, s.name FROM exams e JOIN subjects s ON e.subject_id = s.id WHERE id = ?";
    private static final String SELECTALL = "SELECT e.id, e.mark, e.subject_id, s.name FROM exams e JOIN subjects s ON e.subject_id = s.id";
    private static final String DELETE = "DELETE FROM exams WHERE id = ?";
    private static final String INSERT = "INSERT INTO exams (mark, subject_id, student_id) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE exams SET mark = ?, student_id = ?, subject_id = ? WHERE id = ";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Exam select(int id) {
        Exam exam;
        Subject subject = new Subject();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
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
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return exam;
    }
    @Override
    public List<Exam> selectAll() {
        List<Exam> exams = new ArrayList<>();
        Subject subject;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECTALL);
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
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return exams;
    }

    @Override
    public void insert(Exam exam) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setDouble(1, exam.getMark());
            statement.setInt(2, exam.getSubject().getSubjectId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            }
        }

    @Override
    public void update(Exam exam, int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(UPDATE);
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
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, exam.getExamId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        }
    }


