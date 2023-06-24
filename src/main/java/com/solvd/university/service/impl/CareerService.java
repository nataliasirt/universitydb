package com.solvd.university.service.impl;

import com.solvd.university.dao.ICareerDAO;
import com.solvd.university.dao.IStudentDAO;
import com.solvd.university.impl.CareerDAO;
import com.solvd.university.impl.StudentDAO;
import com.solvd.university.models.Career;
import com.solvd.university.models.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CareerService implements ICareerService {
    private final static Logger LOGGER = LogManager.getLogger(CareerService.class);

    @Override
    public void saveCareerServiceToDB(Career career) {
        if(career != null ) {
            ICareerDAO careerDAO = new CareerDAO();
            careerDAO.insert(career);
        }else{
            LOGGER.error("Career object is null.");
            throw new NullPointerException();
        }
    }
    @Override
    public void saveStudentToDB(Student student) {
        if(student != null) {
            IStudentDAO studentDAO = new StudentDAO();
            studentDAO.insert(student);
        }else{
            LOGGER.error("Student object is null.");
            throw new NullPointerException();
        }
    }




}
