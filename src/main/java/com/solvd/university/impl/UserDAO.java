package com.solvd.university.impl;

import com.solvd.university.dao.IUserDAO;
import com.solvd.university.models.User;
import com.solvd.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();

    @Override
    public User select(int id) {
        String query = "SELECT id, name, surname, email, personal_id FROM users WHERE id = "  + id;
        User user;

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            int userId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            int personalId = resultSet.getInt("personal_id");

            user = new User(userId, name, surname, personalId, email);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> selectAll() {
        String query = "SELECT id, name, surname, email, personal_id FROM users";
        List<User> users = new ArrayList<>();
        User user;

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int personalId = resultSet.getInt("personal_id");

                user = new User(userId, name, surname, personalId, email);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public void insert(User user) {
        String query = "INSERT into users (name, surname, email, personal_id) VALUES (?, ?, ?, ?)";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getPersonalId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(User user, int id) {
        String query = "UPDATE users SET name = ?, surname = ?, email = ?, personal_id = ? WHERE id = ?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getPersonalId() );
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(User user) {
        String query = "DELETE FROM users WHERE u.id = ?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
