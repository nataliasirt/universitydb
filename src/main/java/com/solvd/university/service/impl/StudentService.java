package com.solvd.university.service.impl;

import com.solvd.university.dao.IStudentDAO;
import com.solvd.university.dao.impl.ExamDAO;
import com.solvd.university.dao.impl.StudentDAO;
import com.solvd.university.models.Exam;
import com.solvd.university.models.Student;
import com.solvd.university.service.IStudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService implements IStudentService {
    private final static Logger LOGGER = LogManager.getLogger(StudentService.class);

    private final IStudentDAO studentDAO = new StudentDAO();

    public Student getStudentById(int id) {
        if (id > 0) {
            return studentDAO.select(id);
        } else LOGGER.warn("Invalid ID provided! ");
        return null;
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
