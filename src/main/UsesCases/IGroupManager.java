package main.UsesCases;

import main.Entity.Group;

import java.util.List;

public interface IGroupManager {
    public void addMembers(List<String> list, Group group);
    public void deleteMember(String userID, IGroupList groupList);
    public void resetMember(Group group);
    public void changeLeader(Group group, String user);
    public void resetGroup(Group group);
    public boolean Distributor(String workID, String userID, IGroupList groupList);
    public boolean isMember (String userID, String workID, IGroupList groupList);
    public boolean verifierLeader(String userID, String workID, IGroupList groupList);
    public void assignLeader(String workID, String leaderID, IGroupList groupList);
}