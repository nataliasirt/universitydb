package com.solvd.university.service.mybatisimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CareerService {
    private final static Logger LOGGER = LogManager.getLogger(CareerService.class);
//
////    @Override
//    public void saveCareerToDB(CareerService careerService) {
//        if(careerService !=null) {
//            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
//                ICareerDAO careerDAO= session.getMapper(ICareerDAO.class);
//                careerService.(careerService);
//                session.commit();
//            }
//        }else{
//            LOGGER.error("Career object is null.");
//            throw new NullPointerException();
//        }
//    }
//
}
