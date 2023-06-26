package com.solvd.university.service.impl;

import com.solvd.university.dao.IStudentDAO;
import com.solvd.university.impl.ExamDAO;
import com.solvd.university.impl.StudentDAO;
import com.solvd.university.models.Exam;
import com.solvd.university.models.Student;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService implements IStudentService {
    private final IStudentDAO studentDAO = new StudentDAO();

    public Student getStudentById(int id) {
        return this.studentDAO.select(id);
    }

    public Student getFullStudentInfoById(int id) {
        ExamDAO examDAO = new ExamDAO();

        Student student = this.studentDAO.select(id);
        student.setExams(retrieveExams(student));
        return student;
    }

    public List<Student> getAllStudents() {
        return this.studentDAO.selectAll();
    }

    public List<Student> getStudentsAlphabetically() {
        return this.studentDAO.selectAll().stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    public void registerStudent(Student student) {
        this.studentDAO.insert(student);
    }

    public void updateStudentById(Student student, int id) {
        this.studentDAO.update(student, id);
    }

    public void deleteStudent(Student student) {
        this.studentDAO.delete(student);
    }

    public List<Exam> retrieveExams(Student student) {
        ExamDAO examDAO = new ExamDAO();

        List<Exam> exams = examDAO.selectAll().stream()
                .filter( exam -> exam.getStudent_id() == student.getStudentId())
                .collect(Collectors.toList());

        return exams;
    }

}
