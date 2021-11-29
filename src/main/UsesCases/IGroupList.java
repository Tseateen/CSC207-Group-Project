package main.UsesCases;

import main.Entity.Group;
public interface IGroupList extends ReadWrite{

    public void addGroup(String leader, String workID);
    public boolean deleteUser(String id);
    public int getSize();
    public Group getGroup(String workID);
    public void readInput(Group group);
}
