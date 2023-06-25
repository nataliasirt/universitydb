package com.solvd.university.designpatterns;

public class AbstractFactory {
    private static DaoFactory daoFactory = new DaoFactory();
    private static MyBatisFactory myBatisFactory = new MyBatisFactory();

    public static AbstractFactory getFactory(String factory) {
        switch (factory.toLowerCase()) {
            case "dao":
                return daoFactory;
            case "mybatis":
                return myBatisFactory;
        }

        return null;
    }
}
