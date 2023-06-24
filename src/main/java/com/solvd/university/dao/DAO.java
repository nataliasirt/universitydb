package com.solvd.university.dao;

import java.util.List;

public interface DAO<T> {
        T getById(int id);
        List<T> selectAll();
        void insert(T t);
        void update(T t, int id);
        void delete(T t);

    }

