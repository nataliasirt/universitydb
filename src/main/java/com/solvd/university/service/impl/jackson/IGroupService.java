package com.solvd.university.service.impl.jackson;

import com.solvd.university.models.Group;

import java.util.List;

public interface IGroupService {
    public Group select(int id);
    public List<Group> selectAll();
    public void update (Group group, int id);
    public void delete (Group group, int id);
}
