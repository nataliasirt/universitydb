package com.solvd.university.dao.impl;


import com.solvd.university.dao.IEmployeeDAO;
import com.solvd.university.models.Employee;
import com.solvd.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO implements IEmployeeDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    @Override
    public Employee select(int id) {
        String query = "SELECT e.user_id, e.id, u.name, u.surname, u.email, u.personal_id, e.position FROM users u " +
                "JOIN employees e on u.id = e.user_id and e.id = " + id;
        Employee employee;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            resultSet.next();
            int userId = resultSet.getInt("user_id");
            int professorId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            int personalId = resultSet.getInt("personal_id");
            String position = resultSet.getString("position");

            employee = new Employee(userId, name, surname, personalId, email, professorId, position);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public List<Employee> selectAll() {
        String query = "SELECT e.user_id, e.id, u.name, u.surname, u.email, u.personal_id, e.position FROM users u " +
                "RIGHT JOIN employees e on u.id = e.user_id";
        List<Employee> employees = new ArrayList<>();
        Employee employee;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int professorId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int personalId = resultSet.getInt("personal_id");
                String position = resultSet.getString("position");

                employee = new Employee(userId, name, surname, personalId, email, professorId, position);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public void insert(Employee employee) {
        String query = "INSERT INTO employees (user_id, position) VALUES (?, ?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, employee.getUserId());
            statement.setString(2, employee.getPosition());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Employee employee, int id) {
        String query = "UPDATE users u JOIN employees e ON u.id = e.user_id SET u.name = ?, u.surname = ?, u.email = ?, u.personal_id = ?, e.position = ? WHERE u.id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getEmail());
            statement.setInt(4, employee.getPersonalId());
            statement.setString(5, employee.getPosition());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(Employee employee) {
        String query = "DELETE FROM users u JOIN employees e ON u.id = e.user_id WHERE u.id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employee.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
