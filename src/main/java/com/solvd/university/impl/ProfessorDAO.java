package com.solvd.university.impl;

import com.solvd.university.dao.IProfessorDAO;
import com.solvd.university.models.Professor;
import com.solvd.university.util.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO implements IProfessorDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();

    @Override
    public Professor getById(int id) {
        String query = "SELECT p.user_id, p.id, u.name, u.surname, u.email, u.personal_id, p.degree FROM users u JOIN professors p on u.id = p.user_id and p.id = " + id;
        Professor professor;

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

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
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return professor;
    }

    @Override
    public List<Professor> selectAll() {
        String query = "SELECT p.user_id, p.id, u.name, u.surname, u.email, u.personal_id, p.degree FROM users u RIGHT JOIN professors p on u.id = p.user_id";
        List<Professor> professors = new ArrayList<>();
        Professor professor;

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int professorId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int personalId = resultSet.getInt("personal_id");
                String degree = resultSet.getString("degree");

                professor = new Professor(userId, name, surname, personalId, email, professorId, degree);
                professors.add(professor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return professors;
    }

    @Override
    public void insert(Professor professor) {
        String query = "INSERT into professors (user_id, degree) VALUES (?, ?)";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, professor.getUserId());
            statement.setString(2, professor.getDegree());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Professor professor, int id) {
        String query = "UPDATE users u JOIN professors p on u.id = p.user_id SET u.name = ?, u.surname = ?, u.email = ?, u.personal_id = ?, p.degree = ? WHERE u.id = ?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, professor.getUserId());
            statement.setString(2, professor.getName());
            statement.setString(3, professor.getSurname());
            statement.setString(4, professor.getEmail());
            statement.setInt(5, professor.getPersonalId());
            statement.setString(6, professor.getDegree());
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Professor professor) {
        String query = "DELETE FROM users u JOIN professors p on u.id = p.user_id WHERE u.id = ?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, professor.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }
}


