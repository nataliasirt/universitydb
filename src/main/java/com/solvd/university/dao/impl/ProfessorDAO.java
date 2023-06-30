package com.solvd.university.dao.impl;

import com.solvd.university.dao.IProfessorDAO;
import com.solvd.university.models.Professor;
import com.solvd.university.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO implements IProfessorDAO {
    private final static Logger LOGGER = LogManager.getLogger(ExamDAO.class);
    private static final String SELECT = "SELECT p.user_id, p.id, u.name, u.surname, u.email, u.personal_id, p.degree FROM users u JOIN professors p on u.id = p.user_id and p.id = ";
    private static final String SELECTALL = "SELECT p.user_id, p.id, u.name, u.surname, u.email, u.personal_id, p.degree FROM users u RIGHT JOIN professors p on u.id = p.user_id";
    private static final String DELETE = "DELETE FROM users u JOIN professors p on u.id = p.user_id WHERE u.id = ?";
    private static final String INSERT = "INSERT into professors (user_id, degree) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE users u JOIN professors p on u.id = p.user_id SET u.name = ?, u.surname = ?, u.email = ?, u.personal_id = ?, p.degree = ? WHERE u.id = ?";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();

    @Override
    public Professor select(int id) {
        Professor professor;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            int userId = resultSet.getInt("user_id");
            int professorId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            int personalId = resultSet.getInt("personal_id");
            String degree = resultSet.getString("degree");
            professor = new Professor(userId, name, surname, personalId, email, professorId, degree);
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return professor;
    }

    @Override
    public List<Professor> selectAll() {
        List<Professor> professors = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECTALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int professorId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int personalId = resultSet.getInt("personal_id");
                String degree = resultSet.getString("degree");
                Professor professor = new Professor(userId, name, surname, personalId, email, professorId, degree);
                professors.add(professor);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return professors;
    }

    @Override
    public void insert(Professor professor) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, professor.getUserId());
            statement.setString(2, professor.getDegree());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            }
        }


    @Override
    public void update(Professor professor, int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, professor.getName());
            statement.setString(2, professor.getSurname());
            statement.setString(3, professor.getEmail());
            statement.setInt(4, professor.getPersonalId());
            statement.setString(5, professor.getDegree());
            statement.setInt(6, id);
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
    public void delete(Professor professor) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, professor.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            }
        }
    }




