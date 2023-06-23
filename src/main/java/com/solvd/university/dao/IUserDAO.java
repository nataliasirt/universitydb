package com.solvd.university.dao;

import com.solvd.university.models.User;

import java.util.List;

public interface IUserDAO extends DAO<User>{
    User select(int id);
    List<User> selectAll();
    void insert(User user);
    void update(User user, int id);
    void delete(User user);
}
