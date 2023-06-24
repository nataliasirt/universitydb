package com.solvd.university.service;

import com.solvd.university.jdbc.CareerDAO;
import com.solvd.university.models.Career;
import com.solvd.university.models.Student;
import com.solvd.university.models.Subject;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CareerService {
    private final CareerDAO careerDAO = new CareerDAO();

    public Career getCareerById(int id) {
        return this.careerDAO.select(id);
    }

    public List<Career> getAllCareers() {
        return this.careerDAO.selectAll();
    }

    public List<Career> getAllCareersByCost() {
        return this.careerDAO.selectAll().stream()
                .sorted(Comparator.comparing(Career::getCost))
                .collect(Collectors.toList());
    }

    public void registerCarreer(Career career) {
        this.careerDAO.insert(career);
    }

    public void updateCareerById(Career career, int id) {
        this.careerDAO.update(career, id);
    }

    public void deleteCareer(Career career) {
        this.careerDAO.delete(career);
    }

    public List<Subject> retrieveSubjects(Career career) {
        return this.careerDAO.selectCareerSubjects(career);
    }

    public List<Student> retrieveStudents(Career career) {
        return this.careerDAO.selectCareerStudents(career);
    }

}
