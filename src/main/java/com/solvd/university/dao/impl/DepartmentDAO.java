package com.solvd.university.dao.impl;

import com.solvd.university.dao.IDepartmentDAO;
import com.solvd.university.models.Department;
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

public class DepartmentDAO implements IDepartmentDAO {
    private final static Logger LOGGER = LogManager.getLogger(DepartmentDAO.class);
    private static final String SELECT = "SELECT d.id, d.area, p.user_id, d.head, u.name, u.surname, u.email, u.personal_id, p.degree FROM departments d " +
            "JOIN professors p ON d.head = p.id " +
            "JOIN users u ON p.user_id = u.id WHERE d.id = ?";
    private static final String SELECTALL = "SELECT d.id, d.area, p.user_id, d.head, u.name, u.surname, u.email, u.personal_id, p.degree FROM departments d " +
            "JOIN professors p ON d.head = p.id " +
            "JOIN users u ON p.user_id = u.id";
    private static final String DELETE = "DELETE FROM departments WHERE id = ?";
    private static final String INSERT = "INSERT INTO departments (area, head) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE departments SET area = ?, head = ? WHERE id = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Department select(int id) {
        Department department = new Department();
        Professor head = new Professor();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT);){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    int departmentId = resultSet.getInt("id");
                    String area = resultSet.getString("area");
                head.setUserId(resultSet.getInt("user_id"));
                head.setProfessorId(resultSet.getInt("head"));
                head.setName(resultSet.getString("name"));
                head.setSurname(resultSet.getString("surname"));
                head.setEmail(resultSet.getString("email"));
                head.setPersonalId(resultSet.getInt("personal_id"));
                head.setDegree(resultSet.getString("degree"));
                department = new Department(departmentId, area, head);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return department;
    }

    @Override
    public List<Department> selectAll() {
        List<Department> departments = new ArrayList<>();
        Professor head = new Professor();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECTALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Department info
                int departmentId = resultSet.getInt("id");
                String area = resultSet.getString("area");
                // Department head info
                head.setUserId(resultSet.getInt("user_id"));
                head.setProfessorId(resultSet.getInt("head"));
                head.setName(resultSet.getString("name"));
                head.setSurname(resultSet.getString("surname"));
                head.setEmail(resultSet.getString("email"));
                head.setPersonalId(resultSet.getInt("personal_id"));
                head.setDegree(resultSet.getString("degree"));
                Department department = new Department(departmentId, area, head);
                departments.add(department);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return departments;
    }

    @Override
    public void insert(Department department) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, department.getArea());
            statement.setInt(2, department.getHead().getProfessorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Department department, int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, department.getArea());
            statement.setInt(2, department.getHead().getProfessorId());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Department department) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, department.getDepartmentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }
}


