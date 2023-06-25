package com.solvd.university.service.mybatisimpl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class SessionFactory {
    private static SessionFactory instance;
    private SqlSessionFactory factory;

    private SessionFactory(){
        String resource = "mybatis/mappers/mybatis_config.xml";
        Reader reader = null;

        try {
            reader = Resources.getResourceAsReader(resource);
            factory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getInstance() {
        if (instance == null) {
            instance = new SessionFactory();
        }
        return instance;
    }

    public SqlSessionFactory getFactory() {
        return this.factory;
    }
}
