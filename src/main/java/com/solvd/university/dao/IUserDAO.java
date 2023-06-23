package com.solvd.university.dao;

import com.solvd.university.models.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDAO extends DAO<User>{
    User select(int id);
    List<User> selectAll();
    void insert(@Param("user") User user);
    void update(@Param("user") User user, @Param("id") int id);
    void delete(@Param("user") User user);
}
