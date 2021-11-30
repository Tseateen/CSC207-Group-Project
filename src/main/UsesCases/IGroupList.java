package main.UsesCases;

import main.Entity.Group;

import java.io.Serializable;

public interface IGroupList extends IReadWrite, Serializable {

    void addGroup(String leader, String workID);
    Group getGroup(String workID);
}
