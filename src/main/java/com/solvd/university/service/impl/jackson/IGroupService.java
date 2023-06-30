package com.solvd.university.service.impl.jackson;

import com.solvd.university.models.Group;

import java.util.List;

public interface IGroupService {
    Group select(int id);
    List<Group> selectAll();
    void update (Group group, int id);
    void delete (Group group, int id);
}
