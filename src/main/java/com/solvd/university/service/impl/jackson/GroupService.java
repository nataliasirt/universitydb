package com.solvd.university.service.impl.jackson;

import com.solvd.university.dao.IGroupDAO;
import com.solvd.university.dao.impl.GroupDAO;
import com.solvd.university.models.Group;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GroupService implements IGroupService {
    private final static Logger LOGGER = LogManager.getLogger(GroupService.class);
    private final IGroupDAO groupDAO = new GroupDAO();
    @Override
    public Group select(int id) {
        if (id > 0) {
            return groupDAO.select(id);
        } else LOGGER.warn("Invalid ID provided! ");
        return null;
    }
    @Override
    public List<Group> selectAll() {
        return this.groupDAO.selectAll();
    }
    @Override
    public void update (Group group, int id) {
        this.groupDAO.update(group, id);
    }
    @Override
    public void delete (Group group, int id) {
        this.groupDAO.delete(group);
    }
}
