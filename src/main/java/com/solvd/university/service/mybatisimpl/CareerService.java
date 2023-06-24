package com.solvd.university.service.mybatisimpl;

import com.solvd.university.dao.ICareerDAO;
import com.solvd.university.models.Career;
import com.solvd.university.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CareerService {
    private final static Logger LOGGER = LogManager.getLogger(CareerService.class);


    public void saveCareerToDB(Career career) {
        if(career !=null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                ICareerDAO careerDAO= session.getMapper(ICareerDAO.class);
                careerDAO.insert(career);
                session.commit();
            }
        }else{
            LOGGER.error("Career object is null.");
            throw new NullPointerException();
        }
    }

    }
