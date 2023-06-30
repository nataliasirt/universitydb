package com.solvd.university.dao.impl;


import com.solvd.university.dao.IEmployeeDAO;
import com.solvd.university.models.Employee;
import com.solvd.university.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO implements IEmployeeDAO {
    private final static Logger LOGGER = LogManager.getLogger(EmployeeDAO.class);
    private static final String SELECT = "SELECT e.user_id, e.id, u.name, u.surname, u.email, u.personal_id, e.position FROM users u " +
            "JOIN employees e on u.id = e.user_id and e.id = ";
    private static final String SELECTALL = "SELECT e.user_id, e.id, u.name, u.surname, u.email, u.personal_id, e.position FROM users u " +
            "RIGHT JOIN employees e on u.id = e.user_id";
    private static final String DELETE = "DELETE FROM users u JOIN employees e ON u.id = e.user_id WHERE u.id = ?";
    private static final String INSERT = "INSERT INTO employees (user_id, position) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE users u JOIN employees e ON u.id = e.user_id SET u.name = ?, u.surname = ?, u.email = ?, u.personal_id = ?, e.position = ? WHERE u.id = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    @Override
    public Employee select(int id) {
        Employee employee;
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
            String position = resultSet.getString("position");
            employee = new Employee(userId, name, surname, personalId, email, professorId, position);
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return employee;
    }
    @Override
    public List<Employee> selectAll() {
        List<Employee> employees = new ArrayList<>();
        Employee employee;
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
                String position = resultSet.getString("position");
                employee = new Employee(userId, name, surname, personalId, email, professorId, position);
                employees.add(employee);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return employees;
    }
    @Override
    public void insert(Employee employee) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, employee.getUserId());
            statement.setString(2, employee.getPosition());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }
    @Override
    public void update(Employee employee, int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getEmail());
            statement.setInt(4, employee.getPersonalId());
            statement.setString(5, employee.getPosition());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }
    @Override
    public void delete(Employee employee) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, employee.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }
    }

