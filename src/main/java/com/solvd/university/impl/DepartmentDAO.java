package com.solvd.university.impl;

import com.solvd.university.dao.IDepartmentDAO;
import com.solvd.university.models.Department;
import com.solvd.university.models.Professor;
import com.solvd.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DepartmentDAO implements IDepartmentDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();
    @Override
    public Department select(int id) {
        String query = "SELECT d.id, d.area, p.user_id, d.head, u.name, u.surname, u.email, u.personal_id, p.degree FROM departments d " +
                "JOIN professors p on d.head = p.id " +
                "JOIN users u on p.user_id = u.id WHERE d.id = " + id;
        Department department;
        Professor head = new Professor();

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            //Department info
            int departmentId = resultSet.getInt("id");
            String area = resultSet.getString("area");

            //Department head info
            head.setUserId(resultSet.getInt("user_id"));
            head.setProfessorId(resultSet.getInt("head"));
            head.setName(resultSet.getString("name"));
            head.setSurname(resultSet.getString("surname"));
            head.setEmail(resultSet.getString("email"));
            head.setPersonalId(resultSet.getInt("personal_id"));
            head.setDegree(resultSet.getString("degree"));

            department = new Department(departmentId, area, head);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return department;
    }

    @Override
    public List<Department> selectAll() {
        String query = "SELECT d.id, d.area, p.user_id, d.head, u.name, u.surname, u.email, u.personal_id, p.degree FROM departments d " +
                "JOIN professors p on d.head = p.id " +
                "JOIN users u on p.user_id = u.id";
        List<Department> departments = new ArrayList<>();
        Department department;
        Professor head = new Professor();

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Department info
                int departmentId = resultSet.getInt("id");
                String area = resultSet.getString("area");

                //Department head info
                head.setUserId(resultSet.getInt("user_id"));
                head.setProfessorId(resultSet.getInt("head"));
                head.setName(resultSet.getString("name"));
                head.setSurname(resultSet.getString("surname"));
                head.setEmail(resultSet.getString("email"));
                head.setPersonalId(resultSet.getInt("personal_id"));
                head.setDegree(resultSet.getString("degree"));

                department = new Department(departmentId, area, head);
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return departments;
    }
    @Override
    public void insert(Department department) {
        String query = "INSERT INTO departments (area, head) VALUES (?, ?)";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, department.getArea());
            statement.setInt(2, department.getHead().getProfessorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        }

    @Override
    public void update(Department department, int id) {
        String query = "UPDATE departments SET area = ?, head = ? WHERE id = " + id;

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, department.getArea());
            statement.setInt(2, department.getHead().getProfessorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        }

    @Override
    public void delete(Department department) {
        String query = "DELETE FROM departments WHERE id = ?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, department.getDepartmentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        }

    public List<Professor> selectDepartmentProfessors(Department department) {
        String query = "SELECT p.user_id, p.id, u.name, u.surname, u.email, u.personal_id, p.degree FROM users u " +
                "RIGHT JOIN professors p on u.id = p.user_id " +
                "JOIN professor_department pd on pd.professor_id = p.id and pd.department_id = " + department.getDepartmentId();

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
}
