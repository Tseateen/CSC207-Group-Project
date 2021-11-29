package main.UsesCases;

import main.Entity.Group;
public interface IGroupList extends IReadWrite {

    void addGroup(String leader, String workID);
    boolean deleteUser(String id);
    int getSize();
    Group getGroup(String workID);
}
