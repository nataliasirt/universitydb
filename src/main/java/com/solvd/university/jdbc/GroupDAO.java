package com.solvd.university.jdbc;


import com.solvd.university.dao.IGroupDAO;
import com.solvd.university.models.Group;
import com.solvd.university.models.Professor;
import com.solvd.university.models.Student;
import com.solvd.university.models.Subject;
import com.solvd.university.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GroupDAO implements IGroupDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    @Override
    public Group select(int id) {
        String query = "SELECT g.id, g.code, p.user_id, g.head, u.name, u.surname, u.email, u.personal_id, p.degree, g.subject_id, s.name as subject_name FROM group g " +
                "JOIN professors p on g.head = p.id " +
                "JOIN users u on p.user_id = u.id " +
                "JOIN subjects s on g.subject_id = s.id " +
                "WHERE g.id = " + id;
        Group group;
        Professor head = new Professor();
        Subject subject = new Subject();

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            int groupId = resultSet.getInt("id");
            String groupCode = resultSet.getString("code");


            head.setUserId(resultSet.getInt("user_id"));
            head.setProfessorId(resultSet.getInt("head"));
            head.setName(resultSet.getString("name"));
            head.setSurname(resultSet.getString("surname"));
            head.setEmail(resultSet.getString("email"));
            head.setPersonalId(resultSet.getInt("personal_id"));
            head.setDegree(resultSet.getString("degree"));


            subject.setSubjectId(resultSet.getInt("subject_id"));
            subject.setName("subject_name");

            group = new Group(groupId, groupCode, head, subject);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return group;
    }

    @Override
    public List<Group> selectAll() {
        String query = "SELECT g.id, g.code, p.user_id, g.head, u.name, u.surname, u.email, u.personal_id, p.degree, g.subject_id, s.name as subject_name FROM group g " +
                "JOIN professors p on g.head = p.id " +
                "JOIN users u on p.user_id = u.id " +
                "JOIN subjects s on g.subject_id = s.id ";

        List<Group> groups = new ArrayList<>();
        Group group;
        Professor head = new Professor();
        Subject subject = new Subject();

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int groupId = resultSet.getInt("id");
                String groupCode = resultSet.getString("code");


                head.setUserId(resultSet.getInt("user_id"));
                head.setProfessorId(resultSet.getInt("head"));
                head.setName(resultSet.getString("name"));
                head.setSurname(resultSet.getString("surname"));
                head.setEmail(resultSet.getString("email"));
                head.setPersonalId(resultSet.getInt("personal_id"));
                head.setDegree(resultSet.getString("degree"));


                subject.setSubjectId(resultSet.getInt("subject_id"));
                subject.setName("subject_name");

                group = new Group(groupId, groupCode, head, subject);
                groups.add(group);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }
    @Override
    public void insert(Group group) {
        String query = "INSERT INTO groups (code, subject_id, head) VALUES (?, ?, ?)";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, group.getGroupCode());
            statement.setInt(2, group.getSubject().getSubjectId());
            statement.setInt(3, group.getHead().getProfessorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Group group, int id) {
        String query = "UPDATE groups SET code = ?, subject_id = ? head = ? WHERE id = " + id;

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, group.getGroupCode());
            statement.setInt(2, group.getSubject().getSubjectId());
            statement.setInt(3, group.getHead().getProfessorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Group group) {
        String query = "DELETE FROM groups WHERE id = ?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, group.getGroupId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> selectGroupStudents(Group group) {
        String query = "SELECT s.user_id, s.id, u.name, u.surname, u.email, u.personal_id, s.enrollment FROM users u " +
                "RIGHT JOIN students s on u.id = s.user_id " +
                "JOIN group_student gs on gs.student_id = s.id and gs.group_id = " + group.getGroupId();
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
}
