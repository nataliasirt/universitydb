package com.solvd.university.jdbc;
import com.solvd.university.dao.IStudentDAO;
import com.solvd.university.models.Student;
import com.solvd.university.util.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class StudentDAO implements IStudentDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    @Override
    public Student select(int id) {
        String query = "SELECT s.user_id, s.id, u.name, u.surname, u.email, u.personal_id, s.enrollment FROM users u JOIN students s on u.id = s.user_id and s.id = " + id;
        Student student;

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            int userId = resultSet.getInt("user_id");
            int studentId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            int personalId = resultSet.getInt("personal_id");
            int enrollment = resultSet.getInt("enrollment");

            student = new Student(userId, name, surname, personalId, email, studentId, enrollment);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public List<Student> selectAll() {
        String query = "SELECT s.user_id, s.id, u.name, u.surname, u.email, u.personal_id, s.enrollment FROM users u RIGHT JOIN students s on u.id = s.user_id";
        List<Student> students = new ArrayList<>();
        Student student;

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int studentId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int personalId = resultSet.getInt("personal_id");
                int enrollment = resultSet.getInt("enrollment");

                student = new Student(userId, name, surname, personalId, email, studentId, enrollment);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public Student get(int id) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public void insert(Student student) {
        String query = "INSERT into students (user_id, enrollment) VALUES (?, ?)";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, student.getUserId());
            statement.setInt(2, student.getEnrollment());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student, int id) {
        String query = "UPDATE users u JOIN students s on u.id = s.user_id SET u.name = ?, u.surname = ?, u.email = ?, u.personal_id = ?, s.enrollment = ? WHERE u.id = ?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, student.getUserId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getSurname());
            statement.setString(4, student.getEmail());
            statement.setInt(5, student.getPersonalId());
            statement.setInt(6, student.getEnrollment());
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Student student) {
        String query = "DELETE FROM users u JOIN students s on u.id = s.user_id WHERE u.id = ?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, student.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


